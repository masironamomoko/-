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
        @Data
        public class major1{
            private String name="计算机科学与技术";
            private Integer num;  //期间竞赛总数
            private Integer sign; //期间报名总数
            private Integer award1; //期间获一等奖数
            private Integer award2;
            private Integer award3;
            private Integer awardOther;
        }
        @Data
        public class major2{
            private String name="物联网";
            private Integer num;
            private Integer sign;
            private Integer award1;
            private Integer award2;
            private Integer award3;
            private Integer awardOther;
        }
        @Data
        public class major3{
            private String name="信息安全技术";
            private Integer num;
            private Integer sign;
            private Integer award1;
            private Integer award2;
            private Integer award3;
            private Integer awardOther;
        }
    }
}
