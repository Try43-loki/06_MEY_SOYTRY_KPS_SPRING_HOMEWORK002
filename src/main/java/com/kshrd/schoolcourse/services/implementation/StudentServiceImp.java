package com.kshrd.schoolcourse.services.implementation;

import com.kshrd.schoolcourse.exception.UserNotFoundException;
import com.kshrd.schoolcourse.model.Course;
import com.kshrd.schoolcourse.model.Student;
import com.kshrd.schoolcourse.model.request.StudentRequest;
import com.kshrd.schoolcourse.repo.CourseRepo;
import com.kshrd.schoolcourse.repo.StudentRepo;
import com.kshrd.schoolcourse.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {

    // inject

    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    public StudentServiceImp(StudentRepo studentRepo, CourseRepo courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public List<Student> getAllStudents(Integer offset, Integer limit) {
        return studentRepo.findAllStudents(offset, limit);
    }

    @Override
    public Student getStudentById(Integer id) {
        Student student = studentRepo.findStudentById(id);
        if (student == null) {
            throw new UserNotFoundException("Student ID " + id + " not found");
        }
        return student;
    }

    @Override
    public Student addStudent(StudentRequest studentRequest) {
        // get studentId
        Integer studentId = studentRepo.insertStudent(studentRequest);
        //check course
        for (Integer courseId : studentRequest.getCourseId()) {
            Course course = courseRepo.findCourseById(courseId);
            if (course == null) {
                throw new UserNotFoundException("Course ID " + courseId + " not found");
            }
            studentRepo.insertStudentsCourse(studentId, courseId);
        }
        return studentRepo.findStudentById(studentId);

    }

    @Override
    public void deleteStudent(Integer id) {
        Student student = studentRepo.findStudentById(id);
        if (student == null) {
            throw new UserNotFoundException("Student ID " + id + " not found");
        }
        studentRepo.deleteStudentById(id);
    }

    @Override
    public Student updateStudent(Integer id, StudentRequest studentRequest) {
        // get student id after update
        Integer studentId = studentRepo.updateStudentById(studentRequest, id);
        // delete course
        if(studentId == null) {
            throw new UserNotFoundException("Student ID " + id + " not found");
        }else{
            studentRepo.deleteStudentsCourse(studentId);
        }
        // check
        for (Integer courseUpdateId : studentRequest.getCourseId()) {
            Course course = courseRepo.findCourseById(courseUpdateId);
            if (course == null) {
                throw new UserNotFoundException("Course ID " + courseUpdateId + " not found");
            }
            studentRepo.insertStudentsCourse(studentId, courseUpdateId);
        }
        return studentRepo.findStudentById(studentId);
    }

}