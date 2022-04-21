package com.compe.competition_demo1.cdata.patent_io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

@Data   //添加getter/setter
@NoArgsConstructor     //添加无参构造器
@AllArgsConstructor     //添加全参构造器
@Accessors(chain = true)    //添加链式调用
public class patent_add_in {
    private Integer user_id;
    private String cate_name;
    private String com_num;
    private String patent_name;
    private MultipartFile patent_prove;
}
