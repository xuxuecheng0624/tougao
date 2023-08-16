package com.eprobj.controllers;

import com.eprobj.entity.Contribution;
import com.eprobj.mapper.ContributionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by WXX on 2019/8/20.
 * 管理稿件
 */
@Controller
public class ContributeController {
    @Autowired
    private ContributionMapper contributionMapper;

    @RequestMapping("createcontribute")
    public String insertContribue(){
        return "login";
    }

    @RequestMapping("showallcontribute")
    public String showAllContribute(){
        List<Contribution> list= contributionMapper.findContributionAll ();
        return "login";
    }
    @RequestMapping("showusercontribute")
    public String showUserContribute(@RequestParam int uid){
        List<Contribution> list= contributionMapper.findContributionByUser ( uid );
        return "login";
    }

    @RequestMapping("matchcontribute")
    public String matchContribute(@RequestParam String keyword){
        List<Contribution> list= contributionMapper.matchContribution (keyword);
        return "login";
    }
    
}
