package com.udemy.rest.webservices.restful_web_services.users;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;

    //constructor injection
    public UserResource(UserDaoService service) {
        this.service = service;
    }

    // GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUsers()
    {
        return service.findAll();
    }

    //GET /users/{id}
    @GetMapping("/users/{id}")
    public User retrieveUserById(@PathVariable int id)
    {
        User user = service.findOne(id);

        if (user == null)
            throw new UserNotFoundException("id : "+ id);
        return user;
    }

    // POST /users
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user)
    {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();

        System.out.println("Inside createUser");

        //status code
        return ResponseEntity.created(location).build();
    }

    //DELETE /users/{id}
    @DeleteMapping("/users/{id}")
    public void DeleteById(@PathVariable int id)
    {
        service.deleteById(id);
    }
}
