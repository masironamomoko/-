package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.cate_io.data;

import java.util.List;

public interface CateService {
    int AddCategory(String cate_name);
    List<data> FindAll();
}
