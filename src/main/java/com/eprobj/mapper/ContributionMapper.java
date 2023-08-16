package com.eprobj.mapper;

import com.eprobj.entity.Contribution;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * Created by WXX on 2019/8/19.
 */
public interface ContributionMapper {

    int insertContribution(Contribution contribution);

    @Results({
    @Result(column = "workunits",property = "workUnits",jdbcType = JdbcType.VARCHAR),
    @Result(column = "contributorcontent",property = "conTributorContent",jdbcType = JdbcType.VARCHAR ),
    @Result(column = "dismissedsuggestion",property = "dismissedSuggestion",jdbcType = JdbcType.VARCHAR)
    })
    @Select("select title,channel,workunits,contributorcontent,remorks,status,dismissedsuggestion from contributors where uid=#{uid}")
    List<Contribution> findContributionByUser(int uid);

    @Results({
            @Result(column = "workunits",property = "workUnits",jdbcType = JdbcType.VARCHAR),
            @Result(column = "contributorcontent",property = "conTributorContent",jdbcType = JdbcType.VARCHAR ),
            @Result(column = "dismissedsuggestion",property = "dismissedSuggestion",jdbcType = JdbcType.VARCHAR)
    })
    @Select("select title,channel,workunits,contributorcontent,remorks,status,dismissedsuggestion from contributors")
    List<Contribution> findContributionAll();

    @Results({
            @Result(column = "workunits",property = "workUnits",jdbcType = JdbcType.VARCHAR),
            @Result(column = "contributorcontent",property = "conTributorContent",jdbcType = JdbcType.VARCHAR ),
            @Result(column = "dismissedsuggestion",property = "dismissedSuggestion",jdbcType = JdbcType.VARCHAR)
    })
    @Select("select title,channel,workunits,contributorcontent,remorks,status,dismissedsuggestion from contributors WHERE title like  '%#{keyword}%'; ")
    List<Contribution> matchContribution(String keyword);


}
