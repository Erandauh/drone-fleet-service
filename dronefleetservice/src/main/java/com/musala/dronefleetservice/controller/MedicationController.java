package com.musala.dronefleetservice.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.musala.dronefleetservice.Application;
import com.musala.dronefleetservice.service.MedicationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Application.API_V1_URL + MedicationController.URL)
public class MedicationController {

    public static final String URL = "/medication";

    private final MedicationService medicationService;

    @GetMapping(value = "/{medicationCode}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getMedicationImage(@PathVariable String medicationCode) throws IOException {
        return medicationService.getMedicationImage(medicationCode);
    }
}
