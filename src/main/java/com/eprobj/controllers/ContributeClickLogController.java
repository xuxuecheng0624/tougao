package com.eprobj.controllers;

import com.alibaba.fastjson.JSON;
import com.eprobj.config.MyLog;
import com.eprobj.entity.Contributorclicklog;
import com.eprobj.entity.Watchipclass;
import com.eprobj.enums.OperationType;
import com.eprobj.enums.OperationUnit;
import com.eprobj.service.ContributeClickLogService;
import com.eprobj.utill.CommUtils;
import com.eprobj.utill.IPHost;
import com.eprobj.utill.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by WXX on 2019/8/20.
 * 管理稿件
 */
@Controller
public class ContributeClickLogController {

    @Autowired
    private ContributeClickLogService contributeClickLogService;


    @RequestMapping("/contribueClickLogtoList")
    public String contribueClickLogtoList() {
        return "views/fzw/dianji";
    }

    /**
     * 增加稿件访问记录
     *
     * @return
     */
    @MyLog(detail = "增加稿件点击量", operationType = OperationType.INSERT, operationUnit = OperationUnit.CONTRIBUTORCLICKLOG)
    @RequestMapping("/insertcontributeclicklog")
    @ResponseBody
    public Map insertContribueClickLog(@RequestParam(required = false) Integer cid,
                                       @RequestParam(required = false) String ctitle,
                                       @RequestParam(required = false) String cchannel) {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        Contributorclicklog contributorclicklog = new Contributorclicklog();
        String ip = IPHost.getRemoteHost(request);
        Map<String, Object> map = new HashMap<String, Object>();
        int wresult;
        if (cid == null ||ctitle == "" || cchannel == "") {
            map.put("msg","error");
            return map;
        }
        /*通过cid查询咨询*/
        Contributorclicklog queryContributorclicklog = contributeClickLogService.queryContribueClickLog(cid);
        /*咨询不为空*/
        if (queryContributorclicklog != null) {
            System.out.println("咨询不为空");
            /*查询访问记录*/
            Watchipclass watchipclass = contributeClickLogService.queryWatchipclass(queryContributorclicklog.getCid(), ip);
            System.out.println("查询访问记录:" + ip);
            /*访问记录不为空*/
            if (watchipclass != null) {
                Date date = new Date();
                /* int res = CommUtils.getDatePoor(date, watchipclass.getWatchtime());*/
                int res = 6;
                if (res <= 5) {
                } else {
                    watchipclass.setCid(queryContributorclicklog.getCid());
                    watchipclass.setWatchip(IPHost.getRemoteHost(request));
                    watchipclass.setWatchtime(new Date());
                    wresult = contributeClickLogService.insertWatchipclass(watchipclass);
                    System.out.println("插入访问记录:" + ip);
                    if (wresult > 0) {
                        queryContributorclicklog.setWatchnum(queryContributorclicklog.getWatchnum() + 1);
                        contributeClickLogService.updateContribueClickLog(queryContributorclicklog);
                        map.put("checkNum", queryContributorclicklog.getWatchnum());
                        map.put("msg", "success");
                    }
                }
            }
            /*同ip访问记录为空*/
            else {
                Watchipclass watchipclassnull = new Watchipclass();
                int ccid = queryContributorclicklog.getCid();
                watchipclassnull.setCid(ccid);
                watchipclassnull.setWatchip(IPHost.getRemoteHost(request));
                watchipclassnull.setWatchtime(new Date());
                wresult = contributeClickLogService.insertWatchipclass(watchipclassnull);
                if (wresult > 0) {
                    queryContributorclicklog.setWatchnum(queryContributorclicklog.getWatchnum() + 1);
                    contributeClickLogService.updateContribueClickLog(queryContributorclicklog);
                    map.put("checkNum", queryContributorclicklog.getWatchnum() + 1);
                    map.put("msg", "success");
                }
            }
        }
        /*咨询为空*/
        else {
            /*增加稿件记录*/
            contributorclicklog.setCtitle(ctitle);
            contributorclicklog.setCchannel(cchannel);
            contributorclicklog.setWatchnum(1);
            Integer result = contributeClickLogService.insertContribueClickLog(contributorclicklog);
            System.out.println("插入咨询记录:" + ip);
            if (result > 0) {
                Watchipclass watchipclass = new Watchipclass();
                watchipclass.setCid(cid);
                watchipclass.setWatchip(IPHost.getRemoteHost(request));
                watchipclass.setWatchtime(new Date());
                wresult = contributeClickLogService.insertWatchipclass(watchipclass);
                map.put("checkNum", 1);
                map.put("msg", "success");
            }
        }
        return map;
    }


    /**
     * 访问记录list
     *
     * @return
     */
    @RequestMapping("/contribueClickLogList")
    @ResponseBody
    public String contribueClickLogList(@RequestParam(required = false) String searchword,
                                        @RequestParam(required = false) int page,
                                        @RequestParam(required = false) int limit,
                                        @RequestParam(required = false) String field,
                                        @RequestParam(required = false) String order) {
        List<Contributorclicklog> list;
        Map<String, Object> map = new HashMap<String, Object>();
        if (searchword != null && searchword != "") {
            map.put("ctitle", searchword);
            map.put("cchannel", searchword);
        }
        if (page <= 0) {
            page = 1;
        }
        map.put("page", (page - 1) * limit);
        map.put("limit", limit);
        if (field != null) {
            map.put("field", field);
        } else {
            map.put("field", "watchnum");
        }
        if (order != null) {
            map.put("order", order);
        } else {
            map.put("order", "desc");
        }
        list = contributeClickLogService.contribueClickLogList(map);
        int countnum = contributeClickLogService.contribueClickLogListCount(searchword);
        if (countnum > 0) {
            return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "查询成功", list, countnum);
        }
        return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "未查询到相关数据", list, countnum);

    }
}
