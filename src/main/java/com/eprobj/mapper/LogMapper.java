package com.eprobj.mapper;


import com.eprobj.entity.Log;
import com.eprobj.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SysLogMapper
 * @Description TODO
 * @Author kangjian
 * @Date 2019/8/20 10:59
 * @Version 1.0
 **/

public interface LogMapper {
    int save(Log log);

    Long count(Map map);

    List<Log> queryLogList(Map<String, Object> map);

    Log queryLogOne(Integer id);
}
