package com.endlos.admin.file.controller;

import java.util.List;

import com.endlos.admin.file.Excelhelper.masExcelHelper;
import com.endlos.admin.file.model.Masterdatamodel;
import com.endlos.admin.file.service.ExcelService;
import com.endlos.admin.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



//@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/api/excel")
public class ExcelController {

  @Autowired
  ExcelService fileService;

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";

    if (masExcelHelper.hasExcelFormat(file)) {
    	
      try {
    	  System.out.println("file done");
        fileService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!" +e.getMessage();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
      }
    }

    message = "Please upload an excel file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
  }

  @GetMapping("/tutorials")
  public ResponseEntity<List<Masterdatamodel>> getAllTutorials() {
    try {
      List<Masterdatamodel> tutorials = fileService.getAllTutorials();

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

	/*
	 * @GetMapping("/download") public ResponseEntity<Resource> getFile() { String
	 * filename = "tutorials.xlsx"; InputStreamResource file = new
	 * InputStreamResource(fileService.load());
	 * 
	 * return ResponseEntity.ok() .header(HttpHeaders.CONTENT_DISPOSITION,
	 * "attachment; filename=" + filename)
	 * .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
	 * .body(file); }
	 */

}
