package com.eprobj.controllers;

import com.eprobj.config.MyLog;
import com.eprobj.entity.Consult;
import com.eprobj.service.ConsultService;
import com.eprobj.service.EmailProvService;
import com.eprobj.utill.MailUtils;
import com.eprobj.utill.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.eprobj.enums.OperationType;
import com.eprobj.enums.OperationUnit;

import java.util.*;

/**
 * Created by xxc on 2019/9/10
 * 我要咨询
 *
 */
@Controller
public class ConsultController {

    @Autowired
    private ConsultService consultService;

    @Autowired
    private EmailProvService emailProvService;


    @RequestMapping("/consulttoList")
    public String consulttoList() {
        return "views/fzw/zxlylb";
    }

    @RequestMapping("/consultWyzx")
    public String consultWyzx() {
        return "views/fzw/wyzx";
    }
    @RequestMapping("/webzxlist")
    public String webzxlist() {
        return "views/fzw/webzxlist";
    }
    @RequestMapping("/consultWyzx1")
    public String consultWyzx1() {
        return "views/fzw/wyzx1";
    }
    @RequestMapping("/webzxlist1")
    public String webzxlist1() {
        return "views/fzw/webzxlist1";
    }
    /**
     * 咨询列表
     * @return
     */
   /* @MyLog(detail = "查询列表",operationType = OperationType.SELECT,operationUnit = OperationUnit.CONSULT)*/
    @RequestMapping("/consultlist")
    @ResponseBody
    public String consultlist(
            @RequestParam(required = false) String searchword,
            @RequestParam(required = false) String field,
            @RequestParam(required = false) String order,
            @RequestParam(required = false) int page,
            @RequestParam(required = false) int limit,
            @RequestParam(required = false) String notconsult){

        List<Consult> list;
        Map<String,Object> map = new HashMap<String,Object>();
        if(notconsult!=null) {
            if (notconsult .equals( "2")) {
                map.put("openstatus", 2);
            } else {
                map.put("openstatus", 1);
            }
        }
        map.put("title",searchword);
        map.put("name",searchword);
        if(field!=null){
            map.put("field",field);
        }else{
            map.put("field","createDate");
        }
        if(order!=null){
            map.put("order",order);
        }else{
            map.put("order","desc");
        }
        if(limit%10>0){
            limit = 10;
        }
        map.put("page",(page-1)*limit);
        map.put("limit",limit);

        list=consultService.consultlist(map);
        int countnum =consultService.consultlistCount(map);
        return  RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "查询成功",list,countnum);
    }

    /**
     * 提供静态页面咨询列表
     * @return
     */
    @RequestMapping("/webconsultlist")
    @ResponseBody
    public Map webconsultlist(
                              @RequestParam(required = false) int pageNum,
                              @RequestParam(required = false) int pageSize){

        List<Consult> list;
        int openstatus = 1;
        list=consultService.webconsultlist(openstatus,pageNum,pageSize);
        int countnum =consultService.webconsultlistCount(openstatus);
        Map<String, Object> mapList = new HashMap<String, Object>();
        mapList.put("list",list);
        if(countnum%pageSize==0){
            mapList.put("pageNum",countnum/pageSize);
        }else{
            mapList.put("pageNum",countnum/pageSize+1);
        }
        mapList.put("pageSize",pageSize);
        mapList.put("dataCount",countnum);

        return mapList;
    }

    /**
     * 增加咨询
     * @return
     */
    @MyLog(detail = "增加咨询",operationType = OperationType.INSERT,operationUnit = OperationUnit.CONSULT)
    @RequestMapping("/addconsult")
    @ResponseBody
    public String addconsult(
                             @RequestParam(required = true) String title,
                             @RequestParam(required = true) String name,
                             @RequestParam(required = true) String phone,
                             @RequestParam(required = true) String email,
                             @RequestParam(required = true) String unit,
                             @RequestParam(required = true) String content
                             ){
        Consult consult = new Consult();
        if(!title.isEmpty()){
            consult.setTitle(title);
        }
        if(!name.isEmpty()){
            consult.setName(name);
        }
        if(!phone.isEmpty()){
            consult.setPhone(phone);
        }
        if(!email.isEmpty()){
            consult.setEmail(email);
        }
        if(!unit.isEmpty()){
            consult.setUnit(unit);
        }
        if(!content.isEmpty()){
            consult.setContent(content);
        }
        Integer result =consultService.addconsult(consult);

        if(result==1){
            return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "添加成功",true);
        }else{
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "添加失败",false);
        }
    }


    /**
     * 增加咨询回复
     * @return
     */
    @MyLog(detail = "回复咨询",operationType = OperationType.UPDATE,operationUnit = OperationUnit.CONSULT)
    @RequestMapping("/consultAndAnswer")
    @ResponseBody
    public String consultAndAnswer(@RequestParam(required = false) String answer,
                                   @RequestParam(required = false) Integer id){
        if(!answer.isEmpty()) {
            Consult consult = consultService.queryConsult(id);
            if (consult.getId() >= 0) {
                consult.setAnswer(answer);
                consult.setAnswerCreateDate(new Date());
                consult.setStatus(1);
                Integer result = consultService.consultAndAnswer(consult);
                if(result>0){
                    return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "回复成功",true);
                }
            } else {
                return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "没有该咨询",false);
            }
        }
        return RespUtil.getResp(RespUtil.RESP_CODE_ERROR, "程序异常",false);
    }

    /**
     * 查询单个咨询
     * @return
     */
    @RequestMapping("/queryconsult")
    @ResponseBody
    public Map queryconsult(@RequestParam(required = true) Integer id){
        Consult consult = consultService.queryConsult(id);
        Map<String, Consult> mapList = new HashMap<String, Consult>();
        mapList.put("consult",consult);
        return mapList;
    }

    /**
     * 删除单个咨询
     * @return
     */
    @MyLog(detail = "删除咨询",operationType = OperationType.DELETE,operationUnit = OperationUnit.CONSULT)
    @RequestMapping("/deleteConsult")
    @ResponseBody
    public String deleteConsult(@RequestParam(required = true) Integer id){
        Consult consult = consultService.queryConsult(id);
        int result = 0;
        if(consult!=null) {
            consult.setId(id);
            consult.setOpenstatus(2);
            consult.setStatus(2);
            result= consultService.deleteConsult(consult);
        }
        if(result>0){
            return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "删除成功",true);
        }else{
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "删除失败",false);
        }
    }

    /**
     * 咨询审核，更改openstatus
     * @return
     */
    @MyLog(detail = "咨询审核",operationType = OperationType.UPDATE,operationUnit = OperationUnit.CONSULT)
    @RequestMapping("/updateConsultOpenStatus")
    @ResponseBody
    public String updateConsultOpenStatus(@RequestParam(required = true) Integer id,
                                          @RequestParam(required = true) Integer ispub){
        if(id!=0) {
            Consult consult = consultService.queryConsult(id);
            int result = 0;
            if(consult!=null) {
                if(consult.getAnswer()!=null&&consult.getAnswer()!="") {
                    consult.setOpenstatus(ispub);
                    result = consultService.updateConsultOpenStatus(consult);
                    if (result > 0) {
                        return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "更改咨询状态成功",true);
                    }
                }else{
                    return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "回复为空,请先回复再审核",false);
                }
            }
        } else {
            return RespUtil.getResp(RespUtil.RESP_CODE_ERROR, "传参错误，请重新审核",false);
        }
        return RespUtil.getResp(RespUtil.RESP_CODE_EXCEPTION, "程序异常",false);
    }

    /**
     * 发送咨询回复
     * @return
     */
    @MyLog(detail = "发送咨询回复",operationType = OperationType.SELECT,operationUnit = OperationUnit.CONSULT)
    @RequestMapping("/sendAnswer")
    @ResponseBody
    public String sendAnswer(@RequestParam(required = true) Integer id,
                                          @RequestParam(required = true) Integer ispub){
        if(id!=0) {
            Consult consult = consultService.queryConsult(id);
            int result = 0;
            if(consult!=null) {
                if(consult.getAnswer()!=null&&consult.getAnswer()!="") {
                    consult.setOpenstatus(ispub);
                    result = emailProvService.send(consult);
                    if (result == 1) {
                        consult.setOpenstatus(ispub);
                        consultService.updateConsultOpenStatus(consult);
                        return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "发送成功",true);
                    }else if(result ==2){
                        return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "咨询所留邮箱错误,不能发送",false);
                    }else if(result ==0){
                        return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "发送失败",false);
                    }
                }else{
                    return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "回复为空,请先回复再发送",false);
                }
            }
        } else {
            return RespUtil.getResp(RespUtil.RESP_CODE_ERROR, "传参错误，请重新审核",false);
        }
        return RespUtil.getResp(RespUtil.RESP_CODE_EXCEPTION, "程序异常",false);
    }

    @ResponseBody
    @RequestMapping("/queryConsultNoAnswer")
    public String queryConsultNoAnswer() {
        int answerCount = consultService.queryConsultNoAnswer();
        return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "查询成功",answerCount);
    }
}
