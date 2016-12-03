package cn.springmvc.service.impl;

import cn.springmvc.dao.GJJUserInfoDAO;
import cn.springmvc.model.GJJUserInfo;
import cn.springmvc.service.GJJUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nidayu on 16/12/3.
 */
@Service
public class GJJUserInfoServiceImpl implements GJJUserInfoService{

    @Autowired
    public GJJUserInfoDAO gjjUserInfoDAO;

    @Override
    public int insertGJJUserInfo(GJJUserInfo gjjUserInfo) {
        return gjjUserInfoDAO.insertGJJUserInfo(gjjUserInfo);
    }

    @Override
    public GJJUserInfo queryGJJUserInfoById(int id){
        return gjjUserInfoDAO.queryGJJUserInfoById(id);
    }

    @Override
    public List<String> queryExistAccount() {
        return gjjUserInfoDAO.queryExistAccount();
    }

}
