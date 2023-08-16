package com.eprobj.service;

import com.eprobj.entity.Review;

import java.util.List;

/**
 * @ClassName ReviewService
 * @Description TODO
 * @Author kangjian
 * @Date 2019/9/15 16:14
 * @Version 1.0
 **/

public interface ReviewService {

    int save(Review review);

    List<Review> findViewsById(String docId);

}
