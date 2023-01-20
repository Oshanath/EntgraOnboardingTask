package com.example.entgratask.controllers;

import com.example.entgratask.Routes;
import com.example.entgratask.device.Device;
import com.example.entgratask.services.DeviceService;
import com.example.entgratask.device.DeviceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
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

    private static class EnrolDeviceDTO {

        private long id;
        private String name;
        private DeviceStatus status;
        private String model;
        private LocalDateTime enrolledDateTime;

        public EnrolDeviceDTO(String name, DeviceStatus status, String model, LocalDateTime enrolledDateTime) {
            this.name = name;
            this.status = status;
            this.model = model;
            this.enrolledDateTime = enrolledDateTime;

            if (status == null){
                this.status = DeviceStatus.ENROLLED;
            }
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
        return new ResponseEntity<>(deviceService.getAllDevices(), HttpStatus.OK);
    }

    @PostMapping("/{name}")
    public ResponseEntity<Void> enrolDevice(@RequestBody @Valid EnrolDeviceDTO dto){
        logger.info("POST /devices");
        Device device = new Device(dto.name, dto.status, dto.model, dto.enrolledDateTime);
        deviceService.enrolDevice(device);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
