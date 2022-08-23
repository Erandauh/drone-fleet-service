package com.musala.dronefleetservice.service;

import java.util.List;

import com.musala.dronefleetservice.model.Medication;

public interface MedicationService {

    List<Medication> loadDrone(String droneId, List<Medication> medication);

    List<Medication> getMedicationInDrone(String droneId);
}
