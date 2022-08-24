package com.musala.dronefleetservice.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.musala.dronefleetservice.exception.ConflictException;
import com.musala.dronefleetservice.exception.DroneOverloadedException;
import com.musala.dronefleetservice.exception.EntityNotFoundException;
import com.musala.dronefleetservice.model.Drone;
import com.musala.dronefleetservice.model.DroneModel;
import com.musala.dronefleetservice.model.State;
import com.musala.dronefleetservice.repository.DroneRepository;

@ExtendWith(MockitoExtension.class)
class DroneServiceImplTest {

    @Mock
    private DroneRepository droneRepository;

    @InjectMocks
    private DroneServiceImpl droneService;

    @Test
    public void canRegisterADrone() {
        var expected = Drone.builder()
                .serialNumber("t1")
                .model(DroneModel.Cruiserweight)
                .weightLimit(500)
                .batteryCapacity(100)
                .state(State.IDLE)
                .build();

        when(droneRepository.save(any(Drone.class))).thenReturn(expected);

        var result = droneService.register(expected);

        assertThat(result).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void throwsConflictExceptionWhenDroneAlreadyExists() {
        var drone = mock(Drone.class);

        when(droneRepository.findById(any())).thenReturn(Optional.of(drone));

        Exception exception = assertThrows(ConflictException.class, () -> {
            droneService.register(drone);
        });
        assertThat(exception.getMessage()).isEqualToIgnoringCase("Drone Already exists");
    }

    @Test
    public void canCheckForAvailableDronesForFlying() {

        var drones = List.of(Drone.builder().serialNumber("1").batteryCapacity(100).state(State.IDLE).build(),
                Drone.builder().serialNumber("2").batteryCapacity(20).state(State.IDLE).build(),
                Drone.builder().serialNumber("2").batteryCapacity(100).state(State.DELIVERING).build());

        var dronesEligibleToFly = List.of(drones.get(0));

        when(droneRepository.findDronesByStateAndBatteryCapacityGreaterThan(State.IDLE, 25))
                .thenReturn(dronesEligibleToFly);

        var result = droneService.checkAvailableDrones();

        assertThat(result).containsExactlyElementsOf(dronesEligibleToFly);
    }

    @Test
    public void canGetBatteryHealthOfADrone() {

        Drone drone = Drone.builder()
                .serialNumber("t1")
                .batteryCapacity(100)
                .build();

        when(droneRepository.findById(any())).thenReturn(Optional.of(drone));

        var result = droneService.getDroneBatteryStatus(drone.getSerialNumber());

        assertThat(result.getHealth()).isEqualTo(drone.getBatteryCapacity());
    }

    @Test
    public void throwsNotFoundExceptionWhenDroneIsNotFound() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            droneService.getDroneBatteryStatus("invalid_drone_id");
        });
        assertThat(exception.getMessage()).isEqualToIgnoringCase("Drone not found");
    }
}