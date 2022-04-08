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
    private class major{
        private Integer major1;
        private Integer major2;
        private Integer major3;
    }
    private class level{
        private Integer level1;
        private Integer level2;
        private Integer level3;
        private Integer level4;
        private Integer level5;
    }
    private class category{
        private Integer category1;
        private Integer category2;
        private Integer category3;
        private Integer category4;
    }
    private Integer reword1;
    private Integer reword2;
    private Integer reword3;
    private Integer rewordother;
    private Integer reword0;
}
