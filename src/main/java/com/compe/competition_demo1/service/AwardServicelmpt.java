package com.compe.competition_demo1.service;


import com.compe.competition_demo1.cdata.*;
import com.compe.competition_demo1.cdata.news_io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.compe.competition_demo1.cdata.award_io.award_year_out;
import org.springframework.stereotype.Service;

@Service
public class AwardServicelmpt implements AwardService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public award_year_out AnalysisYear(String year) {
        award_year_out YearOut=new award_year_out();
        String sql="select count(*) from competition where year(date)='"+year+"'";
        YearOut.setAllc(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where year(date)='"+year+"'";
        YearOut.setAllp(jdbcTemplate.queryForObject(sql,Integer.class));
        return YearOut;
    }

    @Override
    public void AnalysisMajor() {

    }

    @Override
    public void AnalysisCategory() {

    }

    @Override
    public void AnalysisLevel() {

    }
}
