package com.musala.dronefleetservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.musala.dronefleetservice.Application;
import com.musala.dronefleetservice.service.MedicationService;

@ExtendWith(MockitoExtension.class)
class MedicationControllerTest {

    @Mock
    private MedicationService medicationService;

    @InjectMocks
    private MedicationController medicationController;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(medicationController).build();
    }

    @Test
    void getMedicationImage() throws Exception {
        when(medicationService.getMedicationImage(any())).thenReturn(new byte[0]);

        mvc.perform(MockMvcRequestBuilders.get(Application.API_V1_URL + MedicationController.URL + "/1/image"))
                .andExpect(status().isOk());
    }
}