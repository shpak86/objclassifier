package com.example.objclassifier.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassifiedImagesRepository extends JpaRepository<ClassifiedImage, Long> {
    
    List<ClassifiedImage> findByName(String name);

    void deleteByName(String name);
}
