package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.award_io.award_year_out;

public interface AwardService {
    award_year_out AnalysisYear(String year);
    void AnalysisMajor();
    void AnalysisCategory();
    void AnalysisLevel();
}
