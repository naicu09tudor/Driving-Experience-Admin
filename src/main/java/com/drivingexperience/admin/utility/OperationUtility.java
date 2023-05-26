package com.drivingexperience.admin.utility;

/*
 Allows us to perform CRUD operations
 */

import com.drivingexperience.admin.dao.*;
import com.drivingexperience.admin.entity.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public class OperationUtility {

    public static void usersOperations(UserDao userDao){

        createUsers(userDao);
        //updateUser(userDao);
        //deleteUser(userDao);
        //fetchUsers(userDao);
    }

    public static void rolesOperations(RoleDao roleDao){

        createRoles(roleDao);
        //updateRole(roleDao);
        //deleteRole(roleDao);
        //fetchRole(roleDao);

    }

    public static void instructorsOperations(UserDao userDao, InstructorDao instructorDao, RoleDao roleDao) {
        createInstructors(userDao, instructorDao, roleDao);
        //updateInstructor(instructorDao);
        //removeInstructor(instructorDao);
        //fetchInstructors(instructorDao);
    }

    public static void studentsOperations(UserDao userDao, StudentDao studentDao, RoleDao roleDao) {
        createStudents(userDao, studentDao, roleDao);
        //updateStudent(studentDao);
        //removeStudent(studentDao);
        //fetchStudents(studentDao);
    }

    public static void drivingCoursesOperations(DrivingCourseDao drivingCourseDao, InstructorDao instructorDao, StudentDao studentDao) {
         //createDrivingCourses(drivingCourseDao, instructorDao);
         //updateDrivingCourses(drivingCourseDao);
         //deleteDrivingCourse(drivingCourseDao);
         //fetchDrivingCourses(drivingCourseDao);
         //assignStudentsToCourse(drivingCourseDao, studentDao);
         fetchCoursesForStudent(drivingCourseDao);
    }


    private static void createDrivingCourses(DrivingCourseDao drivingCourseDao, InstructorDao instructorDao) {
        Instructor instructor = instructorDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("Instructor Not Found"));

        DrivingCourse drivingCourse1 = new DrivingCourse("Expert Sports Driving Course", "Expert", "Master Sports Driving", instructor);
        drivingCourseDao.save(drivingCourse1);
        DrivingCourse drivingCourse2 = new DrivingCourse("Beginner Defensive Driving Course", "Beginner", "Introduction to Defensive Driving", instructor);
        drivingCourseDao.save(drivingCourse2);
    }

    private static void updateDrivingCourses(DrivingCourseDao drivingCourseDao) {
        DrivingCourse drivingCourse = drivingCourseDao.findById(2L).orElseThrow(() -> new EntityNotFoundException("Course Not Found"));
        drivingCourse.setCourseDifficulty("New Driver");
        drivingCourseDao.save(drivingCourse);
    }

    private static void deleteDrivingCourse(DrivingCourseDao drivingCourseDao) {
        drivingCourseDao.deleteById(2L);
    }

    private static void fetchDrivingCourses(DrivingCourseDao drivingCourseDao) {
        drivingCourseDao.findAll().forEach(drivingCourse -> System.out.println(drivingCourse.toString()));
    }

    private static void assignStudentsToCourse(DrivingCourseDao drivingCourseDao, StudentDao studentDao) {
        Optional<Student> student1 = studentDao.findById(1L);
        Optional<Student> student2 = studentDao.findById(2L);
        DrivingCourse drivingCourse = drivingCourseDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("Course Not Found"));

        student1.ifPresent(drivingCourse::assingStudentToCourse);
        student2.ifPresent(drivingCourse::assingStudentToCourse);
        drivingCourseDao.save(drivingCourse);
    }

    private static void fetchCoursesForStudent(DrivingCourseDao drivingCourseDao) {
        drivingCourseDao.getDrivingCourseByStudentId(1L).forEach(course -> System.out.println(course.toString()));
    }

    private static void createStudents(UserDao userDao, StudentDao studentDao, RoleDao roleDao) {
        Role role = roleDao.findByName("Student");
        if (role == null) throw new EntityNotFoundException("Role Not Found");

        User user1 = new User("student_sebivoina@gmail.com", "pass1");
        user1.assignRoleToUser(role);
        userDao.save(user1);
        Student student1 = new Student("Sebastian", "Voina", "Experienced Driver", user1);
        studentDao.save(student1);

        User user2 = new User("student_raulcostea@gmail.com", "pass2");
        user2.assignRoleToUser(role);
        userDao.save(user2);
        Student student2 = new Student("Raul", "Costea", "New Driver", user2);
        studentDao.save(student2);
    }

    private static void updateStudent(StudentDao studentDao) {
        Student student = studentDao.findById(2L).orElseThrow(() -> new EntityNotFoundException("Student Not Found"));
        student.setFirstName("Sebastian-Mihai");
        student.setLastName("Voinaa");
        studentDao.save(student);
    }


    private static void removeStudent(StudentDao studentDao) {
        studentDao.deleteById(2L);
    }

    private static void fetchStudents(StudentDao studentDao) {
        studentDao.findAll().forEach(student -> System.out.println(student.toString()));
    }


    private static void createInstructors(UserDao userDao, InstructorDao instructorDao, RoleDao roleDao) {

        Role role = roleDao.findByName("Instructor");
        if (role == null) throw new EntityNotFoundException("Role Not Found");

        User user1 = new User("instructor_naicutudor@gmail.com", "pass1");
        user1.assignRoleToUser(role);
        userDao.save(user1);
        Instructor instructor1 = new Instructor("Tudor", "Naicu", "Sports Driving Instructor", user1);
        instructorDao.save(instructor1);

        User user2 = new User("instructor_silviutoma@gmail.com", "pass2");
        user2.assignRoleToUser(role);
        userDao.save(user2);
        Instructor instructor2 = new Instructor("Silviu", "Toma", "Defensive Driving Instructor", user2);
        instructorDao.save(instructor2);

    }

    private static void updateInstructor(InstructorDao instructorDao) {
        Instructor instructor = instructorDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("Instructor Not Found"));
        instructor.setSummary("Certified Sport Driving Instructor");
        instructorDao.save(instructor);
    }

    private static void removeInstructor(InstructorDao instructorDao) {
        instructorDao.deleteById(2L);
    }

    private static void fetchInstructors(InstructorDao instructorDao) {
        instructorDao.findAll().forEach(instructor -> System.out.println(instructor.toString()));
    }

    public static void assignRolesToUsers(UserDao userDao, RoleDao roleDao){
        Role role = roleDao.findByName("Admin");
        if(role == null) throw new EntityNotFoundException("Role not found");
        List<User> users = userDao.findAll();
        users.forEach(user -> {
            user.assignRoleToUser(role);
            userDao.save(user);
        });
    }

    private static void fetchRole(RoleDao roleDao) {
        roleDao.findAll().forEach(role -> System.out.println(role.toString()));
    }

    private static void deleteRole(RoleDao roleDao) {

        roleDao.deleteById(2L);

    }

    private static void updateRole(RoleDao roleDao) {

        Role role = roleDao.findById(1L).orElseThrow(()-> new EntityNotFoundException("Role Not Found"));
        role.setName("NewAdmin");
        roleDao.save(role);
    }

    private static void createRoles(RoleDao roleDao) {

        Role role1 = new Role("Admin");
        roleDao.save(role1);

        Role role2 = new Role("Instructor");
        roleDao.save(role2);

        Role role3 = new Role("Student");
        roleDao.save(role3);
    }

    private static void fetchUsers(UserDao userDao) {

        userDao.findAll().forEach(user -> System.out.println(user.toString()));

    }

    private static void deleteUser(UserDao userDao) {

        User user = userDao.findById(3L).orElseThrow(()-> new EntityNotFoundException("User Not Found"));
        userDao.delete(user);

    }

    private static void updateUser(UserDao userDao) {

        User user = userDao.findById(2L).orElseThrow(()-> new EntityNotFoundException("User Not Found"));
        user.setEmail("newEmail@gmail.com");
        userDao.save(user);

    }

    private static void createUsers(UserDao userDao) {

        User user1 = new User("user1@gmail.com", "pass1");
        userDao.save(user1);

        User user2 = new User("user2@gmail.com", "pass2");
        userDao.save(user2);

        User user3 = new User("user3@gmail.com", "pass3");
        userDao.save(user3);

        User user4 = new User("user4@gmail.com", "pass4");
        userDao.save(user4);

    }




}
