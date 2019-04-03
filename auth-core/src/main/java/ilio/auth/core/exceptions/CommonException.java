package ilio.auth.core.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommonException extends RuntimeException {

    public CommonException(String message) {
        super(message);
    }
}