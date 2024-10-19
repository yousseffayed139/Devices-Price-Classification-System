package com.example.devicepriceclassifier.controller;
import com.example.devicepriceclassifier.model.Device;
import com.example.devicepriceclassifier.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api") // Base URL
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    // Retrieve a list of all devices
    @GetMapping("/devices")
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }

    // Retrieve details of a specific device by ID
    @GetMapping("/devices/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        Device device = deviceService.getDeviceById(id);
        return ResponseEntity.ok(device);
    }

    // Add a new device
    @PostMapping("/devices")
    public ResponseEntity<Device> addDevice(@RequestBody Device device) {
        Device savedDevice = deviceService.addDevice(device);
        return ResponseEntity.ok(savedDevice);
    }

    // Call Python API to predict the price
    @PostMapping("/predict/{deviceId}") // Changed this line for the new URL
    public ResponseEntity<Device> predictPrice(@PathVariable Long deviceId) {
        Device updatedDevice = deviceService.predictPrice(deviceId);
        return ResponseEntity.ok(updatedDevice);
    }
}
