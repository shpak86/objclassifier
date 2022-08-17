package com.example.objclassifier.domain;

import java.util.Set;

public interface ClassificationRepository {

    void addImage(String name, Set<String> groups);

    Set<String> getGroups();

    Set<String> getGroupImages(String name);

    Set<String> getImageGroups(String name);

}
