package com.musala.dronefleetservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.dronefleetservice.model.Drone;
import com.musala.dronefleetservice.model.State;

public interface DroneRepository extends JpaRepository<Drone,String> {

    List<Drone> findDronesByState(State IDLE);
}
