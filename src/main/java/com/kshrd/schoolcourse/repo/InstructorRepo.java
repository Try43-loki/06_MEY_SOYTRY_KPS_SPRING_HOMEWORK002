package com.kshrd.schoolcourse.repo;

import com.kshrd.schoolcourse.model.Instructor;
import com.kshrd.schoolcourse.model.request.InstructorRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstructorRepo {

    // get all
    @Select("""
            select * from instructors
            offset #{limit} * (#{offset} - 1)
                    limit #{limit}
            """)
    @Results(id = "instructorMapper", value = {
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name")
    })
    List<Instructor> findAllInstructors(Integer offset, Integer limit);

    // get by id
    @Select("""
                    select * from instructors
                    where instructor_id = #{id}
            """)
    @ResultMap("instructorMapper")
    Instructor findInstructorById(int id);

    // insert instructor
    @Select("""
            insert into instructors (instructor_name, email)
            values (#{instructor.instructorName}, #{instructor.email})
            returning *;
            """)
    @ResultMap("instructorMapper")
    Instructor insertInstructor(@Param("instructor") InstructorRequest instructorRequest);

    // update
    @Select("""
                UPDATE instructors
                SET instructor_name = #{instructor.instructorName},
                    email = #{instructor.email}
                WHERE instructor_id = #{id}
                RETURNING *;
            """)
    @ResultMap("instructorMapper")
    Instructor updateInstructor(int id, @Param("instructor") InstructorRequest instructorRequest);

    // delete
    @Delete("delete from instructors where instructor_id = #{id}")
    void deleteInstructor(int id);
}
