package com.compe.competition_demo1.cdata.competitionsth.stuCom;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用

public class stunoCom {
    private Integer com_id;
    @JSONField(format = "yyyy-MM-dd")
    private Date com_date;
    private String com_mainname;
    private Integer com_status;
    private String user_name;
    private String com_year;
}
