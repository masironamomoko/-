package com.compe.competition_demo1.cdata;

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

public class registration_management {
    @TableId(value = "registration_id",type = IdType.AUTO)
    private Integer user_id;
    private Integer com_id;
    private String declaration;
    private String opening_report;
    private String interim_report;
    private String closing_report;
    private String award;
    private String team_name;
    private Integer registration_id;
}
