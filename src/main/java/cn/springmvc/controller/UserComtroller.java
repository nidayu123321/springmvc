package cn.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author nidayu
 * @Description:
 * @date 2015/8/13
 */
@Controller
@RequestMapping("/")
public class UserComtroller {
    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
