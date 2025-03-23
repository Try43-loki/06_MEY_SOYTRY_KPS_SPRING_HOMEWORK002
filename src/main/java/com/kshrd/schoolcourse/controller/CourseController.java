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
    @GetMapping("{course-id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable("course-id") Integer id) {

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
    public ResponseEntity<ApiResponse<Course>> addCourse(@RequestBody CourseRequest courseRequest) {
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED)
                .message("Add course successfully")
                .payload(courseService.addCourse(courseRequest))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // update course
    @PutMapping("{course-id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@PathVariable("course-id") Integer id, @RequestBody CourseRequest courseRequest) {

        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .message("Update course successfully")
                .payload(courseService.updateCourse(id, courseRequest))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // delete course
    @DeleteMapping("{course-id}")
    public ResponseEntity<ApiResponse<Course>> deleteCourse(@PathVariable("course-id") Integer id) {
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
