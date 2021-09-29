package com.example.demo.file.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileHelper {
    public final static String UPLOAD_DIR=System.getProperty("user.dir")+"/uploadDir"; // change path as your will make the folder .

    public FileHelper() {
    }

    public boolean uploadfile(MultipartFile file)
    {

        boolean f=false;
        try {
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            f=true;
        } catch (IOException e) {
            e.printStackTrace();
        }return  f;
    }

}
