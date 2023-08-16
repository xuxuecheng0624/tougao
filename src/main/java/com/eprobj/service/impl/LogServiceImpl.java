package com.eprobj.service.impl;

import com.eprobj.entity.Log;
import com.eprobj.entity.User;
import com.eprobj.mapper.LogMapper;
import com.eprobj.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName LogServiceImpl
 * @Description TODO
 * @Author kangjian
 * @Date 2019/9/9 11:36
 * @Version 1.0
 **/
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public int save(Log log) {
        return logMapper.save(log);
    }

    @Override
    public Long count(Map params) {
        return logMapper.count(params);
    }

    @Override
    public List<Log> queryLogList(Map<String, Object> map) {
        return logMapper.queryLogList(map);
    }

    @Override
    public Log queryLogOne(Integer id) {
        return logMapper.queryLogOne(id);
    }
}
