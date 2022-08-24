package com.musala.dronefleetservice.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.musala.dronefleetservice.repository.MedicationDispatchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private final MedicationDispatchRepository medicationDispatchRepository;

    @Override
    public byte[] getMedicationImage(String medicationCode) throws IOException {
        var medication = medicationDispatchRepository.findById(medicationCode);
        if (medication.isPresent() && medication.get().getImagePath() != null) {
            var medicationVal = medication.get();
            return Files.readAllBytes(Paths.get(medicationVal.getImagePath()));
        }

        return new byte[0];
    }
}
