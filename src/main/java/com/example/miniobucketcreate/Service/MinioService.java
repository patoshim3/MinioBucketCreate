package com.example.miniobucketcreate.Service;

import com.example.miniobucketcreate.Config.MinioConfig;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.stereotype.Service;
import io.minio.MinioClient;

import java.io.File;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MinioService {
    private MinioConfig minioConfig;

    private void createBucket(File fileJson){
        MinioClient.builder().endpoint(minioConfig.minioUrl);
    }





}
