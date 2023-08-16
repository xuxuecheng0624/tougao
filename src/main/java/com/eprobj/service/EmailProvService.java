package com.eprobj.service;

import com.eprobj.entity.Consult;
import com.eprobj.entity.EmailProv;

public interface EmailProvService {

    String save(String email,String type);

    EmailProv findByEmail(String email);

    Integer send(Consult consult);
}
