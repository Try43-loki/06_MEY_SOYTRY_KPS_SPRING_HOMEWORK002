package com.kshrd.schoolcourse.repo;

import com.kshrd.schoolcourse.model.Course;
import com.kshrd.schoolcourse.model.request.CourseRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseRepo {

    // select all
    @Select("""
            select * from courses
             offset #{limit} * (#{offset} - 1)
            limit #{limit}
            """)
    @Results(id = "courseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "instructor", column = "instructor_id",
                    one = @One(select = "com.kshrd.schoolcourse.repo.InstructorRepo.findInstructorById")
            )
    })
    List<Course> findAllCourses(Integer offset, Integer limit);

    // select by id
    @Select("select * from courses where course_id = #{id}")
    @ResultMap("courseMapper")
    Course findCourseById(Integer id);

    // insert course
    @Insert("""
                INSERT INTO courses (course_name, description, instructor_id)
                VALUES (#{course.courseName}, #{course.description}, #{course.instructorId})
            """)
    void insertCourse(@Param("course") CourseRequest courseRequest);

    // update course
    @Select("""
                    UPDATE courses
                    SET course_name = #{course.courseName},
                        description = #{course.description},
                        instructor_id = #{course.instructorId}
                    WHERE course_id = #{id}
                    RETURNING *;
            """)
    @ResultMap("courseMapper")
    Course updateCourse(Integer id, @Param("course") CourseRequest courseRequest);

    // delete course
    @Select("delete from courses where course_id  = #{id}")
    Course deleteCourse(Integer id);

}
