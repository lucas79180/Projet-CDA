package org.enchere.backend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageStorageService {

    String storeImage(MultipartFile image) throws IOException;
}
