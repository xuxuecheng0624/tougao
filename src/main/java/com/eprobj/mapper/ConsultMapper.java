package com.eprobj.mapper;

import com.eprobj.entity.Consult;

import java.util.List;
import java.util.Map;

/**
 * Created by xxc
 */
public interface ConsultMapper {
    /**
     * 增加咨询
     *
     */
    Integer addconsult(Consult consult);

    /**
     * 回复咨询
     *
     */
    Integer consultAndAnswer(Consult consult);

    /**
     * 查询单个咨询
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
    List<Consult> webconsultlist(Consult consult);

    /**
     * 查询咨询总数
     *
     */
    Integer consultlistCount(Map<String,Object> map);

    /**
     * 查询web咨询总数
     *
     */
    int webconsultlistCount(Consult consult);

    /**
     * 查询未回复咨询数量
     *
     */
    int queryConsultNoAnswer();
}
