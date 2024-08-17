package com.drivingexperience.admin.service.impl;

import com.drivingexperience.admin.dao.DrivingCourseDao;
import com.drivingexperience.admin.dao.InstructorDao;
import com.drivingexperience.admin.dao.StudentDao;
import com.drivingexperience.admin.dto.DrivingCourseDTO;
import com.drivingexperience.admin.entity.DrivingCourse;
import com.drivingexperience.admin.entity.Instructor;
import com.drivingexperience.admin.entity.Student;
import com.drivingexperience.admin.mapper.DrivingCourseMapper;
import com.drivingexperience.admin.service.DrivingCourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.stream.Collectors;


// Logica aplicatiei este in Service Layer
@Service
@Transactional
public class DrivingCourseServiceImpl implements DrivingCourseService {

    private DrivingCourseDao drivingCourseDao;

    private DrivingCourseMapper drivingCourseMapper;

    private InstructorDao instructorDao;

    private StudentDao studentDao;

    public DrivingCourseServiceImpl(DrivingCourseDao drivingCourseDao, DrivingCourseMapper drivingCourseMapper, InstructorDao instructorDao, StudentDao studentDao) {
        this.drivingCourseDao = drivingCourseDao;
        this.drivingCourseMapper = drivingCourseMapper;
        this.instructorDao = instructorDao;
        this.studentDao = studentDao;
    }

    @Override
    public DrivingCourse loadDrivingCourseById(Long courseId) {
        return drivingCourseDao.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course with ID " + courseId + " Not Found"));
    }

    @Override
    public DrivingCourseDTO createDrivingCourse(DrivingCourseDTO drivingCourseDTO) {
        DrivingCourse drivingCourse = drivingCourseMapper.fromDrivingCourseDTO(drivingCourseDTO);
        Instructor instructor = instructorDao.findById(drivingCourseDTO.getInstructor().getInstructorId()).orElseThrow(() -> new EntityNotFoundException("Instructor with ID " + drivingCourseDTO.getInstructor().getInstructorId() + " Not Found"));
        drivingCourse.setInstructor(instructor);
        DrivingCourse savedCourse = drivingCourseDao.save(drivingCourse);
        return drivingCourseMapper.fromDrivingCourse(savedCourse);
    }

    @Override
    public DrivingCourseDTO updateDrivingCourse(DrivingCourseDTO drivingCourseDTO) {
        DrivingCourse loadedCourse = loadDrivingCourseById(drivingCourseDTO.getCourseId());
        Instructor instructor = instructorDao.findById(drivingCourseDTO.getInstructor().getInstructorId()).orElseThrow(() -> new EntityNotFoundException("Instructor with ID " + drivingCourseDTO.getInstructor().getInstructorId() + " Not Found"));
        DrivingCourse drivingCourse = drivingCourseMapper.fromDrivingCourseDTO(drivingCourseDTO);
        drivingCourse.setInstructor(instructor);
        drivingCourse.setStudents(loadedCourse.getStudents());
        DrivingCourse updatedCourse = drivingCourseDao.save(drivingCourse);
        return drivingCourseMapper.fromDrivingCourse(updatedCourse);
    }

    @Override
    public Page<DrivingCourseDTO> findDrivingCoursesByDrivingCoursesName(String keyword, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<DrivingCourse> coursesPage = drivingCourseDao.findDrivingCourseByCourseNameContains(keyword, pageRequest);
        return new PageImpl<>(coursesPage.getContent().stream().map(course -> drivingCourseMapper.fromDrivingCourse(course)).collect(Collectors.toList()), pageRequest, coursesPage.getTotalElements());
    }

    @Override
    public void assignStudentToDrivingCourse(Long courseId, Long studentId) {
        Student student = studentDao.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student with ID " + studentId + " Not Found"));
        DrivingCourse drivingCourse = loadDrivingCourseById(courseId);
        drivingCourse.assingStudentToCourse(student);
    }

    @Override
    public Page<DrivingCourseDTO> fetchDrivingCoursesForStudent(Long studentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<DrivingCourse> studentCoursesPage = drivingCourseDao.getDrivingCourseByStudentId(studentId,pageRequest);
        return new PageImpl<>(studentCoursesPage.getContent().stream().map(course -> drivingCourseMapper.fromDrivingCourse(course)).collect(Collectors.toList()), pageRequest, studentCoursesPage.getTotalElements());
    }

    @Override
    public Page<DrivingCourseDTO> fetchNonEnrolledInDrivingCoursesForStudent(Long studentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<DrivingCourse> nonEnrolledInCoursesPage = drivingCourseDao.getNonEnrolledInDrivingCoursesByStudentId(studentId,pageRequest);
        return new PageImpl<>(nonEnrolledInCoursesPage.getContent().stream().map(course -> drivingCourseMapper.fromDrivingCourse(course)).collect(Collectors.toList()), pageRequest, nonEnrolledInCoursesPage.getTotalElements());
    }

    @Override
    public void removeDrivingCourse(Long courseId) {
        drivingCourseDao.deleteById(courseId);
    }

    @Override
    public Page<DrivingCourseDTO> fetchDrivingCoursesForInstructor(Long instructorId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<DrivingCourse> instructorCoursesPage = drivingCourseDao.getCoursesByInstructorId(instructorId, pageRequest);
        return new PageImpl<>(instructorCoursesPage.getContent().stream().map(course -> drivingCourseMapper.fromDrivingCourse(course)).collect(Collectors.toList()), pageRequest, instructorCoursesPage.getTotalElements());
    }
}
