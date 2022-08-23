package com.musala.dronefleetservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.musala.dronefleetservice.exception.DroneNotReadyException;
import com.musala.dronefleetservice.model.Drone;
import com.musala.dronefleetservice.model.Medication;
import com.musala.dronefleetservice.repository.DroneRepository;
import com.musala.dronefleetservice.repository.MedicationDispatchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DispatchMedicationServiceImpl implements DispatchMedicationService {

    private static final String ERR_MSG_BATT_LOW = "Battery Level is below 25";

    private final DroneRepository droneRepository;
    private final MedicationDispatchRepository medicationDispatchRepository;

    @Override
    public List<Medication> loadDrone(String droneSerialNumber, List<Medication> medications) {

        var drone = droneRepository.findById(droneSerialNumber);
        if (drone.isPresent()) {
            var droneValue = drone.get();

            if(droneValue.getBatteryCapacity() < 25){
                throw new DroneNotReadyException(ERR_MSG_BATT_LOW);
            }

            medications.forEach(m -> m.setDrone(droneValue));
        }
        medicationDispatchRepository.saveAll(medications);

        return medications;
    }

    @Override
    public List<Medication> getMedicationInDrone(String droneSerialNumber) {
        return null;
    }
}
