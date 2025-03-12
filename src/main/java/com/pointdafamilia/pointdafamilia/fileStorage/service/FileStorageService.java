package com.pointdafamilia.pointdafamilia.fileStorage.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.nio.file.StandardCopyOption;

import com.pointdafamilia.pointdafamilia.fileStorage.dtos.response.FileUploadResponseDto;
import com.pointdafamilia.pointdafamilia.fileStorage.enums.FileType;
import com.pointdafamilia.pointdafamilia.fileStorage.exceptions.EmptyFileException;
import com.pointdafamilia.pointdafamilia.fileStorage.exceptions.FileFormatException;
import com.pointdafamilia.pointdafamilia.fileStorage.exceptions.FileStorageException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileStorageService {
    
    private Path storagePath;
    
    public FileStorageService(@Value("${file.storage.path}") String storagePath){
        this.storagePath = Paths.get(storagePath);
    }

    public FileUploadResponseDto storeImageTemporarily(MultipartFile image){
        validateImage(image);
        if(isImage(image)){
            String id = storeFileTemporarily(image);
            return new FileUploadResponseDto(id, FileType.IMAGE);
        }
        throw new FileFormatException("Invalid format! Only images are allowed");
    }

    public void validateImage(MultipartFile file){
        if(file == null || file.isEmpty()){
            throw new EmptyFileException("The uploaded file is empty");
        }
    }

    private boolean isImage(MultipartFile file){
        List<String> imageMimeTypes = List.of("image/jpeg", "image/png", "image/webp");

        String mimeType = file.getContentType();
        return imageMimeTypes.contains(mimeType);
    }

    private String storeFileTemporarily(MultipartFile file) {
        try {
            String filename = getUniqueFilename(file.getOriginalFilename());
            Path targetPath = getTargetPath("tempfiles", filename);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException e) {
            throw new FileStorageException("Failed to store file temporarily.", e);
        }
    }

    private String getUniqueFilename(String originalFilename){
        String uuid = UUID.randomUUID().toString();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        return uuid + fileExtension;
    }

    public Path getTargetPath(String folderName, String filename) throws IOException {
        Path directoryPath = storagePath.resolve(folderName).normalize();
        if (!Files.exists(directoryPath)){
            Files.createDirectories(directoryPath);
        }
        return directoryPath.resolve(filename).normalize();
    }

}
