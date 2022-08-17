package com.example.objclassifier.domain;

import java.util.Set;

public interface ObjectsClassificationUseCase {

    boolean classify(byte[] image);

    Set<String> getGroups();

    Set<String> getGroupImages(String name);

    Set<String> getImageGroups(String name);

    boolean removeGroup(String name);

    boolean removeImage(String name);

}
