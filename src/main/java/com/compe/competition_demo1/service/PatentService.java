package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.patent_io.*;

import java.util.List;

public interface PatentService {
    int AddPatent(patent_add_in patent);
    int DeletePatent(Integer id);
    patent_idsearch_out IdSearch(Integer id);
    List<patent_stunopass_out> pastunopass(Integer user_id);  //学生的未审核专利
    List<patent_stupass_out> pastupass(Integer user_id);
    List<patent_stupass_out> pastunonopass(Integer user_id);
    List<patent_mannopass_out> pamannopass(Integer user_id);  //竞赛负责人的未审核专利
    List<patent_manpass_out> pamanpass(Integer user_id);
    List<patent_mannopass_out> paconnopass();  //项目管理员的未审核专利
    List<patent_manpass_out> paconpass();
    int StuCount(Integer user_id);
    int StunoCount(Integer user_id);
    int ManCount(Integer user_id);
    int ConCount();
    int pacheck(patent_check_in patent_check_in);  //审核专利信息
}
