package com.eprobj.mapper;

import com.eprobj.entity.Role;
import com.eprobj.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by WXX on 2019/8/13.
 */
@Component
public interface UserMapper {
    List<User> findAll();

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 将数据封装到Map类型中
     */
    User queryInfoByUsername(String username);

    /**
     * 根据用户名id用户信息
     * @param userId
     * @return
     */
    User queryInfoById(@Param("userId") int userId);

    /**
     * 根据用户信息查询用户列表
     * @param map 用户名
     */
    List<User> queryInfoByInfo(Map map);

    int queryInfoNumByInfo(Map map);

    /**
     * 注册功能
     * @return
     */
    boolean registerData(User user);


    /**
     * 修改用户
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 修改用户状态
     * @param user
     * @return
     */
    boolean updateUserStatus(User user);

    /**
     * 重置密码
     * @param map
     * @return
     */
    boolean resetPassword(Map map);

    /**
     * 删除用户
     * @param userIds
     * @return
     */
    boolean delUserByIds(@Param("userIds") List userIds);

    /**
     * 根据邮箱来查询用户的信息
     *
     */
    User queryByUserEmail(String email);

    Boolean updatePassword(User user);

    List<Role> listByEmail(String email);

    int updateUserCode(@Param("email") String email, @Param("code") String code);
}
