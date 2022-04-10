package com.compe.competition_demo1.cdata;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用
public class News<String> {
    @TableId(value = "news_id",type = IdType.AUTO)
    private Integer news_id;
    private String title;
    private String essay;
    private String author;
    private String user_name;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private Integer news_check;
    public void main(java.lang.String[] args){
        java.lang.String sql="select user_name from user where user_id='"+author+"'";
        
    }
}
