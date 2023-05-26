package com.drivingexperience.admin.service;

import com.drivingexperience.admin.dto.DrivingCourseDTO;
import com.drivingexperience.admin.entity.DrivingCourse;
import org.springframework.data.domain.Page;

public interface DrivingCourseService {

    DrivingCourse loadDrivingCourseById(Long courseId);
    DrivingCourseDTO createDrivingCourse(DrivingCourseDTO drivingCourseDTO);
    DrivingCourseDTO updateDrivingCourse(DrivingCourseDTO drivingCourseDTO);

    Page<DrivingCourseDTO> findDrivingCoursesByDrivingCoursesName(String keyword, int page, int size);

    void assignStudentToDrivingCourse(Long courseId, Long studentId);

    Page<DrivingCourseDTO> fetchDrivingCoursesForStudent(Long studentId, int page, int size);

    Page<DrivingCourseDTO> fetchNonEnrolledInDrivingCoursesForStudent(Long studentId, int page, int size);

    void removeDrivingCourse(Long courseId);

    Page<DrivingCourseDTO> fetchDrivingCoursesForInstructor(Long instructorId, int page, int size);

}
