package com.pact.consumer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import com.pact.consumer.services.*;
import com.pact.consumer.models.Greeting;

@Controller
public class HelloWorldController {

    private HelloWorldService helloWorldService;

    @Autowired
    HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping("/hello")
    public @ResponseBody
    Greeting sayHello(@RequestParam(value="name", required=false, defaultValue="Nobody") String name) {
        return helloWorldService.sayHello(name);
    }

}