package com.musala.dronefleetservice.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.musala.dronefleetservice.Application;
import com.musala.dronefleetservice.controller.DroneController;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    private static String TEST_URL_PATH = Application.API_V1_URL + DroneController.URL + "/availability";

    @Mock
    private DroneController droneController;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(droneController)
                .setControllerAdvice(globalExceptionHandler)
                .build();
    }

    @Test
    public void handleDroneNotReadyException() throws Exception {
        when(droneController.availability())
                .thenThrow(new DroneNotReadyException(""));
        invokeEndpoint(status().isServiceUnavailable());
    }

    @Test
    public void handleDroneOverloadedException() throws Exception {
        when(droneController.availability())
                .thenThrow(new DroneOverloadedException(""));
        invokeEndpoint(status().isServiceUnavailable());
    }

    @Test
    public void handleConflictException() throws Exception {
        when(droneController.availability())
                .thenThrow(new ConflictException(""));
        invokeEndpoint(status().isConflict());
    }

    @Test
    public void handleEntityNotFoundException() throws Exception {
        when(droneController.availability())
                .thenThrow(new EntityNotFoundException(""));
        invokeEndpoint(status().isNotFound());
    }

    private void invokeEndpoint(ResultMatcher resultMatcher) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(TEST_URL_PATH))
                .andDo(print())
                .andExpect(resultMatcher);
    }
}