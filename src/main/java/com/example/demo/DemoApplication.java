package com.example.demo;

import com.example.demo.file.service.FileHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		new File(FileHelper.UPLOAD_DIR).mkdirs();
		SpringApplication.run(DemoApplication.class, args);
	}

}
