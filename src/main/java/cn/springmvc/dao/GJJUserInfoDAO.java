package cn.springmvc.dao;

import cn.springmvc.model.GJJUserInfo;

import java.util.List;

/**
 * Created by nidayu on 16/12/3.
 */
public interface GJJUserInfoDAO {

    public int insertGJJUserInfo(GJJUserInfo gjjUserInfo);

    public GJJUserInfo queryGJJUserInfoById(int id);

    public List<String> queryExistAccount();
}
