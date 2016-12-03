package cn.springmvc.service.impl;

import cn.springmvc.dao.UserDAO;
import cn.springmvc.model.User;
import cn.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author nidayu
 * @Description:
 * @date 2015/8/11
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDAO userDAO;

    public int insertUser(User user) {
        return userDAO.insertUser(user);
    }

    public User queryUserById(int id){
        return userDAO.queryUserById(id);
    }

}
