package com.musala.dronefleetservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.musala.dronefleetservice.model.Medication;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService{

    @Override
    public List<Medication> loadDrone(String droneId, List<Medication> medication) {
        return null;
    }

    @Override
    public List<Medication> getMedicationInDrone(String droneId) {
        return null;
    }
}
