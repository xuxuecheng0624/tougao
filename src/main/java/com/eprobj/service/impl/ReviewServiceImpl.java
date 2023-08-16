package com.eprobj.service.impl;

import com.eprobj.entity.Documents;
import com.eprobj.entity.Review;
import com.eprobj.mapper.ReviewMapper;
import com.eprobj.service.ReviewService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ReviewServiceImpl
 * @Description TODO
 * @Author kangjian
 * @Date 2019/9/15 20:10
 * @Version 1.0
 **/
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public int save(Review review) {
        review.setCreateTime(new Date());
        return reviewMapper.save(review);
    }

    @Override
    public List<Review> findViewsById(String docId) {
        return reviewMapper.findViewsById(docId);
    }

}
