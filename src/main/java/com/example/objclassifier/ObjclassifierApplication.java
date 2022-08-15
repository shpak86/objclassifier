package com.example.objclassifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.objclassifier.data.ClassifiedImagesRepository;

@SpringBootApplication
public class ObjclassifierApplication {

	@Autowired
	ClassifiedImagesRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(ObjclassifierApplication.class, args);
	}

}
