package com.kshrd.schoolcourse.services;

import com.kshrd.schoolcourse.model.Instructor;
import com.kshrd.schoolcourse.model.request.InstructorRequest;
import com.kshrd.schoolcourse.repo.InstructorRepo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InstructorService {

    List<Instructor> getAllInstructors( Integer offset, Integer limit );
    Instructor getInstructorById(int id);
    Instructor addInstructor(InstructorRequest instructorRequest);
    Instructor updateInstructor(int id, InstructorRequest instructorRequest);
    void removeInstructor(int id);

}
