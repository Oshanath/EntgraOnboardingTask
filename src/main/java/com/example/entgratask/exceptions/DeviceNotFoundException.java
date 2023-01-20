package com.example.entgratask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.NOT_FOUND,
        reason = "Device not found"
)
public class DeviceNotFoundException extends RuntimeException{

    public DeviceNotFoundException(String message){
        super(message);
    }

}
