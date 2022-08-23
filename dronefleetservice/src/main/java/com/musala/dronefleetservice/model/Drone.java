package com.musala.dronefleetservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Drone {

    @Id
    @Size(max = 100, message = "Serial Number too long")
    @Column(name = "SERIALNUMBER")
    private String serialNumber;
    @Column
    @Enumerated(EnumType.STRING)
    private DroneModel model;
    @Max(value = 500, message = "Weight max limit exceeded")
    @Column(name = "WEIGHTLIMIT")
    private int weightLimit;
    @Column(name = "BATTERYCAPACITY")
    private int batteryCapacity;
    @Column
    @Enumerated(EnumType.STRING)
    private State state;
}
