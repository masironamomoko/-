package com.compe.competition_demo1.cdata.news_io;

import com.compe.competition_demo1.cdata.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用
public class news_list_out {
    private int total;
    public List<News> newsList;
}
