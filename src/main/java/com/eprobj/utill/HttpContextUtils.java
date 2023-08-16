package com.eprobj.utill;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName HttpContextUtils
 * @Description TODO
 * @Author kangjian
 * @Date 2019/8/20 10:44
 * @Version 1.0
 **/

public class HttpContextUtils {
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
