package com.jk.service;

import com.jk.mapper.UserMapper;
import com.jk.model.ResultPage;
import com.jk.model.UserBean;
import com.jk.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;

    @Override
    public UserBean queryUserLoginInfo(UserBean userBean) {
        return userDao.queryUserLoginInfo(userBean);
    }

    @Override
    public ResultPage queryUserList(UserBean userBean) {
        ResultPage resultPage = new ResultPage();
        userBean.calculate();
        Integer count = userDao.queryUserCount(userBean);
        List<UserBean> userList = userDao.queryUserList(userBean);
        resultPage.setTotal(count);
        resultPage.setRows(userList);
        return resultPage;
    }

    @Override
    public void addUser(UserBean userBean) {
        userBean.setPassword(Md5Util.getMd516(userBean.getPassword()));
        userDao.addUser(userBean);
    }

    @Override
    public UserBean queryUserById(Integer userId) {
        return userDao.queryUserById(userId);
    }

    @Override
    public void editUser(UserBean userBean) {
        userDao.editUser(userBean);
    }
}


