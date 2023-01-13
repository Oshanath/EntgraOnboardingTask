package com.example.EntgraTask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.ALREADY_REPORTED,
        reason = "Device already exists"
)
public class DeviceAlreadyExistsException extends RuntimeException{

    public DeviceAlreadyExistsException(String message){
        super(message);
    }

}
