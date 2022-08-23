package com.musala.dronefleetservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.dronefleetservice.model.Drone;

public interface DroneRepository extends JpaRepository<Drone,String> {

}
