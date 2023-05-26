package com.drivingexperience.admin;

import com.drivingexperience.admin.dao.*;
import com.drivingexperience.admin.utility.OperationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DrivingexperienceAdminApplication implements CommandLineRunner {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DrivingCourseDao drivingCourseDao;

    @Autowired
    private InstructorDao instructorDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private RoleDao roleDao;

    public static void main(String[] args) {
        SpringApplication.run(DrivingexperienceAdminApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        //OperationUtility.usersOperations(userDao);
        //OperationUtility.rolesOperations(roleDao);
        //OperationUtility.assignRolesToUsers(userDao,roleDao);
        //OperationUtility.instructorsOperations(userDao,instructorDao,roleDao);
        //OperationUtility.studentsOperations(userDao,studentDao,roleDao);
        OperationUtility.drivingCoursesOperations(drivingCourseDao,instructorDao,studentDao);

    }
}
