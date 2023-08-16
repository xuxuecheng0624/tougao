package com.eprobj.controllers;

import com.eprobj.service.EmailProvService;
import com.eprobj.utill.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName EmailProvController
 * @Description TODO
 * @Author kangjian
 * @Date 2019/10/9 17:41
 * @Version 1.0
 **/
@Controller
public class EmailProvController {

    @Autowired
    private EmailProvService emailProvService;

    @RequestMapping("/email/prov")
    @ResponseBody
    public Object getMailCode(HttpServletRequest request) {
        String type = request.getParameter("type");
       String flag = emailProvService.save(request.getParameter("email"),type);
        return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, flag, null);
    }
}
