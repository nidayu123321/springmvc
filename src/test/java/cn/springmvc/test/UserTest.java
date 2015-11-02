package cn.springmvc.test;

import cn.springmvc.model.User;
import cn.springmvc.service.UserService;
import cn.springmvc.util.DatabaseContextHolder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author nidayu
 * @Description:
 * @date 2015/8/12
 */
public class UserTest {
    private UserService userService;

    @Before
    public void before(){
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml", "classpath:conf/spring-mybatis.xml"});
        userService = (UserService) context.getBean("userServiceImpl");
    }

    @Test
    public void addUser(){
        //切换数据源
        DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_SOURCE_META);
        try {
            User user = new User();
            user.setNickname("倪达玉");
            System.out.println(user.getNickname());
            user.setState(1);
            System.out.println(userService.insertUser(user));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DatabaseContextHolder.clearCustomerType();
        }
    }
}
