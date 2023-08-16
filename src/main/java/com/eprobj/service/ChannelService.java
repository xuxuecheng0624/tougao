package com.eprobj.service;

import com.eprobj.entity.Channel;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ChannelService
 * @Description TODO
 * @Author kangjian
 * @Date 2019/10/12 17:18
 * @Version 1.0
 **/

public interface ChannelService {

    int save(Channel channel);

    Long count(Map params);

    List<Channel> page(Map params);

    boolean  deleteByIds(List<Integer> ids);

    Channel getById(String id);

    int updateById(Channel channel);

    Channel findInfoByName(String chnlName);
}
