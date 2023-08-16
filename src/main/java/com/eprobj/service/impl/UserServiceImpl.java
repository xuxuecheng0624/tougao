package com.eprobj.service.impl;

import com.eprobj.entity.Role;
import com.eprobj.entity.User;
import com.eprobj.mapper.EmailProvMapper;
import com.eprobj.mapper.RoleMapper;
import com.eprobj.mapper.UserMapper;
import com.eprobj.service.UserService;
import com.eprobj.utill.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author kangjian
 * @Date 2019/8/21 17:03
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private EmailProvMapper emailProvMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.findAll();
    }

    @Override
    public User queryInfoByUsername(String username) {
        return userMapper.queryInfoByUsername(username);
    }

    @Override
    public User queryInfoById(int userId) {
        return userMapper.queryInfoById(userId);
    }

    @Override
    public List<User> queryInfoByInfo(Map map) {
        return userMapper.queryInfoByInfo(map);
    }

    @Override
    public int queryInfoNumByInfo(Map map) {
        return userMapper.queryInfoNumByInfo(map);
    }

    @Override
    public boolean registerData(User user) {
        String password = user.getPassword();
        Map  map = new HashMap();
        Boolean flag;
        try {
            String newPassword = ShiroUtils.getStrByMD5(password);
            user.setPassword(newPassword);
            user.setCreateDate(new Date());
            user.setStatus(1);
            flag = userMapper.registerData(user);
            User user1 = userMapper.queryByUserEmail(user.getEmail());
            if (user1 != null) {
                map.put("roleId","2");
                map.put("userId",user1.getId());
                roleMapper.saveRole(map);
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }

        return flag;
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public boolean updateUserStatus(User user) {
        return userMapper.updateUserStatus(user);
    }

    @Override
    public boolean resetPassword(Map param) {
        return userMapper.resetPassword(param);
    }

    @Override
    public boolean delUserByIds(List userIds) {
        return userMapper.delUserByIds(userIds);
    }

    @Override
    public User queryByUserEmail ( String email ) {
        return userMapper.queryByUserEmail ( email );
    }

    @Override
    public Boolean updatePassword(User user) {
        user.setNewpassword(ShiroUtils.getStrByMD5(user.getNewpassword()));
        return userMapper.updatePassword(user);
    }

    @Override
    public List<Role> roleList(int status) {
        return roleMapper.roleList(status);
    }

    @Override
    public List<Role> listByUserId(int id) {
        return roleMapper.listByUserId(id);
    }

    @Override
    public boolean saveRole(int userId, List<Integer> roleIdList) {
        roleIdList.forEach(roleid->{
            Map map = new HashMap();
            map.put("roleId",roleid);
            map.put("userId",userId);
            roleMapper.saveRole(map);
        });
        return false;
    }


    @Override
    public boolean delRole(int userId) {
        return roleMapper.delRole(userId);
    }
}
