package com.eprobj.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eprobj.utill.GetServerRealPathUnit;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName ServerController
 * @Description TODO
 * @Author kangjian
 * @Date 2019/9/20 16:16
 * @Version 1.0
 **/
@Controller
public class ServerController {

    @RequestMapping("/config")
    @ResponseBody
    public Object config(HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        String path = "//" + formater.format(new Date());
        ClassPathResource res = new ClassPathResource("config.json");
        String action = request.getParameter("action");
        String rPath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/ueditor/jsp";
        String sPath =  GetServerRealPathUnit.getPath("static/img");;
        if(action.equals("uploadimage")){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
            MultipartFile multipartFile = multipartHttpServletRequest.getFile("upfile");
            String oldFileName = multipartFile.getOriginalFilename();
            String randomStr = UUID.randomUUID().toString();
            String newFileName = randomStr + oldFileName.substring(oldFileName.lastIndexOf("."));
            File filePath = new File(sPath, newFileName);

            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            File file1 = new File(sPath + File.separator + newFileName);
            try {
                multipartFile.transferTo(file1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("original",multipartFile.getOriginalFilename());
            map.put("name",multipartFile.getOriginalFilename());
            map.put("url","?imgUrl="+newFileName);
            map.put("size",multipartFile.getSize());
            map.put("type","."+ oldFileName.substring(oldFileName.lastIndexOf(".")));
            map.put("state","SUCCESS");
            return map;
        }else{
            InputStream is = null;

            response.setHeader("Content-Type" , "text/html");
            try {
                is = res.getInputStream();
                StringBuffer sb = new StringBuffer();
                byte[] b = new byte[1024];
                int length=0;
                while(-1!=(length=is.read(b))){
                    sb.append(new String(b,0,length,"utf-8"));
                }
                String result = sb.toString().replaceAll("/\\*(.|[\\r\\n])*?\\*/","");
//                response.setContentType("application/json");
                JSONObject json = JSON.parseObject(result);
                response.setCharacterEncoding("utf-8");
                request.setCharacterEncoding( "utf-8" );
                PrintWriter writer = response.getWriter();
                writer.write(json.toString());
                writer.flush();
                writer.close();
            }catch (Exception r){
                r.printStackTrace();
            }
        }
        return null;

    }
}
