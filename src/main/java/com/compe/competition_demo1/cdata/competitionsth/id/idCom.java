package com.compe.competition_demo1.cdata.competitionsth.id;

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

public class idCom {
    private Integer com_id;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date com_date;
    private String com_mainname;
    private String user_name;
    private String com_num;           //竞赛副标题
    private String com_level;             //级别
    private String com_major;             //专业
    private String com_category;          //竞赛类别
    private String com_information;
    @JSONField(format = "yyyy-MM-dd")
    private Date sign_up_start;         //报名开始时间
    @JSONField(format = "yyyy-MM-dd")
    private Date sign_up_end;
    @JSONField(format = "yyyy-MM-dd")
    private Date preliminary_start;     //初赛开始时间
    @JSONField(format = "yyyy-MM-dd")
    private Date preliminary_end;
    @JSONField(format = "yyyy-MM-dd")
    private Date repecharge_start;      //复赛开始时间
    @JSONField(format = "yyyy-MM-dd")
    private Date repecharge_end;
    @JSONField(format = "yyyy-MM-dd")
    private Date finals_start;          //决赛开始时间
    @JSONField(format = "yyyy-MM-dd")
    private Date finals_end;
    private Integer com_status;         //竞赛状态
    private Integer com_schedule;       //奖项结果进度
    private Integer sign_num;           //报名人数
    private Integer award1;             //一等奖人数
    private Integer award2;
    private Integer award3;
    private Integer award_other;
    private Integer com_check;          //审核结果
}
