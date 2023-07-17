package com.example.javaapidemo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidParametersException extends RuntimeException{

    private String message = "";

    public InvalidParametersException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
