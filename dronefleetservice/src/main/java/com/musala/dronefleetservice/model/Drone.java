package com.musala.dronefleetservice.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drone {

    @Size(max = 200, message = "Serial Number too long")
    private String serialNumber;
    private DroneModel model;
    @Max(value = 500, message = "Weight max limit exceeded")
    private int weightLimit;
    private int batteryCapacity;
    private State state;
}
