package com.arlley.pack.service;

import com.arlley.pack.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileMapper fileMapper;

    public File saveFile(MultipartFile file){
        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".")+1);
        String newFileName = UUID.randomUUID() + "." + ext;

        return null;
    }
}
