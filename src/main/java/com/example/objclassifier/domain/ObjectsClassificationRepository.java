package com.example.objclassifier.domain;

import java.util.Set;

public interface ObjectsClassificationRepository {

    final class ImageFilter {
        Set<String> groups;
    }

    void add(String group, Set<String> images);

    Set<String> getGroups();

    Set<String> getGroupsImages(ImageFilter filter);

    void removeGroup(String group);

    void removeImage(ImageFilter filter);

}
