package com.kshrd.schoolcourse.controller;

import com.kshrd.schoolcourse.model.Course;
import com.kshrd.schoolcourse.model.request.CourseRequest;
import com.kshrd.schoolcourse.model.response.ApiResponse;
import com.kshrd.schoolcourse.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/course")
public class CourseController {

    // inject course service
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // get all course
    @GetMapping()
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(@RequestParam(defaultValue = "1") Integer offset, @RequestParam(defaultValue = "2") Integer limit) {
        ApiResponse<List<Course>> response = ApiResponse.<List<Course>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .message("Get all courses successfully")
                .payload(courseService.getAllCourses(offset, limit))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // get course by id
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable Integer id) {

        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .message("Get courses ID " + id + " successfully")
                .payload(courseService.getCourseById(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // add course
    @PostMapping
    public ResponseEntity<ApiResponse<?>> addCourse(@RequestBody CourseRequest courseRequest) {
        courseService.addCourse(courseRequest);
        ApiResponse<CourseRequest> response = ApiResponse.<CourseRequest>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED)
                .message("Add course successfully")
                .payload(courseRequest)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // update course
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<CourseRequest>> updateCourse(@PathVariable Integer id, @RequestBody CourseRequest courseRequest) {
        courseService.updateCourse(id, courseRequest);
        ApiResponse<CourseRequest> response = ApiResponse.<CourseRequest>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .message("Update course successfully")
                .payload(courseRequest)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // delete course
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Course>> deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .message("Delete course successfully")
                .payload(null)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
