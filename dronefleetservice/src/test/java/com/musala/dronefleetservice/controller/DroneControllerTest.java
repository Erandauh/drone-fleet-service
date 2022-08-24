package com.musala.dronefleetservice.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.dronefleetservice.Application;
import com.musala.dronefleetservice.model.BatteryHealth;
import com.musala.dronefleetservice.model.Drone;
import com.musala.dronefleetservice.model.DroneModel;
import com.musala.dronefleetservice.model.State;
import com.musala.dronefleetservice.service.DroneService;

@ExtendWith(MockitoExtension.class)
class DroneControllerTest {

    @Mock
    private DroneService droneService;

    @InjectMocks
    private DroneController droneController;

    private MockMvc mvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(droneController).build();
    }

    @Test
    void register() throws Exception {
        var drone = Drone.builder()
                .serialNumber("t1")
                .model(DroneModel.Cruiserweight)
                .weightLimit(500)
                .batteryCapacity(100)
                .state(State.IDLE)
                .build();

        when(droneService.register(any())).thenReturn(drone);

        mvc.perform(MockMvcRequestBuilders.post(Application.API_V1_URL + DroneController.URL + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper().writeValueAsString(drone))).andExpect(status()
                .isOk())
                .andExpect(jsonPath("serialNumber").value(drone.getSerialNumber()));
    }

    @Test
    void availability() throws Exception {
        var drones = List.of(Drone.builder()
                .serialNumber("t1")
                .model(DroneModel.Cruiserweight)
                .weightLimit(500)
                .batteryCapacity(100)
                .state(State.IDLE)
                .build());

        when(droneService.checkAvailableDrones()).thenReturn(drones);

        mvc.perform(MockMvcRequestBuilders.get(Application.API_V1_URL + DroneController.URL + "/availability")
        ).andExpect(status().isOk());
    }

    @Test
    void batteryHealth() throws Exception {
        var battHealth = BatteryHealth.builder()
                .health(100)
                .build();

        when(droneService.getDroneBatteryStatus(any())).thenReturn(battHealth);

        mvc.perform(MockMvcRequestBuilders.get(Application.API_V1_URL + DroneController.URL + "/drone_serial/battery")
        ).andExpect(status().isOk());
    }

    private ObjectMapper objectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }
}