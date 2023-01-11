package com.example.EntgraTask.Device;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private DeviceStatus status;

    private String model;

    private LocalDateTime enrolledDateTime;

    public Device() {
    }

    public Device(String name, DeviceStatus status, String model, LocalDateTime enrolledDateTime) {
        this.name = name;
        this.status = status;
        this.model = model;
        this.enrolledDateTime = enrolledDateTime;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", model='" + model + '\'' +
                ", enrolledDateTime=" + enrolledDateTime +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDateTime getEnrolledDateTime() {
        return enrolledDateTime;
    }

    public void setEnrolledDateTime(LocalDateTime enrolledDateTime) {
        this.enrolledDateTime = enrolledDateTime;
    }
}
