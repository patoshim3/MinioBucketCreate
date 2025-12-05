package com.example.miniobucketcreate.Controller;

import com.example.miniobucketcreate.Model.Image;
import com.example.miniobucketcreate.Service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class ImageController {
    private final ImageService imagesService;
    @GetMapping
    public ResponseEntity<List<Image>> getAllFiles(){
        return ResponseEntity.ok(imagesService.getAllFiles());
    }
}
