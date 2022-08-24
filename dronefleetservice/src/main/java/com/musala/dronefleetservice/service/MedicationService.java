package com.musala.dronefleetservice.service;

import java.io.IOException;

public interface MedicationService {

    byte[] getMedicationImage(String medicationCode) throws IOException;
}
