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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.dronefleetservice.Application;
import com.musala.dronefleetservice.model.Medication;
import com.musala.dronefleetservice.service.DispatchMedicationService;

@ExtendWith(MockitoExtension.class)
class DispatchMedicationControllerTest {

    @Mock
    private DispatchMedicationService medicationService;

    @InjectMocks
    private DispatchMedicationController dispatchMedicationController;

    private MockMvc mvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(dispatchMedicationController).build();
    }

    @Test
    void loadDrone() throws Exception {
        var medicationsToLoad = List.of(Medication.builder().code("M1").build());

        when(medicationService.loadDrone(any(), any())).thenReturn(medicationsToLoad);

        mvc.perform(MockMvcRequestBuilders.post(Application.API_V1_URL + "/drone/1234/medication")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper().writeValueAsString(medicationsToLoad))).andExpect(status()
                .isOk());

    }

    @Test
    void getMedications() throws Exception {
        var medicationsToLoad = List.of(Medication.builder().code("M1").build());

        when(medicationService.getMedicationInDrone(any())).thenReturn(medicationsToLoad);

        mvc.perform(MockMvcRequestBuilders.get(Application.API_V1_URL + "/drone/1234/medication")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                .isOk());

    }

    private ObjectMapper objectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }
}