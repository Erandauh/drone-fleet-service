package com.musala.dronefleetservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.dronefleetservice.model.Medication;

public interface MedicationDispatchRepository extends JpaRepository<Medication,String> {
}
