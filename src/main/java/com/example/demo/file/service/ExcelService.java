package com.example.demo.file.service;

import java.io.IOException;
import java.util.List;

import com.example.demo.file.Excelhelper.masExcelHelper;
import com.example.demo.file.Repository.MasterDataRepository;
import com.example.demo.file.model.Masterdatamodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class ExcelService {
  @Autowired
  MasterDataRepository repository;

  public void save(MultipartFile file) {
    try {
      List<Masterdatamodel> tutorials = masExcelHelper.excelToTutorials(file.getInputStream());
      System.out.println("service class");
      repository.saveAll(tutorials);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }
	/*
	 * public ByteArrayInputStream load() { List<Masterdatamodel> tutorials =
	 * repository.findAll();
	 * 
	 * ByteArrayInputStream in = masExcelHelper.tutorialsToExcel(tutorials); return
	 * in; }
	 */

  public List<Masterdatamodel> getAllTutorials() {
    return repository.findAll();
  }
}
