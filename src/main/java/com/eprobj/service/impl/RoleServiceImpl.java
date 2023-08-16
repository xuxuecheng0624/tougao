package com.eprobj.service.impl;

import com.eprobj.entity.Role;
import com.eprobj.mapper.RoleMapper;
import com.eprobj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Description TODO
 * @Author kangjian
 * @Date 2019/9/23 17:45
 * @Version 1.0
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> listByUserName(String email) {
        return roleMapper.listByUserName(email);
    }
}
