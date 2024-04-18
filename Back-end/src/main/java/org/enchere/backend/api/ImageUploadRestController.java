package org.enchere.backend.api;

import org.enchere.backend.service.ImageStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/upload-image")
public class ImageUploadRestController {

    @Autowired
    private ImageStorageServiceImpl imageStorageService;

    @PostMapping
    public String uploadImage(@RequestParam("image") MultipartFile image) {
        System.out.println("--LOG-- exec uploadImage");
        try {
            String imgUrl = imageStorageService.storeImage(image);
            System.out.println("--LOG-- imgUrl = " + imgUrl);
            return imgUrl;
        } catch (IOException e) {
            System.out.println("Erreur");
            return null;
        }
    }
}
