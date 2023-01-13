package com.example.EntgraTask.controllers;

import com.example.EntgraTask.Routes;
import com.example.EntgraTask.device.Device;
import com.example.EntgraTask.services.DeviceService;
import com.example.EntgraTask.device.DeviceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = Routes.DEVICES)
public class DeviceController {

    private static class ChangeDeviceStatusDTO {
        public final DeviceStatus newStatus;

        private ChangeDeviceStatusDTO(String name, DeviceStatus newStatus) {
            this.newStatus = newStatus;
        }
    }

    private final DeviceService deviceService;
    Logger logger;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
        logger = LoggerFactory.getLogger(DeviceController.class);
        logger.info("Device controller created.");
    }

    @GetMapping
    public ResponseEntity<List<Device>> getDevices(){
        logger.info("GET /devices");
        return new ResponseEntity<>(deviceService.getAllDevices(), HttpStatus.FOUND);
    }

    @PostMapping("/{name}")
    public ResponseEntity<Void> enrolDevice(@RequestBody @Valid Device device){
        logger.info("POST /devices");
        deviceService.enrolDevice(device);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Void> changeDeviceStatus(@PathVariable String name, @RequestBody ChangeDeviceStatusDTO dto){
        logger.info("PUT /devices/" + name);
        deviceService.updateStatus(name, dto.newStatus);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteDevice(@PathVariable String name){
        logger.info("DELETE /devices");
        deviceService.deleteDevice(name);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
