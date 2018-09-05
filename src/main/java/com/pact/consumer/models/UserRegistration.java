package com.pact.consumer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistration {

    @JsonProperty(value ="user_id")
    private long user_id;
    @JsonProperty(value ="user_name")
    private String userName;
    @JsonProperty(value ="courses")
    private List<Course> course;

}