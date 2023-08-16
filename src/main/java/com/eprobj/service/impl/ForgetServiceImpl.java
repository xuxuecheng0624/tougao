package com.eprobj.service.impl;

import com.eprobj.entity.User;
import com.eprobj.mapper.UserMapper;
import com.eprobj.service.ForgetService;
import com.eprobj.utill.MailUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ClassName ForgetServiceImpl
 * @Description TODO
 * @Author kangjian
 * @Date 2019/9/25 15:31
 * @Version 1.0
 **/
@Service
public class ForgetServiceImpl implements ForgetService {

    @Autowired
    private UserMapper userMapper;

    @Value("${mail.host}")
    private String host;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;
    @Override
    public String getMailCode(String email) {
        //随机生成4位验证码
        String code= RandomStringUtils.random(4, true, true);
        User user = userMapper.queryByUserEmail(email);
        MailUtils sendmail = new MailUtils();
        if (user != null) {
            sendmail.setHost(host);
            sendmail.setUserName(username);
            sendmail.setPassWord(password);
            sendmail.setTo(email);
            sendmail.setFrom(username);
            sendmail.setSubject("邮箱验证");
            sendmail.setContent("此验证码只用于重置您的密码，切勿将验证码透露给他人防止账号的损失。"+code + "（邮箱验证码）。再次提醒，请勿转发");
            Boolean flag = sendmail.sendMail();
            if (flag) {
                userMapper.updateUserCode(email,code);
                return "发送成功";
            } else {
                return "发送失败";
            }
        } else {
            return "暂无账户绑定此邮箱，请确定后再试";
        }
    }
}
