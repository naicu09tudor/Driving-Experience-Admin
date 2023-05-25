package com.drivingexperience.admin.dao;

import com.drivingexperience.admin.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstructorDao extends JpaRepository<Instructor, Long> {

    /*
    we combine the first and last name to search by name
     */
    @Query(value = "select i from Instructor as i where i.firstName like %:name% or i.lastName like %:name%")
    List<Instructor> findInstructorsByName(@Param("name") String name);

    @Query(value = "select i from Instructor as i where i.user.email=:email")
    List<Instructor> findInstructorByEmail(@Param("email") String email);

}
