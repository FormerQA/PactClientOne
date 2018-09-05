package com.pact.consumer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseId {

    @JsonProperty(value ="user_id")
    private Long user_id;

    @JsonProperty(value ="course_id")
    private Long courseId;

}