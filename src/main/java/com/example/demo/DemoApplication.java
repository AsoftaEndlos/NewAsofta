package com.example.demo;

import com.example.demo.fileservice.FileHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;

@SpringBootApplication
@EnableJpaRepositories
public class DemoApplication {

	public static void main(String[] args) {
		new File(FileHelper.UPLOAD_DIR).mkdirs();
		SpringApplication.run(DemoApplication.class, args);
	}

}
