package com.compe.competition_demo1.cdata.award_io.award_all;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data   //添加gettertter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用

public class award_all_out {
    private List<String> comList;
    private comData comData;
    private List<awardValue> awardValue;
}