package com.example.devicepriceclassifier.service;

import com.example.devicepriceclassifier.exception.DeviceAlreadyExistsException;
import com.example.devicepriceclassifier.model.Device;
import com.example.devicepriceclassifier.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private RestTemplate restTemplate; // For calling the Python API

    public Map<String, Object> convertDeviceToFeatures(Device device) {
        Map<String, Object> features = new HashMap<>();

        features.put("battery_power", device.getBatteryPower());
        features.put("blue", device.isHasBluetooth() ? 1 : 0); // reconverting boolean features to (0,1)
        features.put("clock_speed", device.getClockSpeed());
        features.put("dual_sim", device.isHasDualSim() ? 1 : 0);
        features.put("fc", device.getFrontCamera());
        features.put("four_g", device.isHas4G() ? 1 : 0);
        features.put("int_memory", device.getInternalMemory());
        features.put("m_dep", device.getMobileDepth());
        features.put("mobile_wt", device.getMobileWeight());
        features.put("n_cores", device.getNumberOfCores());
        features.put("pc", device.getPrimaryCamera());
        features.put("px_height", device.getPixelHeight());
        features.put("px_width", device.getPixelWidth());
        features.put("ram", device.getRam());
        features.put("sc_h", device.getScreenHeight());
        features.put("sc_w", device.getScreenWidth());
        features.put("talk_time", device.getTalkTime());
        features.put("three_g", device.isHas3G() ? 1 : 0);
        features.put("touch_screen", device.isHasTouchScreen() ? 1 : 0);
        features.put("wifi", device.isHasWifi() ? 1 : 0);

        return features;
    }
    // Retrieve a list of all devices
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    // Retrieve details of a specific device by ID
    public Device getDeviceById(Long id) {
        Optional<Device> device = deviceRepository.findById(id);
        return device.orElse(null); // Return null or handle not found
    }

    // Add a new device
    @Transactional
    public Device addDevice(Device device) {
        // Check if the device ID already exists in the database
        if (deviceRepository.existsById(device.getId())) {
            // If the device already exists, throw a custom exception
            throw new DeviceAlreadyExistsException("Device with ID " + device.getId() + " already exists.");
        } else {
            // If the device does not exist, save and return the new device
            return deviceRepository.save(device);
        }
    }

    // Call Python API to predict the price
    @Transactional
    public Device predictPrice(Long deviceId) {
        Device device = getDeviceById(deviceId);
        if (device != null) {
            if (device.getPredictedPrice() != null) {
                // If predicted price is already set, return the device from the database
                return device;
            }
            // Convert the device to feature map
            Map<String, Object> features = convertDeviceToFeatures(device);

            // Call your Python API endpoint here
            String pythonApiUrl = "http://127.0.0.1:5000/predict"; // Replace with your actual Python API URL

            // Set up headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create an HttpEntity with the features and headers
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(features, headers);

            // Use postForObject with the correct method signature
            Integer predictedPrice = restTemplate.postForObject(pythonApiUrl, entity, Integer.class);

            if (predictedPrice != null) {
                device.setPredictedPrice(predictedPrice);
                deviceRepository.save(device); // Save the updated device entity
            }
        }
        return device; // Return the updated device
    }
}
