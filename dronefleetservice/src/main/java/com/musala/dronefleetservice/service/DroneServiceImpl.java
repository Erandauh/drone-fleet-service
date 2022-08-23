package com.musala.dronefleetservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.musala.dronefleetservice.model.Drone;
import com.musala.dronefleetservice.repository.DroneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    @Override
    public Drone register(Drone droneIn) {
        return droneRepository.save(droneIn);
    }

    @Override
    public List<Drone> checkAvailableDrones() {
        return null;
    }

    @Override
    public int getDroneBatteryStatus() {
        return 0;
    }
}
