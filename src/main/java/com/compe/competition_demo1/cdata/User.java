package com.compe.competition_demo1.cdata;

import lombok.experimental.Accessors;
/*用户基本类*/

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用

public class User {
    //成员变量区
    @TableId(value = "user_id",type = IdType.AUTO)
    private int user_id;           //用户id
    private String user_identity;  //用户身份
    private String user_name;      //用户名
    private String user_password;  //用户密码
    private String user_email;     //用户邮箱
    private String user_phone;     //用户电话
    private String user_picture;   //用户头像
}
//    //构造方法
//    public UserBean() {
//        super();
//    }
//
//    public UserBean(String user_name, String user_password, String user_email, String user_phone, String user_picture) {
//        super();
//        this.user_name = user_name;
//        this.user_password = user_password;
//        this.user_email = user_email;
//        this.user_phone = user_phone;
//        this.user_picture = user_picture;
//    }
//
//    //成员方法区
//    public String getuser_name() {
//        return user_name;
//    }
//    public void setuser_name(String user_name) {
//        this.user_name = user_name;
//    }
//
//    public String getuser_password() {
//        return user_password;
//    }
//    public void setuser_password(String user_password) {
//        this.user_password = user_password;
//    }
//
//    public String getUser_email() {
//        return user_email;
//    }
//    public void setUser_email(String user_email) {
//        this.user_email = user_email;
//    }
//
//    public String getUser_phone() {
//        return user_phone;
//    }
//    public void setUser_phone(String user_phone) {
//        this.user_phone = user_phone;
//    }
//
//    public String getUser_picture() {
//        return user_picture;
//    }
//    public void setUser_picture(String user_picture) {
//        this.user_picture = user_picture;
//    }
//
//    @Override
//    public String toString() {
//        return user_name +  user_password + user_email + user_phone + user_picture;
//    }


