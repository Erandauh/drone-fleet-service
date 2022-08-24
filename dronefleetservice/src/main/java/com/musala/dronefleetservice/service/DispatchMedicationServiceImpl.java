package com.musala.dronefleetservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.musala.dronefleetservice.exception.DroneNotReadyException;
import com.musala.dronefleetservice.exception.DroneOverloadedException;
import com.musala.dronefleetservice.exception.EntityNotFoundException;
import com.musala.dronefleetservice.model.Drone;
import com.musala.dronefleetservice.model.Medication;
import com.musala.dronefleetservice.model.State;
import com.musala.dronefleetservice.repository.DroneRepository;
import com.musala.dronefleetservice.repository.MedicationDispatchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DispatchMedicationServiceImpl implements DispatchMedicationService {

    private static final String ERR_MSG_BATT_LOW = "Battery Level is below 25";
    private static final String ERR_MSG_DRONE_OVERLOAD = "Can't carry this load, please reduce medicine Weight";
    private static final String ERR_MSG_DRONE_NOT_FOUND = "Drone not found";

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
            if(!isDroneOverLoaded(droneValue, medications)) {
                medications.forEach(m -> m.setDrone(droneValue));
                droneValue.setState(State.LOADED);
                /*NOTE: assume drone is fully loaded now-real world scenario it will be a
                different case - needs to introduce running-weight field*/
            }

            droneRepository.save(droneValue);
            medicationDispatchRepository.saveAll(medications);
        }

        return medications;
    }

    @Override
    public List<Medication> getMedicationInDrone(String droneSerialNumber) {
        var drone = droneRepository.findById(droneSerialNumber);
        if(drone.isEmpty()){
            throw new EntityNotFoundException(ERR_MSG_DRONE_NOT_FOUND);
        }

        return drone.get().getMedications();
    }

    private boolean isDroneOverLoaded(Drone drone, List<Medication> medicationsToCarry){
        int medicineWeight = medicationsToCarry.stream().mapToInt(Medication::getWeight).sum();
        int droneExistingMedWeight = drone.getMedications().stream().mapToInt(Medication::getWeight).sum();

        if(medicineWeight + droneExistingMedWeight > drone.getWeightLimit()){
            throw new DroneOverloadedException(ERR_MSG_DRONE_OVERLOAD);
        }

        return false;
    }
}
