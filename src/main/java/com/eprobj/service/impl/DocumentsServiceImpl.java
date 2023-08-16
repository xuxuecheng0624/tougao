package com.eprobj.service.impl;

import com.eprobj.entity.Documents;
import com.eprobj.mapper.DocumentsMapper;
import com.eprobj.service.DocumentsService;
import com.eprobj.utill.CommUtils;
import com.eprobj.utill.ExportExcelUtils;
import com.eprobj.utill.GetServerRealPathUnit;
import com.eprobj.utill.SaveImgUnit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName DocumentsServiceImpl
 * @Description TODO
 * @Author kangjian
 * @Date 2019/9/11 16:22
 * @Version 1.0
 **/
@Service
public class DocumentsServiceImpl implements DocumentsService {

    @Autowired
    private DocumentsMapper documentsMapper;

    @Override
    public int save(Documents documents) throws IOException {
        documents.setCreateTime(new Date());
        int rownum = documentsMapper.save(documents);
        return rownum;
    }

    @Override
    public Long count(Map params) {
        return documentsMapper.count(params);
    }

    @Override
    public List<Documents> page(Map params) {
        return documentsMapper.page(params);
    }

    @Override
    public Documents details(String id) {
        return documentsMapper.getById(id);
    }

    @Override
    public int updateByDocId(Documents documents) {
        return documentsMapper.updateByDocId(documents);
    }

    @Override
    public Boolean export(String docIds, HttpServletResponse response) throws UnsupportedEncodingException {
        String exportType = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (StringUtils.isNotBlank(docIds)) {
            List list = Arrays.asList(docIds.split(","));
            if (list.size() == 100) {
                exportType = "all";
            }
            List<Documents> documentsList = documentsMapper.exportByDocId(exportType, list);
            int i = 1;
            for (Documents documents:documentsList) {
                documents.setId(i);
                i++;
                if (StringUtils.isNotBlank(documents.getStatus())) {
                    if (documents.getStatus().equals("0")) {
                        documents.setStatus("草稿");
                    } else if (documents.getStatus().equals("1")){
                        documents.setStatus("推送审核");
                    } else if (documents.getStatus().equals("2")){
                        documents.setStatus("已审核");
                    } else if (documents.getStatus().equals("3")){
                        documents.setStatus("驳回");
                    }
                }
                documents.setDateStr(sdf.format(documents.getCreateTime()));
                documents.setContent(CommUtils.deleteAllHTMLTag(documents.getContent()));
            }
            LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();
            String fileName = URLEncoder.encode( "文档-" + System.currentTimeMillis(),"utf-8");
            fieldMap.put("id","序号");
            fieldMap.put("title","标题");
            fieldMap.put("channel","栏目名称");
            fieldMap.put("opusUnit","作者单位");
            fieldMap.put("content","内容");
            fieldMap.put("notes","备注");
            fieldMap.put("status","稿件状态");
            fieldMap.put("userName","用户名称");
            fieldMap.put("telephone","电话号");
            fieldMap.put("email","邮箱");
            fieldMap.put("dateStr","创建时间");
            try {
                new ExportExcelUtils().export(fileName,documentsList,fieldMap,response);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void IoReadImage(String imgUrl, HttpServletResponse response) throws IOException {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        String upload = null;

        //获取商品图片目录
        upload = GetServerRealPathUnit.getPath("static/img");

        try {
            //获取图片存放路径
            String imgPath = upload + "/" + imgUrl;
            ips = new FileInputStream(new File(imgPath));
            String type = imgUrl.substring(imgUrl.indexOf(".")+1);
            if (type.equals("png")){
                response.setContentType("image/png");
            }
            if (type.equals("jpeg")){
                response.setContentType("image/jpeg");
            }
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            out.close();
            ips.close();
        }
    }

    @Override
    public void IoReadFile(String fileUrl, HttpServletResponse response) throws IOException {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        String upload = null;

        //获取商品图片目录
        upload = GetServerRealPathUnit.getPath("static/file");

        try {
            //获取图片存放路径
            String imgPath = upload + "/" + fileUrl;
            ips = new FileInputStream(new File(imgPath));
            String type = fileUrl.substring(fileUrl.indexOf(".")+1);
            if (type.equals("png")){
                response.setContentType("image/png");
            }
            if (type.equals("jpeg")){
                response.setContentType("image/jpeg");
            }
            if (type.equals("xlsx")){
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            }
            if (type.equals("xls")){
                response.setContentType("application/vnd.ms-excel");
            }
            if (type.equals("doc")){
                response.setContentType("application/msword");
            }
            if (type.equals("docx")){
                response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            }
            if (type.equals("zip")){
                response.setContentType("application/zip");
            }
            if (type.equals("zip")){
                response.setContentType("application/pdf");
            } else {
                response.setContentType("multipart/form-data");
            }
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            out.close();
            ips.close();
        }
    }



    @Override
    public boolean deleteByIds(List<Integer> docIds) {
        try {
            if (documentsMapper.deleteByIds(docIds)<=0)
                throw new Exception("系统异常");
            // 删除关联信息
            documentsMapper.deleteReviewByIds(docIds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<String> getChannel() {
        return documentsMapper.findAllChannel();
    }

    @Override
    public String findDocToReview() {
        return documentsMapper.findAllReview();
    }

}
