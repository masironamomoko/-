package com.compe.competition_demo1.service;


import com.compe.competition_demo1.cdata.*;
import com.compe.competition_demo1.cdata.user_io.identity_out;
import com.compe.competition_demo1.cdata.user_io.*;

import java.util.List;

/*用户类User的功能接口*/
public interface UserService {
    public login_out LoginUser(userLogin user_login);//用户登录
    public int registerUser(userRegister user_register);         //用户注册
    public chabasic_out changeUser_basic(userChabasic user_chabasic);  //用户修改基本信息
    public chapass_out changeUser_password(userChapass user_chapass);
    public void admin_import(User user);  //管理员批量导入
    public identity_out identity(userIdentity user_identity);    //修改用户身份
    public List<User> com();
}

