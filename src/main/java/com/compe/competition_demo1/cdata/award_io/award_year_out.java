package com.compe.competition_demo1.cdata.award_io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用

public class award_year_out {
    private Integer allc;
    private Integer allp;
    @Data
    public class major{
        private Integer major1;
        private Integer major2;
        private Integer major3;
    }
    @Data
    public class level{
        private Integer level1;
        private Integer level2;
        private Integer level3;
        private Integer level4;
        private Integer level5;
    }
    @Data
    public class category{
        private Integer category1;
        private Integer category2;
        private Integer category3;
        private Integer category4;
    }
    private Integer award1;
    private Integer award2;
    private Integer award3;
    private Integer awardOther;
    private Integer award0;
}
