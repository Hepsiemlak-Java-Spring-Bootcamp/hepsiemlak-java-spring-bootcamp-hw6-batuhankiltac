package com.emlakburada.emlakburada.core;

public class ErrorDataResult<T> extends DataResult<T> {

    public ErrorDataResult(String message, T data) {
        super(false, message, data);
    }
}