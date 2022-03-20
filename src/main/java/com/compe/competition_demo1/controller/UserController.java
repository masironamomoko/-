package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.cdata.User;
import com.compe.competition_demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Resource
    private UserService service;
    private JdbcTemplate jdbcTemplate;

    //登录
    @RequestMapping(value = "login")
    public int LoginUser(User user, HttpServletResponse response) throws SQLException {
        return service.LoginUser(user);
    }

    //注册
    @RequestMapping(value = "register")
    public int registerUser(User user, HttpServletResponse response)throws SQLException{
        return service.registerUser(user);
    }

    //修改用户基本信息
    @RequestMapping(value = "chabasic")
    public int changeUser_basic(User user, HttpServletResponse response) throws SQLException {
        return service.changeUser_basic(user);
    }

    //修改密码
    @RequestMapping(value = "chapass")
    public int changeUser_password(User user, HttpServletResponse response) throws SQLException {
        return service.changeUser_password(user);
    }

    @GetMapping(value = "bulkimport")  //管理员批量导入
    public void admin_import(@RequestBody User user){
        service.admin_import(user);
    }

    @RequestMapping(value = "identity")  //身份认证
    public int identity(User user){
        return service.identity(user);
    }
}
