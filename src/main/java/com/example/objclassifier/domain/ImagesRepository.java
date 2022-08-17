package com.example.objclassifier.domain;

public interface ImagesRepository {
    void save(String name, byte[] data);
}
