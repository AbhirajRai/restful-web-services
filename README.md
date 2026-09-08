# RESTful Web Services

A Spring Boot project focused on learning and implementing RESTful web services, covering REST API development, validation, exception handling, JPA/Hibernate, database relationships, API versioning, and Spring Security.

## Features

- RESTful APIs with Spring Boot
- User CRUD operations
- Bean Validation
- Custom exception handling
- JPA/Hibernate with H2 database
- User–Post entity relationship
- REST API versioning
- Spring Security with Basic Authentication
- OpenAPI/Swagger API documentation

## Tech Stack

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Spring Validation
- Spring Security
- SpringDoc OpenAPI
- Maven

## Project Structure

~~~
src/main/java/com/udemy/rest/webservices/restful_web_services/
├── exception/       # Global exception handling
├── helloworld/      # Basic REST endpoints
├── jpa/             # JPA-based REST APIs and repositories
├── security/        # Spring Security configuration
├── users/           # User, Post and in-memory user APIs
├── versioning/      # REST API versioning examples
└── RestfulWebServicesApplication.java

src/main/resources/
├── application.properties
└── data.sql
~~~

## API Examples

~~~
GET    /users
GET    /users/{id}
POST   /users
DELETE /users/{id}

GET    /jpa/users
GET    /jpa/users/{id}
POST   /jpa/users
DELETE /jpa/users/{id}

GET    /jpa/users/{id}/posts
POST   /jpa/users/{id}/posts
~~~

The project also demonstrates API versioning using URL paths and request parameters.

## Running the Application

Clone the repository:

~~~
git clone https://github.com/AbhirajRai/restful-web-services.git
cd restful-web-services
~~~

Run using Maven:

~~~
./mvnw spring-boot:run
~~~

The application runs by default at:

`http://localhost:8080`

APIs can be tested using Postman, IntelliJ HTTP Client, cURL, or Swagger UI.

## Purpose

This repository is part of my Java and Spring Boot backend learning journey, progressing from basic REST endpoints to database persistence, relationships, validation, security, and API versioning.