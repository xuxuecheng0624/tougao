package com.eprobj.controllers;

import com.eprobj.entity.User;
import com.eprobj.service.ForgetService;
import com.eprobj.service.UserService;
import com.eprobj.utill.RespUtil;
import com.eprobj.utill.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ForgetController
 * @Description TODO
 * @Author kangjian
 * @Date 2019/9/10 14:14
 * @Version 1.0
 **/
@Controller
public class ForgetController {

    @Autowired
    private UserService userService;
    @Autowired
    private ForgetService forgetService;

    @RequestMapping("/forget/password")
    public String forgetPassword() {
        return "views/user/forget";
    }

    @RequestMapping("/forget")
    @ResponseBody
    public Object forget(@RequestBody User user) {
        User user1 = userService.queryByUserEmail(user.getEmail());
        if (user1 != null) {
            if (!user1.getCode().equals(user.getCode())) {
                return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "验证码错误", null);
            } else {
                userService.updatePassword(user);
                 return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "修改成功", null);
            }
        } else {
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "邮箱不存在", null);
        }
    }


    @RequestMapping("/forget/getMailCode")
    @ResponseBody
    public Object getMailCode(HttpServletRequest request) {
        String email = request.getParameter("email");
        String flag = forgetService.getMailCode(email);
        return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, flag, null);
    }

    @RequestMapping("/toEditPass")
    public String toEditPass(){
        return "views/user/edit_pass";
    }


    @RequestMapping("/update/password")
    @ResponseBody
    public Object updatePassword(@RequestBody User user) {
        User user1 = (User) SecurityUtils.getSubject().getPrincipal();
        String newPassword = ShiroUtils.getStrByMD5(user.getPassword());
        if (user1.getPassword().equals(newPassword)) {
            user.setEmail(user1.getEmail());
            userService.updatePassword(user);
            return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "修改成功", null);
        } else {
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "原密码输入错误", null);
        }
    }
}
