package com.eprobj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ErrorPageController
 * @Description TODO
 * @Author kangjian
 * @Date 2019/9/23 12:01
 * @Version 1.0
 **/
@Controller
public class ErrorPageController {

    @RequestMapping(value = "/error400Page")
    public String error400Page() {
        return "views/404";
    }
    @RequestMapping(value = "/error401Page")
    public String error401Page() {
        return "views/404";
    }
    @RequestMapping(value = "/error404Page")
    public String error404Page(Model model) {
        model.addAttribute("code","6666666");
        model.addAttribute("msg","服务器降级中......");
        return "views/404";
    }
    @RequestMapping(value = "/error500Page")
    public String error500Page(Model model) {
        return "views/500";
    }
}
