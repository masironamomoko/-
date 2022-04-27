package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.cdata.*;
import com.compe.competition_demo1.cdata.user_io.identity_out;
import com.compe.competition_demo1.cdata.user_io.*;
import com.compe.competition_demo1.service.UserService;
import com.compe.competition_demo1.util.ReadUserExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public int admin_import(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="file",required = false) MultipartFile file){
        List<String> list=new ArrayList();
        Map<String,Object> res=new HashMap<>();
        List<User> excelInfo= ReadUserExcelUtil.getExcelInfo(file);
        for(User UserInfo : excelInfo){
            service.admin_import(UserInfo);
        }
        if(list.size()>0){
            res.put("log",list);
        }
        return 666;
    }

    @RequestMapping(value = "identity")  //身份认证
    public identity_out identity(@RequestBody userIdentity user_identity, HttpServletResponse response){
        return service.identity(user_identity);
    }
    @RequestMapping(value = "com")
    public List<User> com(HttpServletResponse response){
        return service.com();
    }

    @RequestMapping(value = "delete")  //用户删除
    public int deleteuser(@RequestBody Map<String,Object> param,HttpServletResponse response){
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.deleteuser(user_id);
    }

}
