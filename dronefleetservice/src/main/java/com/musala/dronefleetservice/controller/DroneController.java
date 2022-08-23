package com.musala.dronefleetservice.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musala.dronefleetservice.Application;
import com.musala.dronefleetservice.model.Drone;
import com.musala.dronefleetservice.service.DroneService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Application.API_V1_URL + DroneController.URL)
public class DroneController {
    public static final String URL = "/drone";

    private final DroneService droneService;

    @PostMapping("/register")
    public Drone register(@Valid @RequestBody Drone drone) {
        return droneService.register(drone);
    }
}
