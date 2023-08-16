package com.eprobj.service;

import com.eprobj.entity.Contributorclicklog;
import com.eprobj.entity.Watchipclass;

import java.util.List;
import java.util.Map;

/**
 * 静态稿件记录
 *
 */

public interface ContributeClickLogService {
    /**
     * 增加稿件记录
     * @param contributorclicklog
     * @return
     */
    Integer insertContribueClickLog(Contributorclicklog contributorclicklog);
    /**
     * 查询单个稿件访问记录
     * @return
     */
    Contributorclicklog queryContribueClickLog(Integer cid);

    /**
     * 查询最新访问ip
     * @return
     */
    Watchipclass queryWatchipclass(Integer cid,String ip);

    /**
     * 增加稿件访问记录
     * @return
     */
    Integer insertWatchipclass(Watchipclass watchipclass);
    /**
     * 增加稿件访问量
     * @return
     */
    Integer updateContribueClickLog(Contributorclicklog queryContributorclicklog);

    /**
     * 查询访问记录list
     * @return
     */
    List<Contributorclicklog> contribueClickLogList(Map<String,Object> map);

    /**
     * 查询访问记录总数
     * @return
     */
    int contribueClickLogListCount(String searchword);
}
