package com.eprobj.mapper;

import com.eprobj.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RoleMapper
 * @Description TODO
 * @Author kangjian
 * @Date 2019/9/14 17:40
 * @Version 1.0
 **/

public interface RoleMapper {

    List<Role> listByUserName(String email);


    List<Role> roleList(@Param("status") int status);

    List<Role> listByUserId(@Param("id") int id);

    boolean saveRole(Map map);

    boolean delRole(@Param("userId") int userId);
}
