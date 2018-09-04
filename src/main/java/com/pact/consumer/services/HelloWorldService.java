package com.pact.consumer.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.pact.consumer.clients.*;
import com.pact.consumer.models.Greeting;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class HelloWorldService {
    private ProviderClient providerClient;

    @Autowired
    public HelloWorldService(ProviderClient providerClient){
        this.providerClient = providerClient;
    }

    @HystrixCommand(fallbackMethod = "getBackupHello")
    public Greeting sayHello(String name) {
        Greeting greeting = providerClient.getProviderHello(name);
        greeting.setCourseName("Contract Driven Testing");
        return greeting;
    }

    public Greeting getBackupHello(String name) {
        return new Greeting(0, String.format("Sorry - there's no one to welcome you %s , try later!", name),"No Courses found");
    }


}