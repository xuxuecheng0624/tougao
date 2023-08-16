package com.eprobj.service.impl;

import com.eprobj.entity.Consult;
import com.eprobj.mapper.ConsultMapper;
import com.eprobj.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 咨询业务处理
 *
 */
@Service
public class ConsultServiceImpl implements ConsultService {
    @Autowired
    private ConsultMapper consultMapper;
    /**
     * 增加咨询
     *
     */
    @Override
    public Integer addconsult(Consult consult) {
        consult.setStatus(0);
        consult.setOpenstatus(0);
        consult.setCreateDate(new Date());
        return consultMapper.addconsult(consult);
    }
    /**
     * 增加咨询回复
     *
     */
    @Override
    public Integer consultAndAnswer(Consult consult) {
        return consultMapper.consultAndAnswer(consult);
    }
    /**
     * 通过id查询单个咨询
     *
     */
    @Override
    public Consult queryConsult(int id) {
        return consultMapper.queryConsult(id);
    }

    /**
     * 查询咨询列表
     *
     */
    @Override
    public List<Consult> consultlist(Map<String,Object> map) {
        return consultMapper.consultlist(map);
    }


    /**
     * 撤销咨询审核，更改openstatus
     * @return
     */
    @Override
    public Integer updateConsultOpenStatus(Consult consult) {
        return consultMapper.updateConsultOpenStatus(consult);
    }

    /**
     * 删除单个咨询
     * @return
     */
    @Override
    public Integer deleteConsult(Consult consult) {
        return consultMapper.deleteConsult(consult);
    }

    /**
     * web查询咨询列表,接口数据
     *
     */
    @Override
    public List<Consult> webconsultlist(Integer openstatus,Integer pageNum,Integer pageSize) {
        Consult consult = new Consult();
        consult.setOpenstatus(openstatus);
        if(pageNum<=0){
            pageNum = 1;
        }
      /*  if(pageSize%10>0){
            pageSize = 10;
        }*/
        consult.setStatus((pageNum-1)*pageSize);
        consult.setExamine(pageSize);
        return consultMapper.webconsultlist(consult);
    }

    /**
     * 查询咨询总数
     *
     */
    @Override
    public int consultlistCount(Map<String,Object> map) {
        return consultMapper.consultlistCount(map);
    }

    /**
     * 查询web咨询总数
     *
     */
    @Override
    public int webconsultlistCount(int openstatus) {
        Consult consult = new Consult();
        if(openstatus!=1){
            consult.setOpenstatus(1);
        }else{
            consult.setOpenstatus(openstatus);
        }
        return consultMapper.webconsultlistCount(consult);
    }

    /**
     * 查询未回复咨询数量
     *
     */
    @Override
    public int queryConsultNoAnswer() {
        return consultMapper.queryConsultNoAnswer();
    }
}
