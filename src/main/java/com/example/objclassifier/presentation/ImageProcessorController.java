package com.example.objclassifier.presentation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.objclassifier.data.ClassifiedImage;
import com.example.objclassifier.data.ClassifiedImagesRepository;
import com.example.objclassifier.data.ObjectsGroup;
import com.example.objclassifier.data.ObjectsGroupsRepository;

@Controller
@RequestMapping(value = "/v1/classifier")
public class ImageProcessorController {

    @Autowired
    ClassifiedImagesRepository imagesRepository;

    @Autowired
    ObjectsGroupsRepository groupsRepository;

    @GetMapping("/add")
    @ResponseBody
    public String add() {

        var objectsGroups = groupsRepository.findByName("group_1");
        if (objectsGroups.isEmpty()) {
            return "{status: 'error: group_1 not found'}";
        } else {
            String id = Long.valueOf(System.currentTimeMillis()).toString();
            ClassifiedImage image = new ClassifiedImage("image_" + id);
            ObjectsGroup group1 = objectsGroups.get(0);
            ObjectsGroup group2 = new ObjectsGroup("group_" + id);
            image.addGroup(group1);
            image.addGroup(group2);
            groupsRepository.save(group2);
            imagesRepository.save(image);
        }

        return "{status: 'ok'}";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<ClassifiedImage> process() {
        var images = imagesRepository.findAll();
        return StreamSupport.stream(images.spliterator(), false).collect(Collectors.toList());
    }

    @GetMapping("/group/{name}")
    @ResponseBody
    public Iterable<String> groups(@PathVariable("name") String name) {
        List<ObjectsGroup> groups = groupsRepository.findByName(name);
        Set<String> classifiedImages = new HashSet<>();
        groups.stream().forEach(group -> {
            classifiedImages.addAll(group.getImages().stream().map(it -> it.getName()).collect(Collectors.toList()));
        });
        return classifiedImages;
    }
}
