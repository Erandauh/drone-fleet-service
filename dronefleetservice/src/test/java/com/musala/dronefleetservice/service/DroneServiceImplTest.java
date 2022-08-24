package com.musala.dronefleetservice.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
        Drone expected = Drone.builder()
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
}