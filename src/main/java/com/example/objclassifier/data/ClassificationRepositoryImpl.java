package com.example.objclassifier.data;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.objclassifier.domain.ClassificationRepository;

@Component(value = "classificationRepository")
public class ClassificationRepositoryImpl implements ClassificationRepository {

    @Autowired
    ClassifiedImagesRepository imagesRepository;

    @Autowired
    ObjectsGroupsRepository groupsRepository;

    @Override
    public void addImage(String name, Set<String> groups) {
        ClassifiedImage image = new ClassifiedImage(name);
        groups.forEach(groupName -> {
            groupsRepository
                    .findByName(groupName)
                    .forEach(group -> image.getGroups().add(group));
        });
        imagesRepository.save(image);
    }

    @Override
    public Set<String> getGroups() {
        Set<String> result = new HashSet<>();
        groupsRepository.findAll().forEach(group -> result.add(group.getName()));
        return result;
    }

    @Override
    public Set<String> getGroupImages(String name) {
        Set<String> result = new HashSet<>();
        var groups = groupsRepository.findByName(name);
        if (!groups.isEmpty()) {
            groups.get(0).getImages().forEach(image -> result.add(image.getName()));
        }
        return result;
    }

    @Override
    public Set<String> getImageGroups(String name) {
        Set<String> result = new HashSet<>();
        var images = imagesRepository.findByName(name);
        if (!images.isEmpty()) {
            ClassifiedImage image = images.get(0);
            image.getGroups().forEach(group -> result.add(group.name));
        }
        return result;
    }

}
