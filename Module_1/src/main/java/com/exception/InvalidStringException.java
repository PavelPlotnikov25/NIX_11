package com.exception;

import java.io.IOException;

public class InvalidStringException extends IOException {
    public InvalidStringException(String message) {
        super(message);
    }
}
