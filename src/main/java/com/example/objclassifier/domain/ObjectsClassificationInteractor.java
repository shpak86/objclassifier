package com.example.objclassifier.domain;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.objclassifier.domain.entities.Image;

@Service(value = "classificationInteractor")
public class ObjectsClassificationInteractor implements ObjectsClassificationUseCase {

    @Autowired
    @Qualifier("classificationRepository")
    ClassificationRepository repository;

    @Value("${processing.path}")
    String processingPath;

    @Override
    public boolean process(Image image) {
        try {
            File file = Paths.get(processingPath, image.getName()).toFile();
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            outputStream.write(image.getData());
            outputStream.close();
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public Set<String> getGroups() {
        return repository.getGroups();
    }

    @Override
    public Set<String> getGroupImages(String name) {
        return repository.getGroupImages(name);
    }

    @Override
    public Set<String> getImageGroups(String name) {
        return repository.getImageGroups(name);
    }

    @Override
    public boolean removeGroup(String name) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeImage(String name) {
        // TODO Auto-generated method stub
        return false;
    }

}
