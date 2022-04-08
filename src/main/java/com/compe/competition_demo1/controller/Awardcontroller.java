package com.compe.competition_demo1.controller;


import com.compe.competition_demo1.cdata.News;
import com.compe.competition_demo1.cdata.award_io.award_year_out;
import com.compe.competition_demo1.service.AwardService;
import com.compe.competition_demo1.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/award")

public class Awardcontroller {
    @Autowired
    @Resource private AwardService service;
    @RequestMapping(value="year")
    public award_year_out YearAnalysis(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException{
        String year = (String)param.get("year");
        return service.AnalysisYear(year);
    }
    @RequestMapping(value="major")
    public void MajorAnalysis(HttpServletResponse response)throws SQLException{
        service.AnalysisMajor();
    }
    @RequestMapping(value="category")
    public void CategoryAnalysis(HttpServletResponse response)throws SQLException{
        service.AnalysisCategory();
    }
    @RequestMapping(value="level")
    public void LevelAnalysis(HttpServletResponse response)throws SQLException{
        service.AnalysisLevel();
    }
}
