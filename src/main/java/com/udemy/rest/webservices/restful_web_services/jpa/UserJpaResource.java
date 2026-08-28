package com.udemy.rest.webservices.restful_web_services.jpa;


import com.udemy.rest.webservices.restful_web_services.users.Post;
import com.udemy.rest.webservices.restful_web_services.users.User;
import com.udemy.rest.webservices.restful_web_services.users.UserDaoService;
import com.udemy.rest.webservices.restful_web_services.users.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {

    private UserRepository repository;
    private PostRepository postRepository ;

    //constructor injection
    public UserJpaResource( UserRepository repository, PostRepository postRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
    }

    // GET /users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers()
    {
        return repository.findAll();
    }


    //GET /users/{id}
    @GetMapping("/jpa/users/{id}")
    public User retrieveUserById(@PathVariable int id)
    {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id : "+ id);
        return user.orElse(null);
    }


    // POST /users
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user)
    {
        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();

        System.out.println("Inside createUser");

        //status code
        return ResponseEntity.created(location).build();
    }


    //DELETE /users/{id}
    @DeleteMapping("/jpa/users/{id}")
    public void DeleteById(@PathVariable int id)
    {
        repository.deleteById(id);
    }


    ////  GET/users/{id}/post
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostForAUser(@PathVariable int id)
    {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id : "+ id);
        return user.get().getPosts();
    }


    ////  POST/users/{id}/post
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@Valid @RequestBody Post post, @PathVariable int id)
    {
        Optional<User> user = repository.findById(id);//finds if the user exists in db

        if (user.isEmpty())
            throw new UserNotFoundException("id : "+ id);
        post.setUser(user.get());//mapping post to user
        Post savedPost = postRepository.save(post);// saves post to db

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        //status code
        return ResponseEntity.created(location).build();
    }


}
