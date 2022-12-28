# Test task application

The application created with Spring WebFlux. The main purpose is to get list of repositories with branches for given user.

## How to Install and Run the Project

 - Make sure to be in the root directory.
 - Clean and build the project, run the command:

```shell
mvn install
```

This will also generate a jar file with all the dependencies which we will run once it has been created.
Run the Main method in Application.java by running

```shell
mvn exec:java
```


## Running application in Docker

To build and publish the application as docker container, a `Dockerfile` is provided.
Run the following command to create the container, replace the `<image-name>` and `<image-tag>`

```shell
docker build -t <image-name>:<image-tag> .
```

Use next command to check that image was created:

```shell
docker images
```

Run the following to start application in th container

```shell
docker run <image-name>
```
