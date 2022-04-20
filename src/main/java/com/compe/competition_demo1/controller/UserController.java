package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.cdata.*;
import com.compe.competition_demo1.cdata.user_io.identity_out;
import com.compe.competition_demo1.cdata.user_io.*;
import com.compe.competition_demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Resource
    private UserService service;

    //登录
    @RequestMapping(value = "login")
    public login_out LoginUser(@RequestBody userLogin user_login, HttpServletResponse response) throws SQLException {
        return service.LoginUser(user_login);
    }

    //注册
    @RequestMapping(value = "register")
    public int registerUser(@RequestBody userRegister user_register, HttpServletResponse response)throws SQLException{
        return service.registerUser(user_register);
    }

    //修改用户基本信息
    @RequestMapping(value = "chabasic")
    public chabasic_out changeUser_basic(@RequestBody userChabasic user_chabasic, HttpServletResponse response) throws SQLException {
        return service.changeUser_basic(user_chabasic);
    }

    //修改密码
    @RequestMapping(value = "chapass")
    public chapass_out changeUser_password(@RequestBody userChapass user_chapass, HttpServletResponse response) throws SQLException {
        return service.changeUser_password(user_chapass);
    }

    @RequestMapping(value = "bulkimport")  //管理员批量导入
    public void admin_import(@RequestBody User user){
        service.admin_import(user);
    }

    @RequestMapping(value = "identity")  //身份认证
    public identity_out identity(@RequestBody userIdentity user_identity, HttpServletResponse response){
        return service.identity(user_identity);
    }
    @RequestMapping(value = "com")
    public List<User> com(HttpServletResponse response){
        return service.com();
    }
}
