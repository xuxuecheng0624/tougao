package com.eprobj.service.impl;

import com.eprobj.entity.Contributorclicklog;
import com.eprobj.entity.Watchipclass;
import com.eprobj.mapper.ContributorclicklogMapper;
import com.eprobj.service.ContributeClickLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 静态稿件记录
 *
 */
@Service
public class ContributeClickLogServiceImpl implements ContributeClickLogService {

    @Autowired
    private ContributorclicklogMapper contributorclicklogMapper;

    /**
     * 增加稿件记录
     * @param contributorclicklog
     * @return
     */
    @Override
    public Integer insertContribueClickLog(Contributorclicklog contributorclicklog) {
        return contributorclicklogMapper.insertContribueClickLog(contributorclicklog);
    }

    /**
     * 查询单个稿件访问记录
     * @return
     */
    @Override
    public Contributorclicklog queryContribueClickLog(Integer cid) {
        return contributorclicklogMapper.queryContribueClickLog(cid);
    }

    /**
     * 查询最新访问ip
     * @return
     */
    @Override
    public Watchipclass queryWatchipclass(Integer cid,String ip) {
        return contributorclicklogMapper.queryWatchipclass(cid,ip);
    }
    /**
     * 增加稿件访问记录
     * @param watchipclass
     * @return
     */
    @Override
    public Integer insertWatchipclass(Watchipclass watchipclass) {
        return contributorclicklogMapper.insertWatchipclass(watchipclass);
    }
    /**
     * 增加稿件访问量
     * @param queryContributorclicklog
     * @return
     */
    @Override
    public Integer updateContribueClickLog(Contributorclicklog queryContributorclicklog) {
        return contributorclicklogMapper.updateContribueClickLog(queryContributorclicklog);
    }

    /**
     * 查询访问记录list
     * @return
     */
    @Override
    public List<Contributorclicklog> contribueClickLogList(Map<String,Object> map) {
        return contributorclicklogMapper.contribueClickLogList(map);
    }

    /**
     * 查询访问记录总数
     * @return
     */
    @Override
    public int contribueClickLogListCount(String searchword) {
        Map<String,Object> map = new HashMap<String,Object>();
        if(searchword!=null&&searchword!=""){
            map.put("ctitle",searchword);
            map.put("cchannel",searchword);
        }
        return contributorclicklogMapper.contribueClickLogListCount(map);
    }
}
