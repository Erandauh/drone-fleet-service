package com.musala.dronefleetservice.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.musala.dronefleetservice.model.Medication;
import com.musala.dronefleetservice.repository.MedicationDispatchRepository;

@ExtendWith(MockitoExtension.class)
class MedicationServiceImplTest {

    @Mock
    private MedicationDispatchRepository medicationDispatchRepository;

    @InjectMocks
    private MedicationServiceImpl medicationService;

    @Test
    void getMedicationImage() throws IOException {

        var tempImageFile = createTempFile();

        var medication = Medication.builder()
                .code("M1")
                .weight(1000)
                .imagePath(tempImageFile.getPath())
                .build();

        when(medicationDispatchRepository.findById(any())).thenReturn(Optional.of(medication));

        var result = medicationService.getMedicationImage(medication.getCode());

        assertThat(result).isNotEmpty();
    }

    @Test
    void returnsEmptyByteStreamIfMedicationImageWasNotFound() throws IOException {

        var medication = Medication.builder()
                .code("M1")
                .weight(1000)
                .build();

        when(medicationDispatchRepository.findById(any())).thenReturn(Optional.of(medication));

        var result = medicationService.getMedicationImage(medication.getCode());

        assertThat(result).isEmpty();
    }

    private File createTempFile() throws IOException {
        File file = File.createTempFile("image", "-file.jpg");
        Files.write(file.toPath(), "89 78 78 22".getBytes());
        file.deleteOnExit();

        return file;
    }
}