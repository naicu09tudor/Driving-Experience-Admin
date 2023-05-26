package com.drivingexperience.admin.web;

import com.drivingexperience.admin.dto.DrivingCourseDTO;
import com.drivingexperience.admin.dto.StudentDTO;
import com.drivingexperience.admin.entity.User;
import com.drivingexperience.admin.service.DrivingCourseService;
import com.drivingexperience.admin.service.StudentService;
import com.drivingexperience.admin.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentRestController {

    private StudentService studentService;

    private UserService userService;

    private DrivingCourseService drivingCourseService;

    public StudentRestController(StudentService studentService, UserService userService, DrivingCourseService drivingCourseService) {
        this.studentService = studentService;
        this.userService = userService;
        this.drivingCourseService = drivingCourseService;
    }
    @GetMapping
    public Page<StudentDTO> searchStudents(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                                           @RequestParam(name = "page", defaultValue = "0") int page,
                                           @RequestParam(name = "size", defaultValue = "5") int size) {
        return studentService.loadStudentsByName(keyword, page, size);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
        studentService.removeStudent(studentId);
    }

    @PostMapping
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO) {
        User user = userService.loadUserByEmail(studentDTO.getUser().getEmail());
        if (user != null) throw new RuntimeException("Email Already Exist");
        return studentService.createStudent(studentDTO);
    }

    @PutMapping("/{studentId}")
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable Long studentId) {
        studentDTO.setStudentId(studentId);
        return studentService.updateStudent(studentDTO);
    }

    @GetMapping("/{studentId}/courses")
    public Page<DrivingCourseDTO> drivingCoursesByStudentId(@PathVariable Long studentId,
                                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                                         @RequestParam(name = "size", defaultValue = "5") int size) {
        return drivingCourseService.fetchDrivingCoursesForStudent(studentId, page, size);
    }

    @GetMapping("/{studentId}/other-courses")
    public Page<DrivingCourseDTO> nonSubscribedCoursesByStudentId(@PathVariable Long studentId,
                                                           @RequestParam(name = "page", defaultValue = "0") int page,
                                                           @RequestParam(name = "size", defaultValue = "5") int size) {
        return drivingCourseService.fetchNonEnrolledInDrivingCoursesForStudent(studentId, page, size);
    }

    @GetMapping("/find")
    public StudentDTO loadStudentByEmail(@RequestParam(name = "email", defaultValue = "") String email) {
        return studentService.loadStudentByEmail(email);
    }
}
