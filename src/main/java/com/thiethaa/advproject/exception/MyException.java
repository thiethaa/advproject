package com.thiethaa.advproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MyException extends RuntimeException {

    public MyException(String msg) {
        super(msg );
    }

}

