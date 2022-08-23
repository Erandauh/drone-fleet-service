package com.musala.dronefleetservice.exception;

public class DroneNotReadyException extends RuntimeException {

    public DroneNotReadyException(String message) {
        super(message);
    }
}
