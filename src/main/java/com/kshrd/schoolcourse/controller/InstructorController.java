package com.kshrd.schoolcourse.controller;

import com.kshrd.schoolcourse.model.Instructor;
import com.kshrd.schoolcourse.model.request.InstructorRequest;
import com.kshrd.schoolcourse.model.response.ApiResponse;
import com.kshrd.schoolcourse.services.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/instructor")
public class InstructorController {

    // inject instructor service
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    // get all instructor
    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getGetAllInstructor(@RequestParam(defaultValue = "1") Integer offset, @RequestParam(defaultValue = "2") Integer limit) {

        ApiResponse<List<Instructor>> response = ApiResponse.<List<Instructor>>builder()
                .timestamp(LocalDateTime.now())
                .message("Get All Instructor Successfully")
                .status(HttpStatus.OK)
                .payload(instructorService.getAllInstructors(offset, limit))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // get instructor by id
    @GetMapping("{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> getGetInstructorById(@PathVariable("instructor-id") int id) {

        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .timestamp(LocalDateTime.now())
                .message("Get Instructor ID " + id + " Successfully")
                .status(HttpStatus.OK)
                .payload(instructorService.getInstructorById(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // add instructor
    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> addInstructor(@RequestBody InstructorRequest instructorRequest) {
        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .timestamp(LocalDateTime.now())
                .message("Add Instructor Successfully")
                .status(HttpStatus.CREATED)
                .payload(instructorService.addInstructor(instructorRequest))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    // update instructor
    @PutMapping("{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructor(@PathVariable("instructor-id") int id, @RequestBody InstructorRequest instructorRequest) {

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Instructor>builder()
                        .timestamp(LocalDateTime.now())
                        .message("Update Instructor Successfully")
                        .status(HttpStatus.OK)
                        .payload(instructorService.updateInstructor(id, instructorRequest)).build()
        );
    }

    // delete instructor
    @DeleteMapping("{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> deleteInstructor(@PathVariable("instructor-id") int id) {
        instructorService.removeInstructor(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Instructor>builder()
                        .timestamp(LocalDateTime.now())
                        .message("Delete Instructor Successfully")
                        .status(HttpStatus.OK)
                        .payload(null)
                        .build()
        );
    }
}

