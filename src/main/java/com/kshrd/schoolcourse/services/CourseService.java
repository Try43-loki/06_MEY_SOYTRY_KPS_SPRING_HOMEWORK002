package com.kshrd.schoolcourse.services;

import com.kshrd.schoolcourse.model.Course;
import com.kshrd.schoolcourse.model.Instructor;
import com.kshrd.schoolcourse.model.request.CourseRequest;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CourseService {

  List<Course> getAllCourses(Integer offset, Integer limit);

  Course getCourseById(Integer id);

   void addCourse(CourseRequest courseRequest);
   Course updateCourse(Integer id, CourseRequest courseRequest);
   void deleteCourse(Integer id);
}
