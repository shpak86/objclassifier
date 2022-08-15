package com.example.objclassifier.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ObjectsGroupsRepository extends CrudRepository<ObjectsGroup, Long> {

    List<ObjectsGroup> findByName(String name);
}
