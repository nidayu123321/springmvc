package cn.springmvc.dao;

import cn.springmvc.model.User;

/**
 * @author nidayu
 * @Description:
 * @date 2015/8/11
 */
public interface UserDAO {

    /**
     * 添加新用户
     * @param user
     * @return
     */
    public int insertUser(User user);

    public User queryUserById(int id);
}