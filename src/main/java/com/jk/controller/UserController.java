package com.jk.controller;

import com.jk.model.ResultPage;
import com.jk.model.UserBean;
import com.jk.service.UserService;
import com.jk.utils.Md5Util;
import com.jk.utils.RandomValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }



    @RequestMapping("userLogin")
    @ResponseBody
    public HashMap<String, Object> userLogini(UserBean userBean, String imgCode, HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        String sessionCode = session.getAttribute("RANDOMVALIDATECODEKEY").toString();
        String sessionCode1 = session.getAttribute("RANDOMVALIDATECODEKEY").toString();
        if (!sessionCode.equals(imgCode)) {
            result.put("code", 1);
            result.put("msg", "验证码错误");
            return result;
        }
        UserBean userLoginInfo = userService.queryUserLoginInfo(userBean);
        if (userLoginInfo == null) {
            result.put("code", 2);
            result.put("msg", "账号不存在");
            return result;
        }
        String password = userBean.getPassword();
        String md516 = Md5Util.getMd516(password);
        if (!userLoginInfo.getPassword().equals(md516)) {
            result.put("code", 3);
            result.put("msg", "密码错误");
            return result;
        }
        session.setAttribute(session.getId(), userLoginInfo);
        result.put("code", 0);
        result.put("msg", "登录成功");
        return result;
    }







    @RequestMapping("userLogin")
    @ResponseBody
    public HashMap<String, Object> userLogin(UserBean userBean, String imgCode, HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        String sessionCode = session.getAttribute("RANDOMVALIDATECODEKEY").toString();
        if (!sessionCode.equals(imgCode)) {
            result.put("code", 1);
            result.put("msg", "验证码错误");
            return result;
        }
        UserBean userLoginInfo = userService.queryUserLoginInfo(userBean);
        if (userLoginInfo == null) {
            result.put("code", 2);
            result.put("msg", "账号不存在");
            return result;
        }
        String password = userBean.getPassword();
        String md516 = Md5Util.getMd516(password);
        if (!userLoginInfo.getPassword().equals(md516)) {
            result.put("code", 3);
            result.put("msg", "密码错误");
            return result;
        }
        session.setAttribute(session.getId(), userLoginInfo);
        result.put("code", 0);
        result.put("msg", "登录成功");
        return result;
    }

    @RequestMapping("toLayout")
    public String toLayout(){
        return "main";
    }

    @RequestMapping("toUserPage")
    public String toUserPage(){
        return "userList";
    }

    @RequestMapping("queryUserList")
    @ResponseBody
    public ResultPage queryUserList(UserBean userBean){
        ResultPage resultPage =userService.queryUserList(userBean);
        return resultPage;
    }

    @RequestMapping("toAddUser")
    public String toAddUser(){
        return "addUser";
    }

    @RequestMapping("addUser")
    @ResponseBody
    public String addUser(UserBean userBean){
        userService.addUser(userBean);
        return "{}";
    }

    @RequestMapping("toEditUser")
    public String toEditUser(Model model, Integer userId){
        UserBean editUser = userService.queryUserById(userId);
        model.addAttribute("editUser", editUser);
        return "editUser";
    }

    @RequestMapping("editUser")
    @ResponseBody
    public String editUser(UserBean userBean){
        userService.editUser(userBean);
        return "{}";
    }

}
