package com.example.demo.fileservice;

import com.example.demo.filerepository.FileDatabaserepository;
import com.example.demo.fileuploading.Fileinfomodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileUploadingService {
    @Autowired
    FileDatabaserepository filedatarepo;

    public void UploadData(MultipartFile file) throws IOException {
        Fileinfomodel filedata=new Fileinfomodel();
        filedata.setImage(file.getBytes());
        filedata.setFileType(file.getContentType());
        filedata.setFileName(file.getOriginalFilename());
        filedatarepo.save(filedata);

    }
}
