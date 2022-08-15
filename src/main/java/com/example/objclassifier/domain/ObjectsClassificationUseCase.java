package com.example.objclassifier.domain;

import java.util.Set;

public interface ObjectsClassificationUseCase {
    void process(byte[] image);

    Set<String> getClasses();

    Set<String> getImages(String group);

    Set<String> getImages(Set<String> groups);

    void removeClass(String group);

    void removeImage(String image);

}
