package com.example.objclassifier.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.objclassifier.domain.ObjectsClassificationUseCase;
import com.example.objclassifier.domain.entities.Image;

@Controller
@RequestMapping(value = "/v1")
public class ImageProcessorController {

    @Autowired
    @Qualifier("classificationInteractor")
    ObjectsClassificationUseCase useCase;

    @GetMapping("/groups")
    public @ResponseBody Iterable<String> getGroups() {
        return useCase.getGroups();
    }

    @GetMapping("/groups/{name}")
    public @ResponseBody Iterable<String> getGroup(@PathVariable("name") String name) {
        return useCase.getGroupImages(name);
    }

    @GetMapping(value = "/images/{name}")
    public @ResponseBody ResponseEntity<Iterable<String>> getImageGroups(@PathVariable("name") String name) {
        return new ResponseEntity<Iterable<String>>(useCase.getImageGroups(name), HttpStatus.OK);
    }

    @PostMapping(value = "/images")
    public @ResponseBody ResponseEntity<String> process(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file) {
        try {
            useCase.process(new Image(file.getBytes(), name));
            return new ResponseEntity<String>("", HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
