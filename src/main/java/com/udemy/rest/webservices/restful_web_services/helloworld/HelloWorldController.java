package com.udemy.rest.webservices.restful_web_services.helloworld;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    //@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World AR";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World AR");
    }

    //path parameters
    // /users/{id} => users/10
    // /hello-world/path-variable/{name} => hello-world/path-variable/abhi

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        //return new HelloWorldBean("Hello World " + name);
        return new HelloWorldBean(String.format("Hello %s , kaise ho!!", name));
    }
}

