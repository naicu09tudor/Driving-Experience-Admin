package com.drivingexperience.admin.dao;

import com.drivingexperience.admin.entity.DrivingCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrivingCourseDao extends JpaRepository<DrivingCourse, Long> {
    // configuration to use all the methods that are available in the JpaRepo

    List<DrivingCourse> findDrivingCourseByCourseNameContains(String keyword);

    @Query(value = "select * from DrivingCourses as c where c.course_id in (select e.course_id from enrolled_in as e where e.student_id=:studentId)", nativeQuery = true)
    List<DrivingCourse> getDrivingCourseByStudentId(@Param("studentId") Long studentId);

}
