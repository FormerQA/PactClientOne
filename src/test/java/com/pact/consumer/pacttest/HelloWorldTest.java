/*
 * Copyright (c) 2017 Target Inc
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Target Inc and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Target Inc
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Target Inc
 */

package com.pact.consumer.pacttest;

import au.com.dius.pact.consumer.*;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.pact.consumer.models.Course;
import com.pact.consumer.models.CourseId;
import com.pact.consumer.models.Greeting;
import com.pact.consumer.models.UserRegistration;
import com.pact.consumer.utils.Utils;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class HelloWorldTest
{

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("PactProvider", "localhost", 8082, this);

    @Pact(provider ="PactProvider" , consumer = "PactConsumerOne")
    public RequestResponsePact createFragment(PactDslWithProvider builder) {
        return builder
                .uponReceiving("Request from Pact Consumer One")
                .headers("name","pravitha")
                .path("/hello-world")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(basicContract())
                .toPact();
    }

    @Pact(provider ="PactProvider" , consumer = "PactConsumerTwo")
    public RequestResponsePact userRegisteration(PactDslWithProvider builder) {
        return builder
                .uponReceiving("Request from Pact Consumer One")
                .path("/hello-world/register")
                .body(userRegisterContract())
                .method("POST")
                .willRespondWith()
                .status(201)
                .body(userRegisterContract())
                .toPact();
    }

    @Test
    @PactVerification(fragment = "createFragment")
    public void testPactForConsumer() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("name","pravitha");
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response =
            new RestTemplate().exchange(mockProvider.getUrl()+ "/hello-world",
                    HttpMethod.GET, entity, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @PactVerification(fragment = "userRegisteration")
    public void testPactForConsumerUserRegistration() {
        HttpEntity entity = new HttpEntity(getUserEntity());
        ResponseEntity<UserRegistration> response =
                new RestTemplate().exchange(mockProvider.getUrl()+ "/hello-world/register",
                        HttpMethod.POST, entity, UserRegistration.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }


    private DslPart basicContract() {
        return new PactDslJsonBody()
                .numberType("id")
                .stringType("content")
                .asBody();
    }

    private DslPart userRegisterContract() {
        return new PactDslJsonBody()
                .numberType("user_id")
                .stringType("user_name")
                .minArrayLike("courses" , 1)
                    .stringType("course_name")
                    .date("register_date","yyyy-MM-dd")
                    .object("course_details")
                        .numberType("user_id")
                        .numberType("course_id")
                    .closeObject()
                .closeObject()
                .closeArray()
                .asBody();
    }

    private UserRegistration getUserEntity(){
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setUser_id((long) 123456);
        userRegistration.setUserName("Pravitha");
        Course course = new Course();
        CourseId courseId = new CourseId();
        courseId.setUser_id((long) 123456);
        courseId.setCourseId((long) 1);
        course.setCourseId(courseId);
        course.setCourseName("PACT");
        course.setRegisterDate(Utils.parseDate("2018-09-05"));
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        userRegistration.setCourse(courseList);
        return userRegistration;
    }
}

