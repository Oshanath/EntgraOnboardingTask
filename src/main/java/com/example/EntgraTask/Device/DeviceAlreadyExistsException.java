package com.example.EntgraTask.Device;

public class DeviceAlreadyExistsException extends RuntimeException{

    public DeviceAlreadyExistsException(String message){
        super(message);
    }

}
