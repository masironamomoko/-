package com.compe.competition_demo1.cdata.award_io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用
public class award_major_out {
    private Integer allc;
    private Integer allp;
    @Data
    public class major{
        private Integer major1;
        private Integer major2;
        private Integer major3;
    }
}
