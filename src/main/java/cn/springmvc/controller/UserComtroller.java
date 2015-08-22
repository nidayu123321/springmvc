package cn.springmvc.controller;

import cn.springmvc.model.User;
import cn.springmvc.service.UserService;
import cn.springmvc.util.DatabaseContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author nidayu
 * @Description:
 * @date 2015/8/13
 */
@Controller
@RequestMapping("/")
public class UserComtroller {

    protected org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("UserComtroller");

    @Resource
    private UserService userService;

    @RequestMapping("index")
    public String index(){
        return "index";
    }


    //切换数据源的测试
    @RequestMapping(value = "insert/user/{state}")
    public String insertUserById(@PathVariable int state){
        logger.info(state);
        User u = new User();
        u.setState(state);
        u.setNickname("张瑜");
        int ret = userService.insertUser(u);
        logger.info(ret);
        return "index";
    }

    @RequestMapping(value = "insert/user2/{state}")
    public String insertUserById2(@PathVariable int state){
        //切换数据源
        DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_SOURCE_META);
        try {
            logger.info(state);
            User u = new User();
            u.setState(state);
            u.setNickname("张瑜");
            int ret = userService.insertUser(u);
            logger.info(ret);
        }finally {
            DatabaseContextHolder.clearCustomerType();
        }
        return "index";
    }

    @RequestMapping(value = "query/user/{id}")
    public String queryUserById(@PathVariable int id){
        logger.info(id);
        User u = userService.queryUserById(id);
        return "index";
    }


}
