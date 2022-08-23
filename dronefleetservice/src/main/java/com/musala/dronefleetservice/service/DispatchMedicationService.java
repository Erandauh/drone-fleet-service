package com.musala.dronefleetservice.service;

import java.util.List;

import com.musala.dronefleetservice.model.Medication;

public interface DispatchMedicationService {

    List<Medication> loadDrone(String droneSerialNumber, List<Medication> medications);

    List<Medication> getMedicationInDrone(String droneSerialNumber);
}
