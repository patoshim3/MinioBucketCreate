package com.example.miniobucketcreate.Service.ServiceImpl;

import com.example.miniobucketcreate.Model.Image;
import com.example.miniobucketcreate.Repository.ImageRepository;
import com.example.miniobucketcreate.Service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public List<Image> getAllFiles(){
        return imageRepository.findAll();
    }
}
