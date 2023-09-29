package com.khun.studentregistration.service.impl;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig
public class FileService {
    private final MultipartFile filePart;
    private final String uploadPath;
    private final String fileName;


    public FileService(MultipartFile filePart, String uploadPath) {
        this.filePart = filePart;
        this.uploadPath = uploadPath;
        this.fileName = filePart != null ? filePart.getOriginalFilename() : null;
    }

    public String uploadFile(String basePath) throws IOException {
        if (filePart == null) {
            return null;
        }

        // Generate a unique file name to avoid conflicts
        String uniqueFileName = generateUniqueFileName();
        String filePath = uploadPath + File.separator + uniqueFileName;

        // Save the file to the specified location on the server
        try (var inputStream = filePart.getInputStream(); var outputStream = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return basePath+uniqueFileName;
        } catch (IOException e) {
            // Handle the exception (e.g., log or rethrow)
            e.printStackTrace();
            throw e;
        }

        // Return the file URL or path

    }

    private String generateUniqueFileName() {
        // Generate a unique file name using a UUID and the original file name
        String uuid = UUID.randomUUID().toString();
        return uuid + "_" + fileName;
    }
}
