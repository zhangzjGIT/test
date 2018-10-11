package com.jk.mapper;

import com.jk.model.UserBean;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    UserBean queryUserLoginInfo(UserBean userBean);

    int queryUserCount(@Param("userBean")UserBean userBean);

    List<UserBean> queryUserList(@Param("userBean")UserBean userBean);

    void addUser(UserBean userBean);

    UserBean queryUserById(Integer userId);

    void editUser(UserBean userBean);
}
