package com.eprobj.service.impl;

import com.eprobj.entity.Channel;
import com.eprobj.mapper.ChannelMapper;
import com.eprobj.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ChannelServiceImpl
 * @Description TODO
 * @Author kangjian
 * @Date 2019/10/12 17:21
 * @Version 1.0
 **/
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public int save(Channel channel) {
        channel.setCreateTime(new Date());
        return channelMapper.save(channel);
    }

    @Override
    public Long count(Map params) {
        return channelMapper.count(params);
    }

    @Override
    public List<Channel> page(Map params) {
        return channelMapper.page(params);
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
        try {
            if (channelMapper.deleteByIds(ids)<=0)
                throw new Exception("系统异常");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Channel getById(String id) {
        return channelMapper.getById(id);
    }

    @Override
    public int updateById(Channel channel) {
        return channelMapper.updateById(channel);
    }

    @Override
    public Channel findInfoByName(String chnlName) {
        return channelMapper.findInfoByName(chnlName);
    }
}
