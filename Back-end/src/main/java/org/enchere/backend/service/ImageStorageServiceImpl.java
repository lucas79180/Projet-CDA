package org.enchere.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {

    @Value("${image.upload.directory}")
    private String uploadDirectory;

    public String storeImage(MultipartFile image) throws IOException {
        // Générer un nom de fichier unique
        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

        // Construire le chemin complet pour stocker l'image
        String filePath = uploadDirectory + File.separator + fileName;

        // Créer le fichier sur le système de fichiers
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Créer les répertoires parent si nécessaire
        file.createNewFile();

        // Copier les données de l'image dans le fichier
        try (FileOutputStream fos = new FileOutputStream(file)) {
            FileCopyUtils.copy(image.getInputStream(), fos);
        }

        // Retourner l'URL de l'image (vous pouvez adapter cela selon votre structure d'URL)
        return "/images/" + fileName;
    }
}