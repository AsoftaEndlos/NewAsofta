package com.example.demo.file.service;

import com.example.demo.file.Repository.FileDatabaserepository;
import com.example.demo.file.model.Fileinfomodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

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
    public Optional<Fileinfomodel> UpdateUploadImage(Long id, MultipartFile file) throws IOException {
        Optional<Fileinfomodel> fileinfo=filedatarepo.findById(id);
        if(fileinfo.isPresent())
        {
            Fileinfomodel filedata=fileinfo.get();
            filedata.setImage(file.getBytes());
            filedata.setFileType(file.getContentType());
            filedata.setFileName(file.getOriginalFilename());
            filedatarepo.save(filedata);
        }
        return fileinfo;
    }
}
