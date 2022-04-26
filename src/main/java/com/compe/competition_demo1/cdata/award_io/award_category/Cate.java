package com.compe.competition_demo1.cdata.award_io.award_category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)

public class Cate{
    private List<String> award1;
    private List<String> award2;
    private List<String> award3;
}
