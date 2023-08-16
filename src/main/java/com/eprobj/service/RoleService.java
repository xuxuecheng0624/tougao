package com.eprobj.service;

import com.eprobj.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> listByUserName(String email);
}
