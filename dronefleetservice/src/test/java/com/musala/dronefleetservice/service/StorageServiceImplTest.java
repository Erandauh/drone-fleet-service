package com.musala.dronefleetservice.service;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StorageServiceImplTest {

    @InjectMocks
    private StorageServiceImpl storageService;

    @Test
    void canStoreImageFile() throws IOException {

        var file = mock(MultipartFile.class);
        var testFilename = "test.jpg";

        when(file.getOriginalFilename()).thenReturn(testFilename);
        when(file.getBytes()).thenReturn(new byte[0]);

        var result = storageService.store(file);

        assertThat(result).endsWith(testFilename);
    }
}