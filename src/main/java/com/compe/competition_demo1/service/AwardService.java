package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.award_io.*;

public interface AwardService {
    award_year_out AnalysisYear(String year);
    award_major_out AnalysisMajor(award_date_in awardDateIn);
    award_category_out AnalysisCategory(award_date_in awardDateIn);
    award_level_out AnalysisLevel(award_date_in awardDateIn);
    int AddAward(award_add_in awardAddIn);
    int DeleteAward(Integer id);
    int UpdateAward(award_update_in awardUpdateIn);
    award_idsearch_out IdSearch(Integer id);
}
