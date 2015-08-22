package cn.springmvc.service;

import cn.springmvc.model.User;

/**
 * @author nidayu
 * @Description:
 * @date 2015/8/11
 */
public interface UserService {
    public int insertUser(User user);
    public User queryUserById(int id);
}
