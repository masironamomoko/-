package com.compe.competition_demo1.cdata.thesis_io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用

public class thesis_stupass_out {
    private Integer thesis_id;
    private String cate_name;
    private String com_num;
    private String thesis_name;
    private Integer thesis_check;
}
