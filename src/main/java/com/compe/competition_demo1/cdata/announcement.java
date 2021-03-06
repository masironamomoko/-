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
public class announcement<String> {
    @TableId(value = "announcement_id",type = IdType.AUTO)
    private Integer inform_id;
    private String title;
    private String essay;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private  String author;
    private  Integer inform_check;
}