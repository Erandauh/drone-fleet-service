package com.musala.dronefleetservice.scheduler;

import java.sql.Timestamp;
import java.util.HashMap;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.musala.dronefleetservice.model.AuditEntry;
import com.musala.dronefleetservice.repository.AuditEntryRepository;
import com.musala.dronefleetservice.repository.DroneRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Runs periodically and check the drone health
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DroneHealthChecker {

    private static final String AUDIT_LOG_ENTRY = "AUDIT LOG ENTRY : {%s}";

    private final DroneRepository droneRepository;
    private final AuditEntryRepository auditEntryRepository;
    private final HashMap<String, Integer> auditMemento = new HashMap();

    @Scheduled(fixedDelayString = "${drone.scheduler.fixedDelay}")
    public void updateHealthStatus() {

        droneRepository.findAll().forEach(drone -> {
            var timeStamp = new Timestamp(System.currentTimeMillis());
            var entry = AuditEntry.builder()
                    .timestamp(timeStamp)
                    .droneSerialNumber(drone.getSerialNumber())
                    .batteryHealth(drone.getBatteryCapacity())
                    .build();

            // Add audit entry only if battery health is changed otherwise preserve in memory
            if (auditMemento.containsKey(drone.getSerialNumber())) {
                if(auditMemento.get(drone.getSerialNumber()) != drone.getBatteryCapacity()) {
                    auditEntryRepository.save(entry);
                }
            }
            else {
                auditEntryRepository.save(entry);
            }
            auditMemento.put(drone.getSerialNumber(), drone.getBatteryCapacity());
            log.info(String.format(AUDIT_LOG_ENTRY, entry.toString()));
        });
    }
}
