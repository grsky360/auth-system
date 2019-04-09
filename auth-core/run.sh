#!/bin/bash

function usage() {
    echo "Usage: "
    echo "  run.sh build"
    echo "  run.sh [run] dev|prod"
    echo "  run.sh status"
    echo "  run.sh log"
    echo "  run.sh stop"
}

PARAM_COUNT=$#
if [ $PARAM_COUNT -lt 1 ] || [ $PARAM_COUNT -gt 2 ]; then
    usage
    exit 1
fi

APP=auth-core
VERSION=1.0.0
COMMAND="$1"
ENV="$2"

JAR_FILE="${APP}-${VERSION}.jar"

echo "------------------------------------------------------"
echo "APP: ${APP}"
echo "VERSION: ${VERSION}"
echo "COMMAND: ${COMMAND}"
if [[ "$ENV" != "" ]]; then
    echo "$ENV"
fi
echo "------------------------------------------------------"


PID_FILE=RUNNING_PID
STATUS_RUNNING="running"
STATUS_STOPPED="stopped"

function read_status() {
    if [ -f $PID_FILE ]; then
        pid=$(< "$PID_FILE")
        if [ -z "`ps ax | grep -w ${pid} | grep -v grep`" ]; then
            cstatus="${STATUS_STOPPED}"
        else
            cstatus="${STATUS_RUNNING}"
        fi
    else
        cstatus="$STATUS_STOPPED"
    fi
}

function build() {
    ./mvnw package
}

function start() {
    if [ "$cstatus" = "$STATUS_RUNNING" ]; then
        stop
    fi

    if [ -z $ENV ]; then
        usage
        exit 1
    fi

    echo "---------------- starting -------------------------"
    echo "---------------- profile: ${ENV} --------------------"


    RUN_COMMAND=
    if [[ "$ENV" != "prod" ]] && [[ "$ENV" != "dev" ]]; then
        exit 1
    fi

    JAR_PATH=
    if [[ -f "dist/${JAR_FILE}" ]]; then
        JAR_PATH=dist
    elif [[ -f "target/${JAR_FILE}" ]]; then
        JAR_PATH=target
    else
        echo "could not found jar file: ${JAR_FILE}"
        echo "please build first or check APP_NAME and VERSION!"
        usage
        exit 1
    fi

    nohup java -jar ${JAR_PATH}/${JAR_FILE} --spring.profiles.active=${ENV} > logs/full.log 2>&1 &

    new_pid=$!
    echo ${new_pid} > ${PID_FILE}
    echo "$APP is started, pid = $new_pid"
}

function stop() {
    TRY_COUNT=5
    read_status
    echo "stopping $APP, pid = $pid"
    for ((i=1; i<=${TRY_COUNT}; i++))
    do
        read_status
        if [[ "$cstatus" = "$STATUS_RUNNING" ]]; then
            echo "try count: ${i}"
            if [[ ! ${i} -gt ${TRY_COUNT} ]]; then
                kill ${pid}
            else
                kill -9 ${pid}
            fi
            sleep 1
        else
            if [[ -f ${PID_FILE} ]]; then
                rm ${PID_FILE}
            fi
            echo "$APP is stopped"
            return 0
        fi
    done
    echo "ERROR: $APP cannot be killed"
    exit 1
}

function log() {
    tail -f logs/full.log
}

case "$COMMAND" in
    status)
        read_status
        if [ "$cstatus" = "$STATUS_RUNNING" ]; then
            echo "$APP is running"
        else
            echo "$APP is stopped"
        fi
        ;;
    stop)
        stop
        ;;
    run)
        start
        ;;
    build)
        build
        ;;
    log)
        log
        ;;
    *)
        ENV=$COMMAND
        COMMAND="run"
        start
        ;;
esac

