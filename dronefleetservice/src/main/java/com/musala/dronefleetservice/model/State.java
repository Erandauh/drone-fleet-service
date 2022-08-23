package com.musala.dronefleetservice.model;

/**
 * Represents Drone states
 */
public enum State {
    IDLE,
    LOADING,
    LOADED,
    DELIVERING,
    DELIVERED,
    RETURNING
}
