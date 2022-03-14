package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.cdata.User;
import com.compe.competition_demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "login")  //登录用户
    public User LoginUser(String user_in, String user_password){
        User user=service.LoginUser(user_in,user_password);
        return user;
    }

    @RequestMapping(value = "new_login")  //注册用户
    public boolean registerUser(@RequestBody User user){
        System.out.println(user.toString());
        boolean res=service.registerUser(user);
        return res;
    }

    @RequestMapping(value = "change/user_identify")  //修改用户身份
    public boolean changeUser_identity(@RequestBody User user){
        boolean res=service.changeUser_identity(user);
        return res;
    }
    @RequestMapping(value = "change/user_name")  //修改用户名
    public boolean changeUser_name(@RequestBody User user){
        boolean res=service.changeUser_name(user);
        return res;
    }
    @RequestMapping(value = "change/user_password")  //修改用户密码
    public boolean changeUser_password(@RequestBody User user){
        boolean res=service.changeUser_password(user);
        return res;
    }
    @RequestMapping(value = "change/user_email")  //修改用户邮箱
    public boolean changeUser_email(@RequestBody User user){
        boolean res=service.changeUser_mail(user);
        return res;
    }
    @RequestMapping(value = "change/user_phone")  //修改用户电话
    public boolean changeUser_phone(@RequestBody User user){
        boolean res=service.changeUser_phone(user);
        return res;
    }
    @RequestMapping(value = "change/user_picture")  //修改用户头像
    public boolean changeUser_picture(@RequestBody User user){
        boolean res=service.changeUser_picture(user);
        return res;
    }
}
