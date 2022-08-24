package com.musala.dronefleetservice.service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {

    @Override
    public String store(MultipartFile multipartFile) {
        // For the application purpose saved to a temp dir - ideally this has to be reliable storage as AWS s3 etc
        String tmpdir = System.getProperty("java.io.tmpdir");
        Path filepath = Paths.get(tmpdir, multipartFile.getOriginalFilename());

        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filepath.toString();
    }
}
