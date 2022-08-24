package com.musala.dronefleetservice.service;

import java.util.List;


import com.musala.dronefleetservice.model.BatteryHealth;
import com.musala.dronefleetservice.model.Drone;

public interface DroneService {

    Drone register(Drone droneIn);

    List<Drone> checkAvailableDrones();

    BatteryHealth getDroneBatteryStatus(String droneSerialNumber);
}
