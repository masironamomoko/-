package com.compe.competition_demo1.cdata;
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
    private int user_identity;     //用户身份
    private String user_name;      //用户名
    private String user_password;  //用户密码
    private String user_email;     //用户邮箱
    private String user_phone;     //用户电话
    private String user_picture;   //用户头像
    private String user_num;       //用户学号

    @Override
    public String toString(){
        return "User{"+
                "user_id" + user_id +
                ",user_identity=" + user_identity + '\'' +
                ",user_name=" + user_name + '\'' +
                ",user_password" + user_password + '\'' +
                ",user_phone" + user_phone +'\'' +
                "}";
    }
}


