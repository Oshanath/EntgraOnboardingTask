package com.example.EntgraTask.Device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public void enrolDevice(Device device){
        Optional<Device> optionalDevice = deviceRepository.findDeviceByName(device.getName());

        if(optionalDevice.isPresent()){
            throw new DeviceAlreadyExistsException("Device " + device.getName() + " already exists.");
        }

        deviceRepository.save(device);
    }

    public void updateStatus(String deviceName, DeviceStatus newStatus){
        Optional<Device> deviceOptional = deviceRepository.findDeviceByName(deviceName);

        if(!deviceOptional.isPresent()){
            throw new DeviceNotFoundException("Device " + deviceName + " not found.");
        }

        Device device = deviceOptional.get();
        device.setStatus(newStatus);
        deviceRepository.save(device);
    }

    public void deleteDevice(String deviceName){

        Optional<Device> deviceOptional = deviceRepository.findDeviceByName(deviceName);

        if(!deviceOptional.isPresent()){
            throw new DeviceNotFoundException("Device " + deviceName + " not found.");
        }

        deviceRepository.delete(deviceOptional.get());

    }
}
