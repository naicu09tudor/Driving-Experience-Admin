package com.drivingexperience.admin.dao;

import com.drivingexperience.admin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
