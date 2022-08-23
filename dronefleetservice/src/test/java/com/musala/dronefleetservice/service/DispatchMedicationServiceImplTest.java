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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.musala.dronefleetservice.exception.DroneNotReadyException;
import com.musala.dronefleetservice.model.Drone;
import com.musala.dronefleetservice.model.Medication;
import com.musala.dronefleetservice.repository.DroneRepository;
import com.musala.dronefleetservice.repository.MedicationDispatchRepository;

@ExtendWith(MockitoExtension.class)
class DispatchMedicationServiceImplTest {

    @Mock
    private DroneRepository droneRepository;

    @Mock
    private MedicationDispatchRepository medicationDispatchRepository;

    @InjectMocks
    private DispatchMedicationServiceImpl dispatchMedicationService;

    @Test
    public void canLoadDroneWithMedicine() {

        var drone = Optional.of(mock(Drone.class));
        var medicationsToLoad = List.of(Medication.builder().code("M1").build());

        when(droneRepository.findById(any())).thenReturn(drone);

        var result = dispatchMedicationService.loadDrone(drone.get().getSerialNumber(), medicationsToLoad);

        assertThat(result).containsAll(medicationsToLoad);
        verify(medicationDispatchRepository, times(1)).saveAll(medicationsToLoad);
    }

    @Test
    public void shouldThrowAnErrorIfDroneBatteryLessThan25Percent(){

        var drone = Optional.of(mock(Drone.class));
        var medicationsToLoad = List.of(mock(Medication.class));

        when(drone.get().getBatteryCapacity()).thenReturn(10);
        when(droneRepository.findById(any())).thenReturn(drone);

        Exception exception = assertThrows(DroneNotReadyException.class, () -> {
            dispatchMedicationService.loadDrone(drone.get().getSerialNumber(), medicationsToLoad);
        });

        assertThat(exception.getMessage()).isEqualToIgnoringCase("Battery Level is below 25");
    }
}