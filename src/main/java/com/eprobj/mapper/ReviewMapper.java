package com.eprobj.mapper;

import com.eprobj.entity.Review;

import java.util.List;

/**
 * @ClassName ReviewMapper
 * @Description TODO
 * @Author kangjian
 * @Date 2019/9/15 16:03
 * @Version 1.0
 **/

public interface ReviewMapper {

    int save(Review review);
    List<Review> findViewsById(String docId);
}
