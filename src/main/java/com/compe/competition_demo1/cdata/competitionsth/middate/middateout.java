package com.compe.competition_demo1.cdata.competitionsth.middate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用

public class middateout {
    private Integer com_id;
    private String com_mainname;
    private String user_name;
    private Date sign_up_start;
    private String com_level;
    private String com_category;
    private String com_major;
    private String com_year;
}
