package com.example.objclassifier.domain;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "classificationInteractor")
public class ObjectsClassificationInteractor implements ObjectsClassificationUseCase {

    @Autowired
    @Qualifier("classificationRepository")
    ClassificationRepository classificationRepository;

    @Autowired
    @Qualifier("tensorflowObjectsClassifier")
    ObjectsClassifier classifier;

    @Autowired
    @Qualifier("imagesRepository")
    ImagesRepository imagesRepository;

    @Override
    public boolean classify(byte[] image) {
        try {
            String imageName = System.currentTimeMillis() + ".jpg";
            imagesRepository.save(imageName, image);
            Set<String> groups = classifier.classify(image);
            classificationRepository.addImage(imageName, groups);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Set<String> getGroups() {
        return classificationRepository.getGroups();
    }

    @Override
    public Set<String> getGroupImages(String name) {
        return classificationRepository.getGroupImages(name);
    }

    @Override
    public Set<String> getImageGroups(String name) {
        return classificationRepository.getImageGroups(name);
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
