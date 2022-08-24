package com.musala.dronefleetservice.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditEntry {

    @Id
    @Column(name ="TIMESTAMP")
    private Timestamp timestamp;

    @Column(name = "DRONESERIALNUMBER")
    private String droneSerialNumber;

    @Column(name = "BATTERYHEALTH")
    private int batteryHealth;

    @Override
    public String toString() {
        return "AuditEntry{" +
                "timestamp=" + timestamp +
                ", droneSerialNumber='" + droneSerialNumber + '\'' +
                ", batteryHealth=" + batteryHealth +
                '}';
    }
}
