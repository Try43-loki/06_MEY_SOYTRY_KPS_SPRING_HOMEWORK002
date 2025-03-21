package com.kshrd.schoolcourse.services;

import com.kshrd.schoolcourse.model.Student;
import com.kshrd.schoolcourse.model.request.StudentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents(Integer offset, Integer limit);

    Student getStudentById(Integer id);

    Student addStudent(StudentRequest studentRequest);

    void deleteStudent(Integer id);

    Student updateStudent(Integer id, StudentRequest studentRequest);
}
