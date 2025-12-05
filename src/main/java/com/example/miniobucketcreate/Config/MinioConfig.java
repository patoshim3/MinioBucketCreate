package com.example.miniobucketcreate.Config;

import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.context.annotation.Configuration;
import io.minio.MinioClient;

@Configuration
public class MinioConfig {

    @Value('${minio.bucket}')
    private String bucketName;

    @Value('${minio.url}')
    public String minioUrl;




}
