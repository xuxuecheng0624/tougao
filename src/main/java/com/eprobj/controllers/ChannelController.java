package com.eprobj.controllers;

import com.eprobj.config.MyLog;
import com.eprobj.entity.Channel;
import com.eprobj.enums.OperationType;
import com.eprobj.enums.OperationUnit;
import com.eprobj.service.ChannelService;
import com.eprobj.utill.RespUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ChannelController
 * @Description TODO
 * @Author kangjian
 * @Date 2019/10/12 17:31
 * @Version 1.0
 **/
@Controller
@RequestMapping("/chnl")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @RequestMapping("/toList")
    public String toList() {
        return "views/channel/channel";
    }

    @RequestMapping("/list-page")
    @ResponseBody
    public Object listPage(@RequestParam(value = "qChnlName") String qChnlName,
                           @RequestParam(value="qCreateTime") String qCreateTime,
                           @RequestParam(value="page", defaultValue="1") Integer page,
                           @RequestParam(value="limit", defaultValue="20") Integer limit) {
        Map params = new HashMap();

        params.put("qChnlName",qChnlName);
        params.put("qCreateTime",qCreateTime);
        params.put("index",(page-1)*limit);
        params.put("limit",limit);
        Long count = channelService.count(params);
        List<Channel> channelList = channelService.page(params);
        return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS,"栏目查询成功",channelList, count);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value="ids[]") List<Integer> ids){
        if (!channelService.deleteByIds(ids))
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "系统异常", null);
        return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "用户删除成功", null);
    }

    /**
     * 用户新增/编辑页面
     * @return
     */
    @RequestMapping("/edit")
    public String edit(@RequestParam(value="id", required=false,defaultValue = "") String id, HttpServletRequest request){
        if (StringUtils.isNotBlank(id)){
            request.setAttribute("chnl",channelService.getById(id));
        }else {
            request.setAttribute("chnl",new Channel());
        }
        return "views/channel/edit";
    }

    /**
     * 栏目新增/编辑页面
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(@RequestBody Channel channel) {
        if (channel.getId() == null) {
            Channel channel1 = channelService.findInfoByName(channel.getChnlName());
            if (channel1 == null) {
                if (channelService.save(channel) == 1) {
                    return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "栏目保存成功", true);
                }else {
                    return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "栏目保存失败", true);
                }
            } else {
                return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "栏目已存在", true);
            }
        } else {
            if(channelService.updateById(channel) == 1){
                return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "栏目保存成功", true);
            }else {
                return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "栏目保存失败", true);
            }
        }
    }

}
