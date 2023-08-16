package com.eprobj.config;

import com.eprobj.enums.OperationType;
import com.eprobj.enums.OperationUnit;

import java.lang.annotation.*;

/**
 * @ClassName MyLog
 * @Description 自定义注解类
 * @Author kangjian
 * @Date 2019/9/9 11:15
 * @Version 1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface MyLog {
    /**
     * 方法描述,可使用占位符获取参数:{{tel}}
     */
    String detail() default "";

    /**
     * 操作类型(enum):主要是select,insert,update,delete
     */
    OperationType operationType() default OperationType.UNKNOWN;

    /**
     * 被操作的对象(此处使用enum):可以是任何对象，如表名(user)，或者是工具(redis)
     */
    OperationUnit operationUnit() default OperationUnit.UNKNOWN;
}

