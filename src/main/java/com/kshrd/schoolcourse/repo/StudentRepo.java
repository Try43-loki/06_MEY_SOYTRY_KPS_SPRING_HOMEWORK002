package com.kshrd.schoolcourse.repo;

import com.kshrd.schoolcourse.model.Course;
import com.kshrd.schoolcourse.model.Student;
import com.kshrd.schoolcourse.model.request.StudentRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentRepo {

    // get all
    @Select("""
            select * from students
             offset #{limit} * (#{offset} - 1)
            limit #{limit}
            """)
    @Results(id = "studentMapper", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "course", column = "student_id",
                    many = @Many(select = "findAllCourses")
            ),
    })
    List<Student> findAllStudents(Integer offset, Integer limit);

    // get all by id student
    @Select("""
            select * from courses
            inner join student_course sc on courses.course_id = sc.course_id
            where student_id = #{coureId}
            """)
    @Results(id = "courseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "instructor", column = "instructor_id",
                    one = @One(select = "com.kshrd.schoolcourse.repo.InstructorRepo.findInstructorById")
            )
    })
    List<Course> findAllCourses(Integer courseId);

    // get student by id
    @Select("select * from students where student_id = #{studentId}")
    @ResultMap("studentMapper")
    Student findStudentById(Integer studentId);

    // insert student
    @Select("""
            INSERT INTO students(student_name, email, phone_number)
            VALUES (#{rq.studentName}, #{rq.email}, #{rq.phoneNumber})
            returning student_id;
            
            """)
    Integer insertStudent(@Param("rq") StudentRequest studentRequest);

    // insert course
    @Select("""
                Insert Into student_course(student_id, course_id)
                VALUES (#{studentId}, #{courseId})
            """)
    void insertStudentsCourse(Integer studentId, Integer courseId);

    // update student info
    @Select("""
            Update students
            set student_name = #{student.studentName}, email = #{student.email}, phone_number = #{student.phoneNumber}
            where student_id = #{studentId}
            returning student_id;
            """)
    Integer updateStudentById(@Param("student") StudentRequest studentRequest, Integer studentId);

    // delete student
    @Delete("DELETE FROM students WHERE student_id = #{studentId}")
    void deleteStudentById(Integer studentId);
}
