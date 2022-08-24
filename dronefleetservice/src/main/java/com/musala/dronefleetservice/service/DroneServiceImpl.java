package com.musala.dronefleetservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.musala.dronefleetservice.exception.ConflictException;
import com.musala.dronefleetservice.exception.EntityNotFoundException;
import com.musala.dronefleetservice.model.BatteryHealth;
import com.musala.dronefleetservice.model.Drone;
import com.musala.dronefleetservice.model.State;
import com.musala.dronefleetservice.repository.DroneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {

    private static final String ERR_MSG_DRONE_NOT_FOUND = "Drone not found";
    private static final String ERR_MSG_DRONE_EXISTS = "Drone Already exists";

    private final DroneRepository droneRepository;

    @Override
    public Drone register(Drone droneIn) {
        droneRepository.findById(droneIn.getSerialNumber()).ifPresent(drone -> {
            throw new ConflictException(ERR_MSG_DRONE_EXISTS);
        });
        return droneRepository.save(droneIn);
    }

    @Override
    public List<Drone> checkAvailableDrones() {
        return droneRepository.findDronesByStateAndBatteryCapacityGreaterThan(State.IDLE, 25);
    }

    @Override
    public BatteryHealth getDroneBatteryStatus(String droneSerialNumber) {
        BatteryHealth batteryHealth = new BatteryHealth();
        droneRepository.findById(droneSerialNumber).ifPresentOrElse(d -> {
                    batteryHealth.setHealth(d.getBatteryCapacity());
                },
                () -> {
                    throw new EntityNotFoundException(ERR_MSG_DRONE_NOT_FOUND);
                });

        return batteryHealth;
    }
}
