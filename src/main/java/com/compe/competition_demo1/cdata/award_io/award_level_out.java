package com.compe.competition_demo1.cdata.award_io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用
public class award_level_out {
    private Integer allc;
    private Integer allp;
    @Data
    public class level{
        @Data
        public class level1{
            private String name="A类";
            private Integer num;
            private Integer sign;
            private Integer award1;
            private Integer award2;
            private Integer award3;
            private Integer awardOther;
        }
        @Data
        public class level2{
            private String name="B类";
            private Integer num;
            private Integer sign;
            private Integer award1;
            private Integer award2;
            private Integer award3;
            private Integer awardOther;
        }
        @Data
        public class level3{
            private String name="C类";
            private Integer num;
            private Integer sign;
            private Integer award1;
            private Integer award2;
            private Integer award3;
            private Integer awardOther;
        }
        @Data
        public class level4{
            private String name="D类";
            private Integer num;
            private Integer sign;
            private Integer award1;
            private Integer award2;
            private Integer award3;
            private Integer awardOther;
        }
        @Data
        public class level5{
            private String name="E类";
            private Integer num;
            private Integer sign;
            private Integer award1;
            private Integer award2;
            private Integer award3;
            private Integer awardOther;
        }
    }
}
