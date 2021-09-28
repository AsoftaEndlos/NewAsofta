package com.example.demo.filecontroller;

import java.awt.PageAttributes.MediaType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import com.example.demo.fileservice.FileUploadingService;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.exception.FileNotFound;
import com.example.demo.exception.FileStorageException;
import com.example.demo.filerepository.FileDatabaserepository;
import com.example.demo.fileuploading.Fileinfomodel;

@RestController
@RequestMapping("/path")
public class FileController {
	@Autowired
	FileUploadingService fileservice;
	
	@Autowired
	FileDatabaserepository filedb;
	@PostMapping(value = "/file")
	public void fileuploading(@RequestParam("file") MultipartFile file) throws IOException {

		System.out.println("Original Image Byte Size - " + file.getBytes().length+ " "+file.getContentType());
		if (file.isEmpty()) {
			throw new FileNotFound("Sorry! File filed is empty ");
		}
		if (file.getContentType().equals("image/jpeg") ||file.getContentType().equals("image/png")) {
			System.out.print("hello world");
			 fileservice.UploadData(file);
		}


	}

	@GetMapping(value = "/{filename}")
	public Fileinfomodel getimage(@PathVariable("filename") String filename) {
		 Optional<Fileinfomodel> retrivedfile = filedb.findByFilename(filename);
		Fileinfomodel fileinfo = new Fileinfomodel(retrivedfile.get().getFileName(), retrivedfile.get().getFileType(),retrivedfile.get().getImage());
		return fileinfo;
	}
	@GetMapping(value = "/findbyid/{id}")
	public Optional<Fileinfomodel> getimage(@PathVariable("id") Long id) {
		
		return filedb.findById(id);
	}
	@PutMapping("/updateIcon/{id}")
	public Optional<Fileinfomodel> UpdateIcon(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
		return fileservice.UpdateUploadImage(id, file);
	}

	@GetMapping("/find")
	public List<Fileinfomodel> findlastid()
	{
		return filedb.findByIdDesc();
	}
}
