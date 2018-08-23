package com.pact.consumer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Greeting {

    @JsonProperty(value ="id")
    private long id;

    @JsonProperty(value ="content")
    private String content;

}