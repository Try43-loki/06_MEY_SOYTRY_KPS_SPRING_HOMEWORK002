package com.kshrd.schoolcourse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    private Integer courseId;
    private String courseName;
    private String description;
    private Instructor instructor;
}
