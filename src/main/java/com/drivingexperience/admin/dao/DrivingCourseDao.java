package com.drivingexperience.admin.dao;

import com.drivingexperience.admin.entity.DrivingCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


// DATA ACCESS OBJECT - queries for data access to the database
public interface DrivingCourseDao extends JpaRepository<DrivingCourse, Long> {

    // configuration to use all the methods that are available in the JpaRepo

    Page<DrivingCourse> findDrivingCourseByCourseNameContains(String keyword, Pageable pageable);

    @Query(value = "select * from driving_courses as c where c.course_id in (select e.course_id from enrolled_in as e where e.student_id=:studentId)", nativeQuery = true)
    Page<DrivingCourse> getDrivingCourseByStudentId(@Param("studentId") Long studentId, Pageable pageable);

    @Query(value = "select * from driving_courses as c where c.course_id not in (select e.course_id from enrolled_in as e where e.student_id=:studentId)", nativeQuery = true)
    Page<DrivingCourse> getNonEnrolledInDrivingCoursesByStudentId(@Param("studentId") Long studentId, Pageable pageable);

    @Query(value = "select c from DrivingCourse as c where c.instructor.instructorId=:instructorId")
    Page<DrivingCourse> getCoursesByInstructorId(@Param("instructorId") Long instructorId, Pageable pageable);

}
