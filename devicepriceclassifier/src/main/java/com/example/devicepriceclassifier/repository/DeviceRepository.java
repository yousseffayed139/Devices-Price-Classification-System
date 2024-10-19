package com.example.devicepriceclassifier.repository;

import com.example.devicepriceclassifier.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {

}
