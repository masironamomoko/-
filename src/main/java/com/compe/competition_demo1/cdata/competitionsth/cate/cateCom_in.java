package com.compe.competition_demo1.cdata.competitionsth.cate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用

public class cateCom_in {
    private int pageNum;
    private int pageSize;
    private String category;
}
