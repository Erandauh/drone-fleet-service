package com.musala.dronefleetservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MEDICATION_DISPATCH")
public class Medication {

    @Id
    @Pattern(regexp="^[A-Z0-9_]*$", message = "Invalid Medication Code")
    @Column(name = "CODE")
    private String code;

    @Pattern(regexp="^[a-zA-Z0-9_-]*$", message = "Invalid Medication Name")
    @Column(name = "NAME")
    private String name;

    @Column(name = "WEIGHT")
    private int weight;

    @JsonIgnore
    @Column(name = "IMAGEPATH")
    private String imagePath;

    @Column(name = "IMAGEURL")
    private String imageURL;

    @ManyToOne
    @JoinColumn(name = "SERIALNUMBER")
    @JsonIgnore
    private Drone drone;
}
