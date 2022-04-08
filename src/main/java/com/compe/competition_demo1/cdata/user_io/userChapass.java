package com.compe.competition_demo1.cdata.user_io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用

public class userChapass {
    private Integer user_id;
    private String user_password_old;
    private String user_password_new;
}
