package com.rbc.stockmarket.controller;

import com.rbc.stockmarket.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/file")
@RestController
public class FileController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public void upload(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
    }
}
