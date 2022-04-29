package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.Award;
import com.compe.competition_demo1.cdata.award_io.*;
import com.compe.competition_demo1.cdata.award_io.award_all.award_all_out;
import com.compe.competition_demo1.cdata.award_io.award_category.award_category_in;
import com.compe.competition_demo1.cdata.award_io.award_category.award_category_out;

import java.util.List;

public interface AwardService {
    award_category_out awardcate(String cate_name);//竞赛类别获奖分析
    award_all_out awardall(String com_year);//总获奖分析
    int AddAward(award_add_in awardAddIn);
    int DeleteAward(Integer id);
    award_idsearch_out IdSearch(Integer id);
    List<award_stunopass_out> awastunopass(Integer user_id);  //学生的未审核获奖
    List<award_stupass_out> awastupass(Integer user_id);
    List<award_stupass_out> awastunonopass(Integer user_id);
    List<award_mannopass_out> awamannopass(Integer user_id);//竞赛负责人的未审核获奖
    List<award_manpass_out> awamanpass(Integer user_id);
    List<award_mannopass_out> awaconnopass();//项目管理员的未审核获奖
    List<award_manpass_out> awaconpass();
    int awacheck(award_check_in award_check_in);//审核获奖信息
    int StuCount(Integer user_id);
    int StunoCount(Integer user_id);
    int ManCount(Integer user_id);
    int ConCount();
    int AddExcel(Award award);
}
