package com.endlos.admin.file.controller;

import com.endlos.admin.file.Repository.FileDatabaserepository;
import com.endlos.admin.exception.FileNotFound;
import com.endlos.admin.file.service.FileHelper;
import com.endlos.admin.file.service.FileUploadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController
@RequestMapping("/path")
public class FileController {
    @Autowired
    FileUploadingService fileservice;
    @Autowired
    FileHelper filehelper;
    @Autowired
    FileDatabaserepository filedb;

    @PostMapping(value = "/file")
    public ResponseEntity<String> fileuploading(@RequestParam("file") MultipartFile file) throws IOException {

        System.out.println("Original Image Byte Size - " + file.getBytes().length + " " + file.getContentType());
        if (file.isEmpty()) {
            throw new FileNotFound("Sorry! File filed is empty ");
        }
        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
            System.out.print("hello world");
            boolean f = filehelper.uploadfile(file);
            if (f) {
                //return ResponseEntity.ok("file Succesfully uploaded");
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploaddir").path(file.getOriginalFilename()).toUriString());

            }
            //	 fileservice.UploadData(file);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("some went to wrong ! try Again");
    }
//
//	@GetMapping(value = "/{filename}")
//	public Fileinfomodel getimage(@PathVariable("filename") String filename) {
//		 Optional<Fileinfomodel> retrivedfile = filedb.findByFilename(filename);
//		Fileinfomodel fileinfo = new Fileinfomodel(retrivedfile.get().getFileName(), retrivedfile.get().getFileType(),retrivedfile.get().getImage());
//		return fileinfo;
//	}
//	@GetMapping(value = "/findbyid/{id}")
//	public Optional<Fileinfomodel> getimage(@PathVariable("id") Long id) {
//
//		return filedb.findById(id);
//	}
//	@PutMapping("/updateIcon/{id}")
//	public Optional<Fileinfomodel> UpdateIcon(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
//		return fileservice.UpdateUploadImage(id, file);
//	}
//
//	@GetMapping("/find")
//	public List<Fileinfomodel> findlastid()
//	{
//		return filedb.findByIdDesc();
//	}
}
