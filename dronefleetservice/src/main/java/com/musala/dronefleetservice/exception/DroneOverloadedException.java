package com.musala.dronefleetservice.exception;

public class DroneOverloadedException extends RuntimeException {

    public DroneOverloadedException(String message) {
        super(message);
    }
}
