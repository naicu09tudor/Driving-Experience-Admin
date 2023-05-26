package com.drivingexperience.admin.runner;

import com.drivingexperience.admin.dto.DrivingCourseDTO;
import com.drivingexperience.admin.dto.InstructorDTO;
import com.drivingexperience.admin.dto.StudentDTO;
import com.drivingexperience.admin.dto.UserDTO;
import com.drivingexperience.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component // scanned by application context
public class MyRunner implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private DrivingCourseService drivingCourseService;

    @Autowired
    private StudentService studentService;



    @Override
    public void run(String... args) throws Exception {
        createRoles();
        createAdmin();
        createInstructors();
        createDrivingCourses();
        StudentDTO student = createStudent();
        assignCourseToStudent(student);
        createStudent2();
    }

    private void createStudent2() {
        StudentDTO studentDTO = new StudentDTO();
        UserDTO userDTO = new UserDTO();

        studentDTO.setFirstName("Raul");
        studentDTO.setLastName("Costea");
        studentDTO.setLevel("Beginner");
        userDTO.setEmail("student_costearaul@gmail.com");
        userDTO.setPassword("1234");
        studentDTO.setUser(userDTO);
        studentService.createStudent(studentDTO);

        studentDTO.setFirstName("Ionut");
        studentDTO.setLastName("Cojocariu");
        studentDTO.setLevel("Expert");
        userDTO.setEmail("student_cojocariuionut@gmail.com");
        userDTO.setPassword("1234");
        studentDTO.setUser(userDTO);
        studentService.createStudent(studentDTO);

    }

    private void assignCourseToStudent(StudentDTO student) {
        drivingCourseService.assignStudentToDrivingCourse(3L, student.getStudentId());
        drivingCourseService.assignStudentToDrivingCourse(6L, student.getStudentId());
    }

    private StudentDTO createStudent() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName("Sebastian");
        studentDTO.setLastName("Voina");
        studentDTO.setLevel("Advanced");
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("student_voinasebi@gmail.com");
        userDTO.setPassword("1234");
        studentDTO.setUser(userDTO);
        return studentService.createStudent(studentDTO);
    }

    private void createDrivingCourses() {
        DrivingCourseDTO drivingCourseDTO = new DrivingCourseDTO();
        InstructorDTO instructorDTO = new InstructorDTO();

        drivingCourseDTO.setCourseName("Expert Sports Driving Course");
        drivingCourseDTO.setCourseDescription("Master Sports Driving");
        drivingCourseDTO.setCourseDifficulty("Expert");
        instructorDTO.setInstructorId(1L);
        drivingCourseDTO.setInstructor(instructorDTO);
        drivingCourseService.createDrivingCourse(drivingCourseDTO);

        drivingCourseDTO.setCourseName("Beginner Sports Driving Course");
        drivingCourseDTO.setCourseDescription("Introduction to Sports Driving");
        drivingCourseDTO.setCourseDifficulty("Beginner");
        instructorDTO.setInstructorId(1L);
        drivingCourseDTO.setInstructor(instructorDTO);
        drivingCourseService.createDrivingCourse(drivingCourseDTO);

        drivingCourseDTO.setCourseName("Sports Driving Course");
        drivingCourseDTO.setCourseDescription("Advance in Sports Driving");
        drivingCourseDTO.setCourseDifficulty("Advanced");
        instructorDTO.setInstructorId(3L);
        drivingCourseDTO.setInstructor(instructorDTO);
        drivingCourseService.createDrivingCourse(drivingCourseDTO);

        drivingCourseDTO.setCourseName("Expert Defensive Driving Course");
        drivingCourseDTO.setCourseDescription("Master Defensive Driving");
        drivingCourseDTO.setCourseDifficulty("Expert");
        instructorDTO.setInstructorId(2L);
        drivingCourseDTO.setInstructor(instructorDTO);
        drivingCourseService.createDrivingCourse(drivingCourseDTO);

        drivingCourseDTO.setCourseName("Beginner Defensive Driving Course");
        drivingCourseDTO.setCourseDescription("Introduction to Defensive Driving");
        drivingCourseDTO.setCourseDifficulty("Beginner");
        instructorDTO.setInstructorId(2L);
        drivingCourseDTO.setInstructor(instructorDTO);
        drivingCourseService.createDrivingCourse(drivingCourseDTO);

        drivingCourseDTO.setCourseName("Defensive Driving Course");
        drivingCourseDTO.setCourseDescription("Advance in Defensive Driving");
        drivingCourseDTO.setCourseDifficulty("Advanced");
        instructorDTO.setInstructorId(2L);
        drivingCourseDTO.setInstructor(instructorDTO);
        drivingCourseService.createDrivingCourse(drivingCourseDTO);

        drivingCourseDTO.setCourseName("Racing Driving Course");
        drivingCourseDTO.setCourseDescription("Master Racing Driving");
        drivingCourseDTO.setCourseDifficulty("Expert");
        instructorDTO.setInstructorId(4L);
        drivingCourseDTO.setInstructor(instructorDTO);
        drivingCourseService.createDrivingCourse(drivingCourseDTO);
    }

    private void createInstructors() {

        InstructorDTO instructorDTO = new InstructorDTO();
        UserDTO userDTO = new UserDTO();

        instructorDTO.setFirstName("Tudor");
        instructorDTO.setLastName("Naicu");
        instructorDTO.setSummary("Sports Driving Instructor");
        userDTO.setEmail("instructor_naicutudor@gmail.com");
        userDTO.setPassword("1234");
        instructorDTO.setUser(userDTO);
        instructorService.createInstructor(instructorDTO);

        instructorDTO.setFirstName("Silviu");
        instructorDTO.setLastName("Toma");
        instructorDTO.setSummary("Defensive Driving Instructor");
        userDTO.setEmail("instructor_silviutoma@gmail.com");
        userDTO.setPassword("1234");
        instructorDTO.setUser(userDTO);
        instructorService.createInstructor(instructorDTO);

        instructorDTO.setFirstName("Radu");
        instructorDTO.setLastName("Ariciu");
        instructorDTO.setSummary("Sports Driving Instructor");
        userDTO.setEmail("instructor_ariciuradu@gmail.com");
        userDTO.setPassword("1234");
        instructorDTO.setUser(userDTO);
        instructorService.createInstructor(instructorDTO);

        instructorDTO.setFirstName("Thomas");
        instructorDTO.setLastName("Holland");
        instructorDTO.setSummary("Racing Driving Instructor");
        userDTO.setEmail("instructor_thomasholland@gmail.com");
        userDTO.setPassword("1234");
        instructorDTO.setUser(userDTO);
        instructorService.createInstructor(instructorDTO);
    }

    private void createAdmin() {
        userService.createUser("admin@gmail.com", "1234");
        userService.assignRoleToUser("admin@gmail.com", "Admin");
    }

    private void createRoles() {
        Arrays.asList("Admin", "Instructor", "Student").forEach(role -> roleService.createRole(role));
    }
}
