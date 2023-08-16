package com.eprobj.service;

import com.eprobj.entity.Consult;

import java.util.List;
import java.util.Map;

/**
 * 咨询业务
 *
 */
public interface ConsultService {
    /**
     * 增加咨询
     *
     */
    Integer addconsult(Consult consult);

    /**
     * 咨询回复
     *
     */
    Integer consultAndAnswer(Consult consult);

    /**
     * 通过id查询单个咨询
     *
     */
    Consult queryConsult(int id);

    /**
     * 查询咨询列表
     *
     */
    List<Consult> consultlist(Map<String,Object> map);

    /**
     * 撤销咨询审核，更改openstatus
     * @return
     */
    Integer updateConsultOpenStatus(Consult consult);

    /**
     * 删除单个咨询
     * @return
     */
    Integer deleteConsult(Consult consult);

    /**
     * web查询咨询列表,接口数据
     *
     */
    List<Consult> webconsultlist(Integer openstatus,Integer pageNum,Integer pageSize);

    /**
     * 查询咨询总数
     *
     */
    int consultlistCount(Map<String,Object> map);

    /**
     * 查询web咨询总数
     *
     */
    int webconsultlistCount(int openstatus);

    /**
     * 查询未回复咨询数量
     *
     */
    int queryConsultNoAnswer();
}
