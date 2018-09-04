package com.pact.consumer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Greeting {

    @JsonProperty(value ="id")
    private long id;

    @JsonProperty(value ="content")
    private String content;

    @JsonProperty(value ="course_name")
    private String courseName;




}