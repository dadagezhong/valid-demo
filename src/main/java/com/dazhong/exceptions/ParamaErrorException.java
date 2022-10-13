package com.dazhong.exceptions;

public class ParamaErrorException extends RuntimeException{

    public ParamaErrorException() {
    }

    public ParamaErrorException(String message) {
        super(message);
    }
}
