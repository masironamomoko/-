package com.compe.competition_demo1.cdata.competitionsth.findall;

import com.compe.competition_demo1.cdata.competitionsth.sthCom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用

public class findallCom_out {
    private Integer total;
    private List<sthCom> data;
}