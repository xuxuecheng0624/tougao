package com.eprobj.service;

import com.eprobj.entity.Role;
import com.eprobj.entity.User;
import com.eprobj.mapper.UserMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by WXX on 2019/8/14.LoginService
 */
public interface UserService {

    /**
     * 根据接口查询所用的用户
     */
    List<User> findAllUser();

    User queryInfoByUsername(String username);

    User queryInfoById(int userId);

    List<User> queryInfoByInfo(Map map);

    int queryInfoNumByInfo(Map map);

    boolean registerData(User user);

    boolean updateUser(User user);

    boolean updateUserStatus(User user);


    boolean resetPassword(Map param);

    boolean delUserByIds(List<Integer> userIds);
    /**
     * 根据邮箱来查询用户的信息
     *
     */
    User queryByUserEmail(String email);

    Boolean updatePassword(User user);

    List<Role> roleList(int status);

    List<Role> listByUserId(int id);

    boolean saveRole(int userId,List<Integer> roleIdList);

    boolean delRole(int userId);
}
