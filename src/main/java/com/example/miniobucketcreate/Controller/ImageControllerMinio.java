package com.example.miniobucketcreate.Controller;

import com.example.miniobucketcreate.Service.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class ImageControllerMinio {

    private final MinioService minioService;

    @PostMapping("/upload/txt")
    public ResponseEntity<String> uploadTxt(@RequestParam MultipartFile file) {
        String message = minioService.uploadTxt(file);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/upload/png")
    public ResponseEntity<String> uploadPng(@RequestParam MultipartFile file) {
        String message = minioService.uploadPng(file);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/upload/json")
    public ResponseEntity<String> uploadJson(@RequestParam MultipartFile file) {
        String message = minioService.uploadJson(file);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> download(@RequestParam String fileName) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(minioService.download(fileName));
    }
}
