package com.kshrd.schoolcourse.controller;

import com.kshrd.schoolcourse.model.Student;
import com.kshrd.schoolcourse.model.request.StudentRequest;
import com.kshrd.schoolcourse.model.response.ApiResponse;
import com.kshrd.schoolcourse.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    // inject student service
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // get all student
    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents(@RequestParam(defaultValue = "1") Integer offset, @RequestParam(defaultValue = "2") Integer limit) {
        ApiResponse<List<Student>> response = ApiResponse.<List<Student>>builder()
                .timestamp(LocalDateTime.now())
                .message("Get All Students Successfully")
                .status(HttpStatus.OK)
                .payload(studentService.getAllStudents(offset, limit))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    // get student by id
    @GetMapping("{student-id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable("student-id") Integer id) {

        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .timestamp(LocalDateTime.now())
                .message("Get Student ID " + id + " Successfully")
                .status(HttpStatus.OK)
                .payload(studentService.getStudentById(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // add student
    @PostMapping()
    public ResponseEntity<ApiResponse<Student>> addStudent(@RequestBody StudentRequest studentRequest) {
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .timestamp(LocalDateTime.now())
                .message("Get Student Successfully")
                .status(HttpStatus.CREATED)
                .payload(studentService.addStudent(studentRequest))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    // update student
    @PutMapping("{student-id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@PathVariable("student-id") Integer id, @RequestBody StudentRequest studentRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Student>builder()
                        .timestamp(LocalDateTime.now())
                        .message("Update Student Successfully")
                        .status(HttpStatus.OK)
                        .payload(studentService.updateStudent(id, studentRequest))
                        .build()
        );
    }

    // delete student
    @DeleteMapping("{student-id}")
    public ResponseEntity<ApiResponse<Student>> deleteStudent(@PathVariable("student-id") Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Student>builder()
                        .timestamp(LocalDateTime.now())
                        .message("Delete Student Successfully")
                        .status(HttpStatus.OK)
                        .payload(null)
                        .build()
        );
    }

}