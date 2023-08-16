package com.eprobj.mapper;

import com.eprobj.entity.Channel;
import com.eprobj.entity.Documents;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChannelMapper {

    int save(Channel channel);

    Long count(Map map);

    List<Channel> page(Map map);

    int deleteByIds(@Param("ids") List<Integer> docIds);

    Channel getById(String id);

    int updateById(Channel channel);

    Channel findInfoByName(String chnlName);
}
