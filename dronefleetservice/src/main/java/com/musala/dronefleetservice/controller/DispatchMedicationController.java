package com.musala.dronefleetservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musala.dronefleetservice.Application;
import com.musala.dronefleetservice.model.Medication;
import com.musala.dronefleetservice.service.DispatchMedicationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Application.API_V1_URL + DispatchMedicationController.URL)
public class DispatchMedicationController {

    public static final String URL = "/drone/{droneSerialNumber}/medication";

    private final DispatchMedicationService medicationService;

    @PostMapping()
    public List<Medication> loadDrone(@PathVariable String droneSerialNumber,
                                      @Valid @RequestBody List<Medication> medications) {
        return medicationService.loadDrone(droneSerialNumber, medications);
    }

}
