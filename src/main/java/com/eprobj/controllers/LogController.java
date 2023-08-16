package com.eprobj.controllers;

import com.eprobj.entity.Log;
import com.eprobj.service.LogService;
import com.eprobj.utill.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxc on 2019/9/23
 * 日志操作
 */
@Controller
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("/logtoList")
    public String logtoList() {
        return "views/fzw/rizhi";
    }

    /**
     * 分页查询日志列表
     *
     * @param qLoginName
     * @param slogType
     * @param operation
     * @param operationUnit
     * @param page
     * @param limit
     * @return
     */

    @RequestMapping("/queryLogList")
    @ResponseBody
    public String queryLogList(
            @RequestParam(required = false) String qLoginName,
            @RequestParam(required = false) String slogType,
            @RequestParam(required = false) String operation,
            @RequestParam(required = false) String operationUnit,
            @RequestParam(required = false) int page,
            @RequestParam(required = false) int limit) {
        List<Log> list = new ArrayList<Log>();
        Map<String, Object> map = new HashMap<String, Object>();
        if (slogType!=null) {
            switch (slogType){
                case "1":slogType = "信息";break;
                case "2":slogType = "错误";break;
                case "3":slogType = "异常";break;
                case "4":slogType = "调试";break;
                case "0":slogType = "";break;
            }
        }
        if (operationUnit!=null) {
            switch (operationUnit){
                case "1":operationUnit = "document";break;
                case "2":operationUnit = "user";break;
                case "3":operationUnit = "consult";break;
                case "4":operationUnit = "contributorclicklog";break;
                case "0":operationUnit = "";break;
            }
        }
        if (operation!=null) {
            switch (operation){
                case "1":operation = "select";break;
                case "2":operation = "update";break;
                case "3":operation = "delete";break;
                case "4":operation = "insert";break;
                case "5":operation = "unknown";break;
                case "0":operation = "";break;
            }
        }
        map.put("qLoginName", qLoginName);
        map.put("slogType", slogType);
        map.put("operationUnit", operationUnit);
        map.put("operation", operation);
        map.put("page", (page-1)*limit);
        map.put("limit", limit);
        list = logService.queryLogList(map);
        long countnum = logService.count(map);
        if (countnum > 0) {
            return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "查询成功", list, countnum);
        } else {
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "未查询到相关数据", list, countnum);
        }
    }

    /**
     * 根据ID查询日志
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryLogOne")
    public ModelAndView queryLogOne(HttpServletRequest request) {
        String id = request.getParameter("logid");
        ModelAndView mav = new ModelAndView();
        Log log = logService.queryLogOne(Integer.parseInt(id));
        mav.setViewName("views/fzw/logchakan");
        mav.addObject("log", log);
        return mav;

    }
}
