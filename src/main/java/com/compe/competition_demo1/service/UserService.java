package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.User;

import java.util.List;

/*用户类UserBean的功能接口*/
public interface UserService {
    /**
     * 用户登录方法
     * @return
     */
    public User LoginUser(String user_in, String user_password);

    /**
     * 用户注册方法
     * @param user 用户对象
     * @return boolean 注册成功返回true,注册失败返回false
     */
    public boolean registerUser(User user);

    /**
     * 用户修改方法
     * @return boolean 修改成功返回true,注册失败返回false
     */
    public boolean changeUser_identity(User user);
    public boolean changeUser_name(User user);
    public boolean changeUser_password(User user);
    public boolean changeUser_mail(User user);
    public boolean changeUser_phone(User user);
    public boolean changeUser_picture(User user);

    //修改用户信息
    //身份

}

