package com.example.objclassifier.domain;

import java.util.Set;

public interface ObjectsClassifier {
    public Set<String> classify(byte[] image);
}
