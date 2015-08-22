package cn.springmvc.dao;

import cn.springmvc.model.User;

/**
 * @author nidayu
 * @Description:
 * @date 2015/8/11
 */
public interface UserDAO {

    /**
     * ������û�
     * @param user
     * @return
     */
    public int insertUser(User user);

    public User queryUserById(int id);
}