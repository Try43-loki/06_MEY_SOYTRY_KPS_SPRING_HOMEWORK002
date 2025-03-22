package com.kshrd.schoolcourse.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    private String studentName;
    @Schema(example = "john.doe@gmail.com")
    private String email;
    @Schema(example = "012345678")
    private String phoneNumber;
    private List<Integer> courseId;
}
