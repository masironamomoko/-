package com.compe.competition_demo1.cdata.award_io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用
public class award_category_out {
    private Integer allc;
    private Integer allp;
    @Data
    public class category{
        @Data
        public class class1{
            private String name="体育类";
            private Integer num;
            private Integer sign;
            private Integer award1;
            private Integer award2;
            private Integer award3;
            private Integer awardOther;
        }
        @Data
        public class class2{
            private String name="艺术类";
            private Integer num;
            private Integer sign;
            private Integer award1;
            private Integer award2;
            private Integer award3;
            private Integer awardOther;
        }
        @Data
        public class class3{
            private String name="科技类";
            private Integer num;
            private Integer sign;
            private Integer award1;
            private Integer award2;
            private Integer award3;
            private Integer awardOther;
        }
        @Data
        public class class4{
            private String name="电子类";
            private Integer num;
            private Integer sign;
            private Integer award1;
            private Integer award2;
            private Integer award3;
            private Integer awardOther;
        }
    }
}
