package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.award_io.*;

import java.util.List;

public interface AwardService {
    award_year_out AnalysisYear(String year);
    award_major_out AnalysisMajor(award_date_in awardDateIn);
    award_category_out AnalysisCategory(award_date_in awardDateIn);
    award_level_out AnalysisLevel(award_date_in awardDateIn);
    int AddAward(award_add_in awardAddIn);
    int DeleteAward(Integer id);
    award_idsearch_out IdSearch(Integer id);
    List<award_stunopass_out> awastunopass(Integer user_id);  //学生的未审核获奖
    List<award_stupass_out> awastupass(Integer user_id);
    List<award_mannopass_out> awamannopass(Integer user_id);//竞赛负责人的未审核获奖
    List<award_manpass_out> awamanpass(Integer user_id);
    List<award_mannopass_out> awaconnopass();//项目管理员的未审核获奖
    List<award_manpass_out> awaconpass();
    int awacheck(award_check_in award_check_in);//审核获奖信息
    int StuCount(Integer user_id);
    int ManCount(Integer user_id);
    int ConCount(Integer user_id);
}
