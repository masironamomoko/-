package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Scanner;

public class UserServicelmpt implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //登录
    @Override
    public int LoginUser(User user) {
        Scanner s = new Scanner(System.in);
        String user_in = s.next();
        //判断是用户名还是手机号的正则表达式
        String ph = "^((13[0-9])|(15[^4,\\\\D])|(17[0-9])|(18[0,5-9]))\\\\d{8}$";
        if (user_in.matches(ph)) {  //手机号登陆
            String sql = "select * from user where user_phone=? and password=?";
            jdbcTemplate.update(sql, user.getUser_id(), user.getUser_name(), user.getUser_password(), user.getUser_phone());
            sql ="select count(*) from user where user_phone ='"+user.getUser_phone()+"'";
            int count = jdbcTemplate.queryForObject(sql,Integer.class);
            if(count !=1)
                return 700;
            return 666;
        }
        else {  //用户名登陆
            String sql = "select * from user where user_name=? and password=?";
            jdbcTemplate.update(sql, user.getUser_id(), user.getUser_name(), user.getUser_password(), user.getUser_phone());
            sql ="select count(*) from user where user_name ='"+user.getUser_name()+"'";
            int count = jdbcTemplate.queryForObject(sql,Integer.class);
            if(count !=1)
                return 700;
            return 666;
        }
    }

    //注册
    @Override
    public int registerUser(User user) {
        String sql="insert into User(user_id,user_name,user_password,user_phone) values(null,?,?,?)";
        jdbcTemplate.update(sql,user.getUser_name(),user.getUser_password(),user.getUser_phone());
        sql ="select count(*) from user where user_name ='"+user.getUser_name()+"'";
        int count = jdbcTemplate.queryForObject(sql,Integer.class);
        if(count !=1)
            return 700;
        return 666;
    }

    //修改用户基本信息
    @Override
    public int changeUser_basic(User user) {
        String sql="update User set user(user_name,user_phone,user_num) values (?,?,?) where user_id = ?";
        jdbcTemplate.update(sql,user.getUser_name(),user.getUser_phone(),user.getUser_num());
        sql ="select count(*) from user where user_name ='"+user.getUser_name()+"and user_phone ='"+user.getUser_phone()+"and user_num ='"+user.getUser_num()+"'";
        int count = jdbcTemplate.queryForObject(sql,Integer.class);
        if(count !=1)
            return 700;
        return 666;
    }

    //修改用户密码
    @Override
    public int changeUser_password(User user) {
        String sql1 ="select count(*) from user where user_password ='"+user.getUser_password()+"and user_id ='"+user.getUser_id()+"'";
        int count1 = jdbcTemplate.queryForObject(sql1,Integer.class);
        if(count1 !=1) {
            String sql2 = "update user set user_password = " + user.getUser_password() + "where user_id =" + user.getUser_id() + "'";
            jdbcTemplate.update(sql2, user.getUser_password());
            sql2 = "select count(*) from user where user_id ='" + user.getUser_id() + "and user_password ='" + user.getUser_password() + "'";
            int count2 = jdbcTemplate.queryForObject(sql2, Integer.class);
            if(count2 !=1)
                return 701;
            return 666;
        }
        return 700;
    }

    //管理员批量导入
    @Override
    public void admin_import(User user) {

    }

    //认证身份
    @Override
    public int identity(User user) {
        String sql = "insert into User(user_identity) values ? where user_id = ?";
        jdbcTemplate.update(sql,user.getUser_identity());
        sql = "select count (*) from user where user_id ='" + user.getUser_id() + "and user_identity ='" + user.getUser_identity() + "'";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        if(count!=1)
            return 700;
        return 666;
    }
}
