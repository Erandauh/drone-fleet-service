package com.musala.dronefleetservice.model;

import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class Medication {

    @Pattern(regexp="^[A-Z0-9_-]*$", message = "Invalid Medication Name")
    private String name;
    private int weight;
    @Pattern(regexp="^[A-Z0-9_]*$", message = "Invalid Medication Code")
    private String code;
    private String image;
}
