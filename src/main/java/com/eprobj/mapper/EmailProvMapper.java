package com.eprobj.mapper;

import com.eprobj.entity.EmailProv;


/**
 * @ClassName EmailProvMapper
 * @Description TODO
 * @Author kangjian
 * @Date 2019/10/9 17:21
 * @Version 1.0
 **/

public interface EmailProvMapper {

    EmailProv findInfoByEmail(String email);

    int save(EmailProv emailProv);

    int updateById(EmailProv emailProv);
}
