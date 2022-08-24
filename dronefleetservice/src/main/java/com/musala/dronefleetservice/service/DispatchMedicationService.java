package com.musala.dronefleetservice.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.musala.dronefleetservice.model.Medication;

public interface DispatchMedicationService {

    List<Medication> loadDrone(String droneSerialNumber, List<Medication> medications);

    List<Medication> loadDrone(String droneSerialNumber, Medication medication, MultipartFile fileImage);

    List<Medication> getMedicationInDrone(String droneSerialNumber);
}
