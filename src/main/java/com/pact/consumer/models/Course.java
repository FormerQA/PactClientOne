package com.pact.consumer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @JsonProperty(value ="course_details")
    private CourseId courseId;

    @JsonProperty(value ="course_name")
    private String courseName;

    @JsonProperty(value ="register_date")
    private Date registerDate;

}