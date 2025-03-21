package com.kshrd.schoolcourse.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instructor {
    private Integer instructorId;
    private String instructorName;
    private String email;
}
