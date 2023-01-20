package com.example.entgratask.services;

import com.example.entgratask.device.Device;
import com.example.entgratask.device.DeviceRepository;
import com.example.entgratask.device.DeviceStatus;
import com.example.entgratask.exceptions.DeviceAlreadyExistsException;
import com.example.entgratask.exceptions.DeviceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    Logger logger;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        logger = LoggerFactory.getLogger(DeviceService.class);
        logger.info("Device service created.");
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getAllDevices() {
        logger.info("Getting all devices from database.");
        return deviceRepository.findAll();
    }

    public void enrolDevice(Device device){
        logger.info("Enrolling new device.");
        Optional<Device> optionalDevice = deviceRepository.findDeviceByName(device.getName());

        if(optionalDevice.isPresent()){
            logger.info("Device " + device.getName() + " already exists.");
            throw new DeviceAlreadyExistsException("Device " + device.getName() + " already exists.");
        }

        deviceRepository.save(device);
        logger.info("Device " + device.getName() + " enrolled.");
    }

    public void updateStatus(String deviceName, DeviceStatus newStatus){
        logger.info("Updating device status.");
        Optional<Device> deviceOptional = deviceRepository.findDeviceByName(deviceName);

        if(!deviceOptional.isPresent()){
            logger.info("Device " + deviceName + " not found.");
            throw new DeviceNotFoundException("Device " + deviceName + " not found.");
        }

        Device device = deviceOptional.get();
        device.setStatus(newStatus);
        deviceRepository.save(device);
        logger.info("Device status changed.");
    }

    public void deleteDevice(String deviceName){
        logger.info("Deleting device.");
        Optional<Device> deviceOptional = deviceRepository.findDeviceByName(deviceName);

        if(!deviceOptional.isPresent()){
            logger.info("Device " + deviceName + " not found.");
            throw new DeviceNotFoundException("Device " + deviceName + " not found.");
        }

        deviceRepository.delete(deviceOptional.get());
        logger.info("Device deleted.");
    }
}
