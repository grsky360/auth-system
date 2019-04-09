package ilio.auth.core.util;

import ilio.auth.core.exceptions.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtil {

    @Aspect
    @Component
    @Profile("!prod")
    public static class Handler {
        @Around("bean(redisUtil)")
        public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
            List<Object> args = Arrays.asList(joinPoint.getArgs());
            if (!args.isEmpty()) {
                args.set(0, handleNonProdKey(String.valueOf(args.get(0))));
            }
            return joinPoint.proceed(args.toArray());
        }

        private String handleNonProdKey(String key) {
            return "dev/" + key;
        }
    }

    @Autowired
    private RedissonClient client;

    public <K, V> RMap<K, V> map(String key) {
        return client.getMap(key);
    }

    public <K, V> V getMap(String key, K valueKey) {
        return this.<K, V>map(key).get(valueKey);
    }

    public <K, V> V putMap(String key, K valueKey, V valueValue) {
        return this.<K, V>map(key).put(valueKey, valueValue);
    }

    public <K, V> V removeMap(String key, K valueKey) {
        return this.<K, V> map(key).remove(valueKey);
    }

    public <K, V> RMapCache<K, V> mapCache(String key) {
        return client.getMapCache(key);
    }

    public <K, V> V getMapCache(String key, K valueKey) {
        return this.<K, V>mapCache(key).get(valueKey);
    }

    public <K, V> V putMapCache(String key, K valueKey, V valueValue, long second) {
        return this.<K, V>mapCache(key).put(valueKey, valueValue, second, TimeUnit.SECONDS);
    }

    public <K, V> V removeMapCache(String key, K valueKey) {
        return this.<K, V> mapCache(key).remove(valueKey);
    }
}
