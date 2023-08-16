package com.eprobj.utill;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

/**
 * <p>
 * Title: 使用javamail发送邮件
 * </p>
 */
public class MailUtils {


    String to = "";// 收件人
    String from = "";// 发件人
    String host = "";// smtp主机
    String username = "";
    String password = "";
    String filename = "";// 附件文件名
    String subject = "";// 邮件主题
    String content = "";// 邮件正文
    List file = new ArrayList();// 附件文件集合

    /**
     * <br>
     * 方法说明：默认构造器 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public MailUtils() {
    }

    /**
     * <br>
     * 方法说明：构造器，提供直接的参数传入 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public MailUtils(String to, String from, String smtpServer,
                     String username, String password, String subject, String content) {
        this.to = to;
        this.from = from;
        this.host = smtpServer;
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.content = content;
    }

    /**
     * <br>
     * 方法说明：设置邮件服务器地址 <br>
     * 输入参数：String host 邮件服务器地址名称 <br>
     * 返回类型：
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * <br>
     * 方法说明：设置登录服务器校验密码 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setPassWord(String pwd) {
        this.password = pwd;
    }

    /**
     * <br>
     * 方法说明：设置登录服务器校验用户 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setUserName(String usn) {
        this.username = usn;
    }

    /**
     * <br>
     * 方法说明：设置邮件发送目的邮箱 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * <br>
     * 方法说明：设置邮件发送源邮箱 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * <br>
     * 方法说明：设置邮件主题 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * <br>
     * 方法说明：设置邮件内容 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * <br>
     * 方法说明：把主题转换为中文 <br>
     * 输入参数：String strText <br>
     * 返回类型：
     */
    public String transferChinese(String strText) {
        try {
            strText = MimeUtility.encodeText(new String(strText.getBytes(), "GB2312"), "GB2312", "B");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strText;
    }

    /**
     * <br>
     * 方法说明：往附件组合中添加附件 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void attachfile(String fname) {
        file.add(fname);
    }

    /**
     * <br>
     * 方法说明：发送邮件 <br>
     * 输入参数： <br>
     * 返回类型：boolean 成功为true，反之为false
     */
    public boolean sendMail() {

        // 构造mail session
        Properties props = new Properties() ;
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // 构造MimeMessage 并设定基本的值
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));

            msg.addRecipients(Message.RecipientType.TO, to);
            //多人
            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(to));
            msg.setSubject(MimeUtility.encodeText(subject,"gb2312","B"));

            // 构造Multipart
            Multipart mp = new MimeMultipart();

            // 向Multipart添加正文
            MimeBodyPart mbpContent = new MimeBodyPart();
            mbpContent.setContent(content, "text/html;charset=gb2312");

            // 向MimeMessage添加（Multipart代表正文）
            mp.addBodyPart(mbpContent);

            // 向Multipart添加附件
            Iterator iterator = file.iterator();
            while (iterator.hasNext()) {

                MimeBodyPart mbpFile = new MimeBodyPart();
                filename = iterator.next().toString();
                FileDataSource fds = new FileDataSource(filename);
                mbpFile.setDataHandler(new DataHandler(fds));
                //这个方法可以解决附件乱码问题。
                mbpFile.setFileName(MimeUtility.encodeText(fds.getName(), "gb2312", "B"));
                // 向MimeMessage添加（Multipart代表附件）
                mp.addBodyPart(mbpFile);

            }

            file.clear();
            // 向Multipart添加MimeMessage
            msg.setContent(mp);
            msg.setSentDate(new Date());
            msg.saveChanges() ;
            // 发送邮件

            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception mex) {
            mex.printStackTrace();
            return false;
        }
        return true;
    }

}