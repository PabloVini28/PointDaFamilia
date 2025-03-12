package com.pointdafamilia.pointdafamilia.fileStorage.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.nio.file.StandardCopyOption;

import com.pointdafamilia.pointdafamilia.fileStorage.dtos.response.FileUploadResponseDto;
import com.pointdafamilia.pointdafamilia.fileStorage.entity.FilePrefix;
import com.pointdafamilia.pointdafamilia.fileStorage.enums.FileType;
import com.pointdafamilia.pointdafamilia.fileStorage.exceptions.EmptyFileException;
import com.pointdafamilia.pointdafamilia.fileStorage.exceptions.FileFormatException;
import com.pointdafamilia.pointdafamilia.fileStorage.exceptions.FileStorageException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
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

    public Path getTargetPath(String folderName, String filename) throws IOException {
        Path directoryPath = storagePath.resolve(folderName).normalize();
        if (!Files.exists(directoryPath)){
            Files.createDirectories(directoryPath);
        }
        return directoryPath.resolve(filename).normalize();
    }

    public String persistImage(FilePrefix prefix, String fileName) throws Exception {
        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")||fileName.endsWith(".png") || fileName.endsWith(".webp")) {
            return persistFile(prefix, fileName);
        }
        throw new FileFormatException("File must be .jpg, .png, or .webp");
    }

    private String persistFile(FilePrefix prefix, String filename) throws Exception {
       try {
           Path sourcePath = getTargetPath("tempfiles", filename);
           validateFileExistence(sourcePath);

           Path destinationPath = getTargetPath(prefix.getDisplayName(), filename);
           Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

           return prefix.getDisplayName() + "/" + filename;
       } catch (FileNotFoundException e) {
           throw e;
       } catch (Exception e) {
           throw new FileStorageException("Failed to persist file.", e);
       }
    }

    public void deleteFile(String filePath) throws Exception {
        try {
            Path sourcePath = storagePath.resolve(filePath);
            validateFileExistence(sourcePath);
            Files.delete(sourcePath);
        } catch (IOException e) {
            throw new FileStorageException("Failed to remove image.", e);
        }
    }

    public UrlResource downloadFile(String filePath) throws Exception {
        Path sourcePath = storagePath.resolve(filePath);
        validateFileExistence(sourcePath);
        try {
            return new UrlResource(sourcePath.toUri());
        } catch (Exception e) {
            throw new FileStorageException("Error while trying to read the file " + filePath);
        }
    }

    public String updateFile(String oldPath, String newFilename, FilePrefix filePrefix) throws Exception {
        try {
            String persisted = persistFile(filePrefix, newFilename);
            deleteFile(oldPath);
            return persisted;
        } catch (RuntimeException e) {
            throw new FileStorageException("Failed while trying to update the image", e);
        }
    }

    public boolean fileExists(String filePath){
        Path sourcePath = storagePath.resolve(filePath);
        return Files.exists(sourcePath);
    }

    private void validateFileExistence(Path filePath) throws Exception{
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("File " + filePath +  " does not exist.");
        }
    }
 
    private String getUniqueFilename(String originalFilename){
        String uuid = UUID.randomUUID().toString();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        return uuid + fileExtension;
    }

}
