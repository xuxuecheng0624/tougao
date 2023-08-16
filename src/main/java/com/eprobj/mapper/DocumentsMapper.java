package com.eprobj.mapper;

import com.eprobj.entity.Documents;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DocumentsMapper
 * @Description TODO
 * @Author kangjian
 * @Date 2019/9/11 16:51
 * @Version 1.0
 **/

public interface DocumentsMapper {

    int save(Documents documents);

    Long count(Map map);

    List<Documents> page(Map map);

    Documents getById(String id);

    int updateByDocId(Documents documents);

    List<Documents> exportByDocId(@Param("exportType") String exportType, @Param("docIdList") List docIdList);

    int deleteByIds(@Param("docIds") List<Integer> docIds);

    int deleteReviewByIds(@Param("docIds") List<Integer> docIds);

    List<String> findAllChannel();

    String findAllReview();
}
