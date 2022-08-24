package com.musala.dronefleetservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String store(MultipartFile multipartFile);
}
