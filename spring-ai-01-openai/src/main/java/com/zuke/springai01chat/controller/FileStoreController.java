package com.zuke.springai01chat.controller;

import com.zuke.springai01chat.service.FileStoreService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/file")
@RestController
public class FileStoreController {
    @Resource
    private FileStoreService fileStoreService;

    public FileStoreController(FileStoreService fileStoreService) {
        this.fileStoreService = fileStoreService;
    }

    @PostMapping("/upload")
    public Object uploadFile(MultipartFile file){
        return fileStoreService.saveFile(file);
    }
}
