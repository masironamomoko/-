package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.*;
import com.compe.competition_demo1.cdata.user_io.identity_out;
import com.compe.competition_demo1.cdata.user_io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicelmpt implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //登录
    @Override
    public login_out LoginUser(userLogin user_login) {
        String user_in = user_login.getUser_in();
        login_out log = new login_out();
//        //判断是用户名还是手机号的正则表达式
//        String ph = "^((13[0-9])|(15[^4,\\\\D])|(17[0-9])|(18[0,5-9]))\\\\d{8}$";
//        String ph;
        String sql="select count(*) from user where user_phone='"+user_login.getUser_in()+"' and user_password='"+user_login.getUser_password()+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=0) {
            String sql1 = "select * from user where user_phone='" + user_login.getUser_in() + "' and user_password='" + user_login.getUser_password() + "'";
            User user1 = jdbcTemplate.queryForObject(sql1, new BeanPropertyRowMapper<User>(User.class));
            log.setCode(666);
            log.setUser(user1);
            return log;
        }
        sql="select count(*) from user where user_name='"+user_login.getUser_in()+"' and user_password='"+user_login.getUser_password()+"'";
        count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=0) {  //用户名登陆
            String sql2 = "select * from user where user_name='"+user_login.getUser_in()+"' and user_password='"+user_login.getUser_password()+"'";
            User user2=jdbcTemplate.queryForObject(sql2,new BeanPropertyRowMapper<User>(User.class));
            log.setCode(666);
            log.setUser(user2);
            return log;
        }
        log.setCode(700);
        return log;
    }

    //注册
    @Override
    public int registerUser(userRegister user_re) {
        String sql="select count(*) from user where user_phone='"+user_re.getUser_phone()+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=0)
            return 700;
        sql="insert into user(user_identity,user_name,user_password,user_email,user_phone,user_id,user_picture,user_num) values(3,?,?,null,?,null,null,1)";
        jdbcTemplate.update(sql,user_re.getUser_name(),user_re.getUser_password(),user_re.getUser_phone());
        sql="select count(*) from user where user_name='"+user_re.getUser_name()+"' and user_phone='"+user_re.getUser_phone()+"'";
        User user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class));
        if(user==null)
            return 700;
        else
            return 666;
    }

    //修改用户基本信息
    @Override
    public chabasic_out changeUser_basic(userChabasic user_chabasic) {
        chabasic_out chb = new chabasic_out();
        String sql1="update user set user(user_name,user_phone,user_num) values (?,?,?) where user_id ='"+user_chabasic.getUser_id()+"'";
        jdbcTemplate.update(sql1,user_chabasic.getUser_name(),user_chabasic.getUser_phone(),user_chabasic.getUser_num());
        String sql2 = "select count(*) from user where user_name='"+user_chabasic.getUser_name()+"'and user_phone ='"+user_chabasic.getUser_phone()+"'and user_num ='"+user_chabasic.getUser_num()+"'";
        int user = jdbcTemplate.queryForObject(sql2,Integer.class);
        if(user==0)
            chb.setCode(700);
        else
            chb.setCode(666);
        return chb;
    }

    //修改用户密码
    @Override
    public chapass_out changeUser_password(userChapass user_chapass){
        chapass_out chp = new chapass_out();
        String sql1 ="select count(*) from user where user_password ='"+user_chapass.getUser_password_old()+"'and user_id ='"+user_chapass.getUser_id()+"'";
        int right = jdbcTemplate.queryForObject(sql1,Integer.class);
        if(right ==1) {
            String sql2 = "update user set user_password =? where user_id =?";
            jdbcTemplate.update(sql2,user_chapass.getUser_password_new(),user_chapass.getUser_id());
            String sql3 ="select count(*) from user where user_password ='"+user_chapass.getUser_password_new()+"'and user_id ='"+user_chapass.getUser_id()+"'";
            int user = jdbcTemplate.queryForObject(sql3,Integer.class);
            if(user==0)
                chp.setCode(701);
            else
                chp.setCode(666);
        }
        else chp.setCode(700);
        return chp;
    }

    //管理员批量导入
    @Override
    public void admin_import(User user) {

    }

    //认证身份
    @Override
    public identity_out identity(userIdentity user_identity) {
        identity_out id = new identity_out();
        String sql1 = "update user set user_identity =? where user_id =?";
        jdbcTemplate.update(sql1,user_identity.getUser_identity(),user_identity.getUser_id());
        String sql2 = "select count(*) from user where user_identity ='"+user_identity.getUser_identity()+"' and user_id ='"+user_identity.getUser_id()+"'";
        int user = jdbcTemplate.queryForObject(sql2,Integer.class);
        if(user==0)
            id.setCode(700);
        else
            id.setCode(666);
        return id;
    }

    @Override
    public List<User> com() {
        String sql="select user_id,user_name,user_phone,user_num,user_identity from user";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class));
    }
}
