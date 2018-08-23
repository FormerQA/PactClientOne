package com.pact.consumer.controllers;

import java.util.concurrent.atomic.AtomicLong;

import com.pact.consumer.models.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello-world")
public class HelloWorldController {

    private static final String template = "Welcome to %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public @ResponseBody
    Greeting sayHello(@RequestParam(value="name", required=false, defaultValue="PACT") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}