package com.eprobj.utill;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by WXX on 2019/8/20.
 * 获取访问人的信息
 */
public class GetMessage {
    public String gtIp( HttpServletRequest request){
       String expip= request.getHeader ( "X-Forwarded-For" );
       if(expip==null){
        return request.getRemoteUser ();
       }else{
           return expip.contains ( "," )?expip.split (",")[0]:expip;
       }
    }
}
