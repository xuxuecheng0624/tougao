package com.eprobj.controllers;

import com.eprobj.entity.Documents;
import com.eprobj.entity.Review;
import com.eprobj.entity.User;
import com.eprobj.service.DocumentsService;
import com.eprobj.service.ReviewService;
import com.eprobj.utill.RespUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @ClassName ReviewController
 * @Description TODO
 * @Author kangjian
 * @Date 2019/9/15 20:12
 * @Version 1.0
 **/
@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private DocumentsService documentsService;

    @Value("${project.basepath}")
    private String basePath;

    @RequestMapping("/toReview")
    public ModelAndView toReview(HttpServletRequest request){
        String docId = request.getParameter("docId");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("views/fzw/shenghe");
        Documents documents = documentsService.details(docId);
        mav.addObject("documents",documents);
        List files = new ArrayList();
        List images = new ArrayList();
        List fileNames = new ArrayList();
        List imagesNames = new ArrayList();

        List<Map<String, Object>> filesList = new ArrayList<>();
        List<Map<String, Object>> imagesList = new ArrayList<>();
        if (StringUtils.isNotBlank(documents.getAppendixAddress())) {
            files = Arrays.asList(documents.getAppendixAddress().split(";"));
        }
        if (StringUtils.isNotBlank(documents.getImagesName())) {
            imagesNames = Arrays.asList(documents.getImagesName().split(";"));
        }
        if (StringUtils.isNotBlank(documents.getImagesAddress())) {
            images = Arrays.asList(documents.getImagesAddress().split(";"));
        }
        if (StringUtils.isNotBlank(documents.getAppendixName())) {
            fileNames = Arrays.asList(documents.getAppendixName().split(";"));
        }
        for (int i = 0; i< files.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("file",files.get(i));
            map.put("fileName",fileNames.get(i));
            filesList.add(map);
        }
        for (int i = 0; i< images.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image",basePath+"/doc/showImg?imgUrl="+imagesNames.get(i));
            map.put("imageName",imagesNames.get(i));
            imagesList.add(map);
        }
        mav.addObject("files",filesList);
        mav.addObject("images",imagesList);
        return mav;
    }

    /**
     * 审核文档
     * @param docId
     * @param review
     * @return
     */
    @RequestMapping("/review/{docId}")
    @ResponseBody
    public Object reviewDoc(@PathVariable String docId,@RequestBody Review review) {
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            review.setUserId(user.getId());
            review.setDocId(Integer.valueOf(docId));
            reviewService.save(review);
            Documents documents = new Documents();
            documents.setId(Integer.valueOf(docId));
            documents.setStatus(review.getStatus());
            documentsService.updateByDocId(documents);
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "审核成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "审核失败", null);
        }
    }


    /**
     * 查看文档审核意见
     * @param docId
     * @return
     */
    @RequestMapping("/review/toView/{docId}")
    public ModelAndView reviewView(@PathVariable String docId) {
        ModelAndView mav = new ModelAndView();
        List<Review> review = reviewService.findViewsById(docId);
        mav.setViewName("views/fzw/fanboyijian");
        mav.addObject("review",review);
        return mav;
    }
}
