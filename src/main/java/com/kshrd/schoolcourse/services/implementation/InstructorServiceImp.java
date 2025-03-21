package com.kshrd.schoolcourse.services.implementation;

import com.kshrd.schoolcourse.exception.UserNotFoundException;
import com.kshrd.schoolcourse.model.Instructor;
import com.kshrd.schoolcourse.model.request.InstructorRequest;
import com.kshrd.schoolcourse.repo.InstructorRepo;
import com.kshrd.schoolcourse.services.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImp implements InstructorService {

    // inject repo
    private final InstructorRepo instructorRepo;

    public InstructorServiceImp(InstructorRepo instructorRepo) {
        this.instructorRepo = instructorRepo;
    }

    @Override
    public List<Instructor> getAllInstructors(Integer offset, Integer limit) {
        return instructorRepo.findAllInstructors(offset, limit);
    }

    @Override
    public Instructor getInstructorById(int id) {

        Instructor instructor = instructorRepo.findInstructorById(id);
        if (instructor == null) {
            throw new UserNotFoundException("Instructor ID " + id + " Not found");
        }
        return instructor;
    }

    @Override
    public void addInstructor(InstructorRequest instructorRequest) {
        instructorRepo.insertInstructor(instructorRequest);
    }

    @Override
    public Instructor updateInstructor(int id, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepo.updateInstructor(id, instructorRequest);
        if (instructor == null) {
            throw new UserNotFoundException("Instructor ID " + id + " Not found");
        }
        return instructor;
    }

    @Override
    public Instructor removeInstructor(int id) {
        Instructor instructor = getInstructorById(id);
        instructorRepo.deleteInstructor(id);
        return instructor;
    }

}
