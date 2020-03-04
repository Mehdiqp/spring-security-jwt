package com.company.security.jwt.exception;

public class UserNameAlreadyExistException extends RuntimeException {
    public UserNameAlreadyExistException() {
        super();
    }

    public UserNameAlreadyExistException(String message) {
        super(message);
    }

    public UserNameAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameAlreadyExistException(Throwable cause) {
        super(cause);
    }

    protected UserNameAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
