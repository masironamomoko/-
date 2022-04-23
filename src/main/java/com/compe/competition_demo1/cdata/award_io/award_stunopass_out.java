package com.compe.competition_demo1.cdata.award_io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用

public class award_stunopass_out {
    private Integer award_id;
    private String cate_name;
    private String com_num;
    private String award_level;
}
