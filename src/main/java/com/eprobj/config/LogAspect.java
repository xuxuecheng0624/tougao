package com.eprobj.config;

import com.alibaba.fastjson.JSON;
import com.eprobj.entity.Log;
import com.eprobj.enums.OperationType;
import com.eprobj.enums.OperationUnit;
import com.eprobj.service.LogService;
import com.eprobj.utill.CommUtils;
import com.eprobj.utill.HttpContextUtils;
import com.eprobj.utill.IPHost;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @ClassName LogAspect
 * @Description 系统日志：切面处理类
 * @Author kangjian
 * @Date 2019/8/20 10:25
 * @Version 1.0
 **/
@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    private static final org.apache.commons.logging.Log LOG = LogFactory.getLog(LogAspect.class);


    ThreadLocal<Long> start = new ThreadLocal<>();  //线程副本类去记录各个线程的开始时间
    long end =  0;
    //执行请求前记录开始时间
    @Before(value = "logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        start.set(System.currentTimeMillis());
        LOG.info("==" + CommUtils.getTimeStr() + " 开始请求" + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName() + "==");
    }
    //执行请求后记录结束时间
    @After(value = "logPointCut()")
    public void doAfter(JoinPoint joinPoint) {
        LOG.info("==" + CommUtils.getTimeStr() + " 请求" + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName() + "结束==");
    }
    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.eprobj.config.MyLog )")
    public void logPointCut() {
    }

    /**
     * 切面配置通知
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void saveSysLog(JoinPoint joinPoint,Object ret) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //保存日志
        Log log = new Log();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String value = myLog.detail();
            OperationType operationTypeEnum = myLog.operationType();
            String operationType = operationTypeEnum.getValue();
            OperationUnit operationUnitEnum = myLog.operationUnit();
            String operationUnit = operationUnitEnum.getValue();
            log.setOperation(operationType);
            log.setOperationUnit(operationUnit);
            log.setDescribes(value);//保存获取的操作
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        log.setMethod(className + "." + methodName);

        //获取请求接口时长
        try {
            end = System.currentTimeMillis();
            LOG.info("执行----" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()
                    + "方法,耗时:----" + (end - start.get()) + " ms!");
        } catch (Exception e) {
            end = System.currentTimeMillis();
            LOG.error(joinPoint + ",耗时:----" + (end - start.get()) + " ms,抛出异常 :" + e.getMessage());
            throw e;
        }
        log.setRunTime(end - start.get());


        String reqParam = "";
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            //此处可以改成自定义的注解
            if (annotation.annotationType().equals(RequestMapping.class)) {
                reqParam = JSON.toJSONString(request.getParameterMap());
                break;
            }
        }
        log.setParams(reqParam);

        log.setCreateDate(new Date());

        //获取用户名
            log.setUsername("admin");

        //获取用户ip地址
        HttpServletRequest request2 = HttpContextUtils.getHttpServletRequest();
        log.setIp(IPHost.getRemoteHost(request2));

        //日志类型
        log.setLogType("信息");

        //方法返回值
        log.setReturnValue((String) ret.toString());

        //保存日志
        logService.save(log);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing(pointcut = "logPointCut()" ,throwing = "e")
    public void throwss(JoinPoint joinPoint,Throwable e){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //保存日志
        Log log = new Log();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String value = myLog.detail();
            OperationType operationTypeEnum = myLog.operationType();
            String operationType = operationTypeEnum.getValue();
            OperationUnit operationUnitEnum = myLog.operationUnit();
            String operationUnit = operationUnitEnum.getValue();
            log.setOperation(operationType);
            log.setOperationUnit(operationUnit);
            log.setDescribes(value);//保存获取的操作
        }
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        log.setMethod(className + "." + methodName);
        //获取请求接口时长
            end = System.currentTimeMillis();
        log.setRunTime(end - start.get());
        String reqParam = "";
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            //此处可以改成自定义的注解
            if (annotation.annotationType().equals(RequestMapping.class)) {
                reqParam = JSON.toJSONString(request.getParameterMap());
                break;
            }
        }
        log.setParams(reqParam);

        log.setCreateDate(new Date());

        //获取用户名
        log.setUsername("admin");

        //获取用户ip地址
        HttpServletRequest request2 = HttpContextUtils.getHttpServletRequest();
        log.setIp(IPHost.getRemoteHost(request2));

        //日志类型
        log.setLogType("异常");

        //方法返回值
        log.setReturnValue(e.getMessage());

        //保存日志
        logService.save(log);
    }

}
