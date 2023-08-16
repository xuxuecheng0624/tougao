package com.eprobj.service;

import com.eprobj.entity.Documents;
import com.sun.javafx.iio.ios.IosDescriptor;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DocumentsService
 * @Description TODO
 * @Author kangjian
 * @Date 2019/9/11 16:18
 * @Version 1.0
 **/

public interface DocumentsService {

    int save(Documents documents) throws IOException;

    Long count(Map params);

    List<Documents> page(Map params);

    Documents details(String id);

    int updateByDocId(Documents documents);

    Boolean export(String docIds, HttpServletResponse response) throws UnsupportedEncodingException;

    void IoReadImage(String imgUrl, HttpServletResponse response) throws IOException;
    void IoReadFile(String imgUrl, HttpServletResponse response) throws IOException;

    boolean deleteByIds(List<Integer> docIds);

    List<String> getChannel();

    String findDocToReview();

}
