package ilio.oauth.core.controller;

import ilio.oauth.core.controller.resp.JsonResult;
import ilio.oauth.core.exceptions.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity unauthorizedException(UnauthorizedException e) {
        JsonResult jsonResult = JsonResult.of("unauthorized");
        jsonResult.status(jodd.net.HttpStatus.error401());
        return new ResponseEntity<>(jsonResult, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity runtimeException(RuntimeException e) {
        log.error("", e);
        JsonResult jsonResult = JsonResult.of(e.getMessage());
        jsonResult.status(jodd.net.HttpStatus.error500());
        return new ResponseEntity<>(jsonResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity exception(Exception e) {
        log.error("", e);
        JsonResult jsonResult = JsonResult.of(e.getMessage());
        jsonResult.status(jodd.net.HttpStatus.error500());
        return new ResponseEntity<>(jsonResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
