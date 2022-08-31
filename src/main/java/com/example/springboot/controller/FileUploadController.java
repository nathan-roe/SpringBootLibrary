package com.example.springboot.controller;

import com.example.springboot.service.UploadService;
import com.example.springboot.utility.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/upload")
public class FileUploadController {
    private final UploadService uploadService;

    public FileUploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file)
            throws FileStorageException, IOException {
        uploadService.uploadFile(file);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/multiple")
    public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files)
            throws FileStorageException, IOException {
        for (MultipartFile file : files) {
            uploadService.uploadFile(file);
        }
        return ResponseEntity.accepted().build();
    }
}
