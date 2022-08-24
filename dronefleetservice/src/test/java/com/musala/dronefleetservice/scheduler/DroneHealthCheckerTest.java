package com.musala.dronefleetservice.scheduler;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.musala.dronefleetservice.model.Drone;
import com.musala.dronefleetservice.repository.AuditEntryRepository;
import com.musala.dronefleetservice.repository.DroneRepository;

@ExtendWith(MockitoExtension.class)
class DroneHealthCheckerTest {


    @Mock
    private DroneRepository droneRepository;

    @Mock
    private AuditEntryRepository auditEntryRepository;

    @InjectMocks
    private DroneHealthChecker droneHealthChecker;

    @Test
    void canUpdateHealthStatus() {

        var drones = List.of(Drone.builder().serialNumber("1").batteryCapacity(100).build(),
                Drone.builder().serialNumber("2").batteryCapacity(90).build());

        when(droneRepository.findAll()).thenReturn(drones);

        droneHealthChecker.updateHealthStatus();

        verify(auditEntryRepository, times(drones.size())).save(any());
    }

    @Test
    void ifBatteryHealthWasNotChangedStatusPreservesInMemory() {

        var drone = Drone.builder().serialNumber("1").batteryCapacity(100).build();
        var drones = List.of(drone);

        when(droneRepository.findAll()).thenReturn(drones);

        droneHealthChecker.updateHealthStatus();
        droneHealthChecker.updateHealthStatus();

        verify(auditEntryRepository, times(1)).save(any());
    }

    @Test
    void auditEntryIsAddedOnlyIfBatteryHealthChanged() {

        var drone = Drone.builder().serialNumber("1").batteryCapacity(100).build();
        var drones = List.of(drone);

        when(droneRepository.findAll()).thenReturn(drones);

        droneHealthChecker.updateHealthStatus();
        droneHealthChecker.updateHealthStatus();

        drone.setBatteryCapacity(70);
        droneHealthChecker.updateHealthStatus();
        verify(auditEntryRepository, times(2)).save(any());
    }
}