# Spring REST Design Best Practice

This service implements a demo on design rest api.

## Getting started

After cloning the repository :

```bash
    git clone git@github.com:mohamedelnaggar/spring-rest-best-practice.git
    cd spring-rest-best-practice
``` 

Application Configuration [change database username and password] on file

    src/main/resources/application.yml
    
    
Create mysql database

```bash
    create database demo
```

### Running the service tests 
```bash
    mvn test
```


### Running Service
```bash
    mvn spring-boot:run
```

### Api Documentation  

A swagger api documentation is avilable through visiting the documentation url at :

    http:localhost:8080/swagger-ui.html 
