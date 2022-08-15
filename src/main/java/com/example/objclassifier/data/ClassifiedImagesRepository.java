package com.example.objclassifier.data;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassifiedImagesRepository extends JpaRepository<ClassifiedImage, Long> {

    // @Query("select i from ClassifiedImage i, ObjectsGroup g, images_groups l where g.name = 'group_1' and l.objects_group_id = g.id and l.classified_image_id = i.id")
    // Collection<ClassifiedImage> findImagesGroup() ;

}
