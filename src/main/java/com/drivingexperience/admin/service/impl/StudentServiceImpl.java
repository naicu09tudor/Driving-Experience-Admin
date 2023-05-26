package com.drivingexperience.admin.service.impl;

import com.drivingexperience.admin.dao.StudentDao;
import com.drivingexperience.admin.dto.StudentDTO;
import com.drivingexperience.admin.entity.DrivingCourse;
import com.drivingexperience.admin.entity.Student;
import com.drivingexperience.admin.entity.User;
import com.drivingexperience.admin.mapper.StudentMapper;
import com.drivingexperience.admin.service.StudentService;
import com.drivingexperience.admin.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    private StudentMapper studentMapper;

    private UserService userService;

    public StudentServiceImpl(StudentDao studentDao, StudentMapper studentMapper, UserService userService) {
        this.studentDao = studentDao;
        this.studentMapper = studentMapper;
        this.userService = userService;
    }


    @Override
    public Student loadStudentById(Long studentId) {
        return studentDao.findById(studentId).orElseThrow(()-> new EntityNotFoundException("Student with ID " + studentId + "Not Found!"));
    }

    @Override
    public Page<StudentDTO> loadStudentsByName(String name, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Student> studentsPage = studentDao.findStudentsByName(name, pageRequest);
        return new PageImpl<>(studentsPage.getContent().stream().map(student -> studentMapper.fromStudent(student)).collect(Collectors.toList()), pageRequest, studentsPage.getTotalElements());
    }

    @Override
    public StudentDTO loadStudentByEmail(String email) {
        return studentMapper.fromStudent(studentDao.findStudentByEmail(email));
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        User user = userService.createUser(studentDTO.getUser().getEmail(), studentDTO.getUser().getPassword());
        userService.assignRoleToUser(user.getEmail(), "Student");
        Student student = studentMapper.fromStudentDTO(studentDTO);
        student.setUser(user);
        Student savedStudent = studentDao.save(student);
        return studentMapper.fromStudent(savedStudent);
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Student loadedStudent = loadStudentById(studentDTO.getStudentId());
        Student student = studentMapper.fromStudentDTO(studentDTO);
        student.setUser(loadedStudent.getUser());
        student.setDrivingCourses(loadedStudent.getDrivingCourses());
        Student updatedStudent = studentDao.save(student);
        return studentMapper.fromStudent(updatedStudent);
    }

    @Override
    public void removeStudent(Long studentId) {
        Student student = loadStudentById(studentId);
        Iterator<DrivingCourse> drivingCourseIterator = student.getDrivingCourses().iterator();
        if (drivingCourseIterator.hasNext()) {
            DrivingCourse drivingCourse = drivingCourseIterator.next();
            drivingCourse.removeStudentFromCourse(student);
        }
        studentDao.deleteById(studentId);
    }
}
