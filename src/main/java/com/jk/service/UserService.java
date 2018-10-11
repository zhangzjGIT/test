package com.jk.service;

import com.jk.model.ResultPage;
import com.jk.model.UserBean;

public interface UserService {

    UserBean queryUserLoginInfo(UserBean userBean);

    ResultPage queryUserList(UserBean userBean);

    void addUser(UserBean userBean);

    UserBean queryUserById(Integer userId);

    void editUser(UserBean userBean);
}
