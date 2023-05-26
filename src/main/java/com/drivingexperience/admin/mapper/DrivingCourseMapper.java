package com.drivingexperience.admin.mapper;

import com.drivingexperience.admin.dto.DrivingCourseDTO;
import com.drivingexperience.admin.entity.DrivingCourse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DrivingCourseMapper {

    private InstructorMapper instructorMapper;

    public DrivingCourseMapper(InstructorMapper instructorMapper) {
        this.instructorMapper = instructorMapper;
    }

    public DrivingCourseDTO fromDrivingCourse(DrivingCourse drivingCourse) {
        DrivingCourseDTO drivingCourseDTO = new DrivingCourseDTO();
        BeanUtils.copyProperties(drivingCourse, drivingCourseDTO);
        drivingCourseDTO.setInstructor(instructorMapper.fromInstructor(drivingCourse.getInstructor()));
        return drivingCourseDTO;
    }

    public DrivingCourse fromDrivingCourseDTO(DrivingCourseDTO drivingCourseDTO) {
        DrivingCourse drivingCourse = new DrivingCourse();
        BeanUtils.copyProperties(drivingCourseDTO, drivingCourse);
        return drivingCourse;
    }

}
