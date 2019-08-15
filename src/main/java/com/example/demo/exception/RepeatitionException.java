package com.example.demo.exception;

public class RepeatitionException extends Exception {
    public RepeatitionException() {
        super();
    }

    public RepeatitionException(String message) {
        super(message);
    }

    public RepeatitionException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepeatitionException(Throwable cause) {
        super(cause);
    }

    protected RepeatitionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
