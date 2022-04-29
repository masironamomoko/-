package com.compe.competition_demo1.cdata.competitionsth.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用

public class updateCom_in {
    private Integer com_id;
    private String com_num;           //竞赛类别
    private String com_level;             //级别
    private String com_major;             //专业
    private String cate_name;          //竞赛类别
    private String com_information;
    private Date sign_up_start;         //报名开始时间
    private Date sign_up_end;
    private Date preliminary_start;     //初赛开始时间
    private Date preliminary_end;
    private Date repecharge_start;      //复赛开始时间
    private Date repecharge_end;
    private Date finals_start;          //决赛开始时间
    private Date finals_end;
    private String com_year;
}
