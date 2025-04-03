package com.legoinventorytool.api.exceptions;

public class SetNotFoundException extends RuntimeException {
    public SetNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
