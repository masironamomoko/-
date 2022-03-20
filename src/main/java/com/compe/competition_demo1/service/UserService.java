package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.User;

/*用户类User的功能接口*/
public interface UserService {
    public int LoginUser(User user);//用户登录
    public int registerUser(User user);         //用户注册
    public int changeUser_basic(User user);  //用户修改基本信息
    public int changeUser_password(User user);
    public void admin_import(User user);  //管理员批量导入
    public int identity(User user);    //修改用户身份
}

