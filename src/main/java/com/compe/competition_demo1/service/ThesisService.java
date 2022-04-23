package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.thesis_io.*;

import java.util.List;

public interface ThesisService {
    int AddThesis(thesis_add_in thesis);
    int DeleteThesis(Integer id);
    thesis_idsearch_out IdSearch(Integer id);
    String essay(Integer id);
    List<thesis_stunopass_out> thestunopass(Integer user_id);  //学生的未审核论文
    List<thesis_stupass_out> thestupass(Integer user_id);
    List<thesis_stupass_out> thestunonopass(Integer user_id);
    List<thesis_mannopass_out> themannopass(Integer user_id);  //竞赛负责人的未审核论文
    List<thesis_manpass_out> themanpass(Integer user_id);
    List<thesis_mannopass_out> theconnopass();  //项目管理员的未审核论文
    List<thesis_manpass_out> theconpass();
    int StuCount(Integer user_id);
    int StunoCount(Integer user_id);
    int ManCount(Integer user_id);
    int ConCount();
    int thecheck(thesis_check_in thesis_check_in);  //审核论文信息
}
