package com.example.objclassifier.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.objclassifier.domain.ImagesRepository;

@Component(value = "imagesRepository")
public class ImagesRepositoryImpl implements ImagesRepository {

    private static Logger logger = LoggerFactory.getLogger(ImagesRepositoryImpl.class);

    @Value("${processing.path}")
    String processingPath;

    @Override
    public void save(String name, byte[] data) {
        try {
            Files.write(Path.of(processingPath, name), data);
        } catch (IOException e) {
            logger.error("Unable to save " + name, e);
        }
    }

}
