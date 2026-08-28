package com.udemy.rest.webservices.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionPersonController {

    //url versioning
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson()
    {
        return new PersonV1("bob charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson()
    {
        return new PersonV2(new Name("bob", "charlie"));
    }

    //by request params
    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter()
    {
        return new PersonV1("bob charlie");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestPerson()
    {
        return new PersonV2(new Name("bob", "charlie"));
    }

}
