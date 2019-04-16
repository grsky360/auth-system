package ilio.oauth.core.controller.resp;

import jodd.exception.ExceptionUtil;
import jodd.net.HttpStatus;
import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class JsonResult<T> {
    private final T body;
    private int status = 200;
    private String message = "ok";

    public static <T> JsonResult<T> of(T object) {
        return new JsonResult<>(object);
    }

    public static JsonResult of(HttpStatus httpStatus) {
        return empty().status(httpStatus);
    }

    public static JsonResult of(Exception exception) {
        HashMap<String, Object> errorMap = new HashMap<>();
        errorMap.put("message", ExceptionUtil.message(exception));
        errorMap.put("error", exception.getClass().getName());
        errorMap.put("cause", exception.getCause() != null ? exception.getCause().getClass().getName() : null);
        List<String> details = Arrays
                .stream(ExceptionUtil.getStackTrace(exception, null, null))
                .map(StackTraceElement::toString)
                .collect(Collectors.toList());
        errorMap.put("details", details);
        return (new JsonResult<>(errorMap)).status(HttpStatus.error500().internalError());
    }

    public static JsonResult empty() {
        return new JsonResult<>(null);
    }

    public JsonResult(T body) {
        this.body = body;
    }

    public JsonResult status(int status) {
        this.status = status;
        return this;
    }

    public JsonResult status(HttpStatus httpStatus) {
        this.status = httpStatus.status();
        this.message = httpStatus.message();
        return this;
    }

    public JsonResult status(int status, String message) {
        this.status = status;
        this.message = message;
        return this;
    }

    public T value() {
        return this.body;
    }

    public int status() {
        return this.status;
    }

    public String message() {
        return this.message;
    }
}
