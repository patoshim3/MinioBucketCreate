package com.example.miniobucketcreate.Service;

import com.example.miniobucketcreate.Model.Image;
import com.example.miniobucketcreate.Repository.ImageRepository;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;
    private final ImageRepository imageRepository;

    @Value("${minio.bucket}")
    private String bucket;


    private void createBucketIfNotExists() {
        try {
            boolean exists = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(bucket).build()
            );

            if (!exists) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder().bucket(bucket).build()
                );
            }
        } catch (Exception ignored) {}
    }

    private String uploadFile(MultipartFile file, String objectName) {
        try {
            createBucketIfNotExists();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(objectName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            Image image = new Image();
            image.setName(objectName);
            image.setSize(file.getSize());
            image.setUploadTime(LocalDateTime.now());
            image.setOriginalFileName(file.getOriginalFilename());
            image.setContentType(file.getContentType());
            imageRepository.save(image);

            return "uploaded";

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String uploadTxt(MultipartFile file) {
        if (!file.getOriginalFilename()
                .endsWith(".txt")) return "only txt allowed";
        return uploadFile(file, file.getOriginalFilename());
    }

    public String uploadPng(MultipartFile file) {
        if (!file.getOriginalFilename()
                .endsWith(".png")) return "only png allowed";
        return uploadFile(file, file.getOriginalFilename());
    }

    public String uploadJson(MultipartFile file) {
        if (!file.getOriginalFilename()
                .endsWith(".json")) return "only json allowed";
        return uploadFile(file, file.getOriginalFilename());
    }

    public ByteArrayResource download(String fileName) {
        try {
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(fileName)
                            .build()
            );
            byte[] bytes = stream.readAllBytes();
            stream.close();
            return new ByteArrayResource(bytes);
        } catch (Exception e) {
            return null;
        }
    }



}
