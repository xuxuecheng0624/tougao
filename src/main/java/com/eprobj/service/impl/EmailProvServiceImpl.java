package com.eprobj.service.impl;

import com.eprobj.entity.Consult;
import com.eprobj.entity.EmailProv;
import com.eprobj.entity.User;
import com.eprobj.mapper.EmailProvMapper;
import com.eprobj.mapper.UserMapper;
import com.eprobj.service.EmailProvService;
import com.eprobj.utill.MailUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName EmailProvServiceImpl
 * @Description TODO
 * @Author kangjian
 * @Date 2019/10/9 17:34
 * @Version 1.0
 **/
@Service
public class EmailProvServiceImpl implements EmailProvService {

    @Autowired
    private EmailProvMapper emailProvMapper;

    @Autowired
    private UserMapper userMapper;

    @Value("${mail.host}")
    private String host;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;


    @Override
    public String save(String email,String type) {
        MailUtils sendmail = new MailUtils();

        if (StringUtils.isNotBlank(email)) {
            User user = userMapper.queryByUserEmail ( email );
            if (user != null) {
                return "邮箱已存在";
            } else {
                String code= RandomStringUtils.random(4, true, true);
                sendmail.setHost(host);
                sendmail.setUserName(username);
                sendmail.setPassWord(password);
                sendmail.setTo(email);
                sendmail.setFrom(username);
                sendmail.setSubject("邮箱验证");
                if ("register".equals(type)) {
                    sendmail.setContent("此验证码只用于注册用户的验证，切勿将验证码透露给他人防止账号的损失。"+code + "（邮箱验证码）。再次提醒，请勿转发");
                } else {
                    sendmail.setContent("此验证码只用于重置您的密码，切勿将验证码透露给他人防止账号的损失。"+code + "（邮箱验证码）。再次提醒，请勿转发");
                }
                Boolean flag = sendmail.sendMail();
                if (flag) {
                    EmailProv emailProv = emailProvMapper.findInfoByEmail(email);
                    int rownum = 0;
                    if (emailProv != null) {
                        emailProv.setCode(code);
                        rownum = emailProvMapper.updateById(emailProv);
                    } else {
                        EmailProv emailProv1  = new EmailProv();
                        emailProv1.setCode(code);
                        emailProv1.setEmail(email);
                        emailProv1.setCreateTime(new Date());
                        rownum = emailProvMapper.save(emailProv1);
                    }
                    return "发送成功";
                } else {
                    return "发送失败";
                }

            }
        } else {
            return "请输入邮箱";
        }

    }

    @Override
    public EmailProv findByEmail(String email) {
        return emailProvMapper.findInfoByEmail(email);
    }

    @Override
    public Integer send(Consult consult) {
        MailUtils sendmail = new MailUtils();

        if (StringUtils.isNotBlank(consult.getEmail())) {
                sendmail.setHost(host);
                sendmail.setUserName(username);
                sendmail.setPassWord(password);
                sendmail.setTo(consult.getEmail());
                sendmail.setFrom(username);
                sendmail.setSubject(consult.getTitle()+"的回复");
                sendmail.setContent("您好：针对您提出的["+consult.getTitle()+"]问题，回复内容如下:<br>  "+consult.getAnswer()+"<br>回复时间："+consult.getAnswerCreateDate()+"<br>再次提醒，请勿转发!");
                Boolean flag = sendmail.sendMail();
                if (flag) {
                    return 1;
                } else {
                    return 0;
                }

        } else {
            return 2;
        }
    }
}
