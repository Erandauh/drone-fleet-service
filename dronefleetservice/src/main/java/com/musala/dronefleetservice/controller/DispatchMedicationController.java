package com.musala.dronefleetservice.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.musala.dronefleetservice.Application;
import com.musala.dronefleetservice.model.Medication;
import com.musala.dronefleetservice.service.DispatchMedicationService;
import com.musala.dronefleetservice.service.MedicationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Application.API_V1_URL + DispatchMedicationController.URL)
public class DispatchMedicationController {

    public static final String URL = "/drone/{droneSerialNumber}/medication";

    private final DispatchMedicationService dispatchMedicationService;

    @PostMapping()
    public List<Medication> loadDrone(@PathVariable String droneSerialNumber,
                                      @Valid @RequestBody List<Medication> medications) {
        return dispatchMedicationService.loadDrone(droneSerialNumber, medications);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Medication> loadDrone(@PathVariable String droneSerialNumber,
                                      @Valid @RequestPart("metadata") Medication medication,
                                      @RequestPart("content") MultipartFile imageFile
    ) {
        return dispatchMedicationService.loadDrone(droneSerialNumber, medication, imageFile);
    }

    @GetMapping
    public List<Medication> getMedications(@PathVariable String droneSerialNumber) {
        return dispatchMedicationService.getMedicationInDrone(droneSerialNumber);
    }
}
