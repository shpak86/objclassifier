package com.example.objclassifier.domain.entities;

public class Image {
    
    private byte[] data;
    private String name;

    public Image(byte[] data, String name) {
        this.data = data;
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public String getName() {
        return name;
    }

}
