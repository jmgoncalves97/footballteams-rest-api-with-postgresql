package com.example.footballteams.exception;

import java.lang.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String menssage, int id){
        super(String.format(menssage, id));
    }
}
