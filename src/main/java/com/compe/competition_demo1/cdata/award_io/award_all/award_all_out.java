package com.compe.competition_demo1.cdata.award_io.award_all;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用

public class award_all_out {
    private Integer allc;//竞赛总数
    private List<String> cates;
    private List<String> levels;
    private List<Integer> cate;
    private List<CateValue> cateValue;
    private List<LevelValue> levelValue;
}
