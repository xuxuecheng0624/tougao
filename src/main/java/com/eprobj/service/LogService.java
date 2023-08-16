package com.eprobj.service;


import com.eprobj.entity.Log;
import com.eprobj.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @ClassName LogService
 * @Description
 * @Author kangjian
 * @Date 2018/10/30 15点26分
 * @Version 1.0
 */
public interface LogService {
    int save(Log log);

    /**
     * 查询总数
     * @param params
     * @return
     */
    Long count(Map params);
    /**
     * 分页查询日志列表
     * @param map
     * @return
     */
    List<Log> queryLogList(Map<String, Object> map);

    /**
     * 根据ID查询日志
     * @param logid
     * @return
     */
    Log queryLogOne(Integer logid);
}
