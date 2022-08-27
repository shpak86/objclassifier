# ObjClassifier

## Description

Objects classification service which provides simple REST API for images upload and search images by a class. Free tensorflow model published under the Apache license is used for the classification. H2 database is used as a data storagre.

## Installation

1. Build JAR using `mvn package` command.
2. Create `models` directory and place `tensorflow_inception_graph.*` there.
3. Create `public` directory as a storage for the uploaded images.
4. Create `application.properties` and set needed parameters for the application.
5. Run the application using `java -jar objclassifier-0.0.1-SNAPSHOT.jar` command.

## Configuration

The project uses Spring boot, Spring Web and Spring data JPA, so you shoud use standard properties if you need to customize database or service.

You should set processing properties:

- `processing.path`
  
  Path to processed images storage. By default it is 'public'.

- `processing.model`
  
  Path to the tensorflow classification model.

- `processing.groups`
  
  Path to the groups list file.

## API

### /v1/groups [GET]

Return a list of images groups.

Response example:
```
[
  "zebra",
  "African elephant",
  "tabby"
]
```

### /v1/groups/{name} [GET]

Return a list of images in a group {name}.

Response example:
```
[
  "1661588051129.jpg"
  "1661588055135.jpg"
]
```

### /v1/images/{name} [GET]

Return a list of groups for specified image {name}

Response example:
```
[
  "zebra"
]
```

### /v1/images [POST]

Process and classify image from `file` field of the request. File will be saved to the `processing.path` folder with name generated from unix epoch time.

### /{image} [GET]

Get a previously uploaded image with the name {image}.

## LICENSE

You can do whatever you need with the code. Data and software should be totally free!