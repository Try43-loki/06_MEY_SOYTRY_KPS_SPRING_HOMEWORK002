package com.kshrd.schoolcourse.services.implementation;

import com.kshrd.schoolcourse.exception.UserNotFoundException;
import com.kshrd.schoolcourse.model.Course;
import com.kshrd.schoolcourse.model.Instructor;
import com.kshrd.schoolcourse.model.request.CourseRequest;
import com.kshrd.schoolcourse.repo.CourseRepo;
import com.kshrd.schoolcourse.repo.InstructorRepo;
import com.kshrd.schoolcourse.services.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService {

    // inject
    private final CourseRepo courseRepo;
    private final InstructorRepo instructorRepo;

    public CourseServiceImp(CourseRepo courseRepo, InstructorRepo instructorRepo) {
        this.courseRepo = courseRepo;
        this.instructorRepo = instructorRepo;
    }

    @Override
    public List<Course> getAllCourses(Integer offset, Integer limit) {
        return courseRepo.findAllCourses(offset, limit);
    }

    @Override
    public Course getCourseById(Integer id) {
        Course course = courseRepo.findCourseById(id);
        if (course == null) {
            throw new UserNotFoundException("Course ID " + id + " Not found");
        }
        return course;

    }

    @Override
    public void addCourse(CourseRequest courseRequest) {
        courseRepo.insertCourse(courseRequest);
    }

    @Override
    public Course updateCourse(Integer id, CourseRequest courseRequest) {
        Instructor instructor = instructorRepo.findInstructorById(id);
        Course course = courseRepo.findCourseById(id);

        if (course == null) {
            throw new UserNotFoundException("Course ID " + id + " Not found");
        }

        if (instructor == null) {
            throw new UserNotFoundException("Instructor ID " + id + " Not found");
        }
        return courseRepo.updateCourse(id, courseRequest);
    }

    @Override
    public Course deleteCourse(Integer id) {
        Course course = getCourseById(id);
        courseRepo.deleteCourse(id);
        return course;
    }
}
