package com.example.miniobucketcreate.Service.ServiceImpl;

import com.example.miniobucketcreate.Model.Image;
import com.example.miniobucketcreate.Repository.ImageRepository;
import com.example.miniobucketcreate.Service.ImageService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ImageServiceImpl implements ImageService {
    private ImageRepository imageRepository;
    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public Image createImage() {
        return null;
    }
}
