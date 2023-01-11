package com.example.EntgraTask.Device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public List<Device> getDevices(){
        return deviceService.getAllDevices();
    }

    @PostMapping
    public void enrolDevice(@RequestBody Device device){
        deviceService.enrolDevice(device);
    }

    @PutMapping
    public void changeDeviceStatus(@RequestBody HashMap<String, Object> jsonObject){
        deviceService.updateStatus((String) jsonObject.get("name"), DeviceStatus.values()[(int)jsonObject.get("newStatus")]);
    }

    @DeleteMapping
    public void deleteDevice(@RequestBody String name){
        deviceService.deleteDevice(name);
    }

}
