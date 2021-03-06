package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.cate_io.cate_find_out;
import com.compe.competition_demo1.cdata.cate_io.data;
import com.compe.competition_demo1.cdata.cate_io.recate;

import java.util.List;

public interface CateService {
    int AddCategory(String cate_name);
    List<String> FindAll();
    List<recate> ReFindall();
    List<cate_find_out> Find();
    int Delete(Integer cate_id);
}
