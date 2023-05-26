package com.drivingexperience.admin.service.impl;

import com.drivingexperience.admin.dao.RoleDao;
import com.drivingexperience.admin.entity.Role;
import com.drivingexperience.admin.service.RoleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role createRole(String roleName) {
        return roleDao.save(new Role(roleName));
    }
}
