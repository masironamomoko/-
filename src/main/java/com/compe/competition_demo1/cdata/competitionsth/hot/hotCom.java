package com.compe.competition_demo1.cdata.competitionsth.hot;

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

public class hotCom {
    private Integer com_id;
    private String com_mainname;
    private Integer com_status;
    private Integer com_manager;
    @JSONField(format = "yyyy-MM-dd")
    private Date sign_up_start;
    @JSONField(format = "yyyy-MM-dd")
    private Date sign_up_end;
    private String com_year;
}
