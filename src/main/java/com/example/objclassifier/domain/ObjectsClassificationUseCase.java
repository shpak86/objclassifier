package com.example.objclassifier.domain;

import java.util.Set;

import com.example.objclassifier.domain.entities.Image;

public interface ObjectsClassificationUseCase {

    boolean process(Image image);

    Set<String> getGroups();

    Set<String> getGroupImages(String name);

    Set<String> getImageGroups(String name);

    boolean removeGroup(String name);

    boolean removeImage(String name);

}
