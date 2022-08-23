package com.musala.dronefleetservice.service;

import java.util.List;


import com.musala.dronefleetservice.model.Drone;

public interface DroneService {

    Drone register(Drone droneIn);

    List<Drone> checkAvailableDrones();

    int getDroneBatteryStatus();
}
