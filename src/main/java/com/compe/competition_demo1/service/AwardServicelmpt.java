package com.compe.competition_demo1.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.compe.competition_demo1.cdata.award_io.award_year_out;
import com.compe.competition_demo1.cdata.award_io.award_year_out.*;

@Service
public class AwardServicelmpt implements AwardService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public award_year_out AnalysisYear(String year) {
        award_year_out YearOut=new award_year_out();
        major major=YearOut.new major();
        level level=YearOut.new level();
        category category=YearOut.new category();
        String sql="select count(*) from competition where year(sign_up_start)="+year+"";
        YearOut.setAllc(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where year(date)="+year+"";
        YearOut.setAllp(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_major='计算机科学与技术' and year(sign_up_start)="+year+"";
        major.setMajor1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_major='物联网' and year(sign_up_start)="+year+"";
        major.setMajor2(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_major='信息安全技术' and year(sign_up_start)="+year+"";
        major.setMajor3(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_level='A' and year(sign_up_start)="+year+"";
        level.setLevel1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_level='B' and year(sign_up_start)="+year+"";
        level.setLevel1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_level='C' and year(sign_up_start)="+year+"";
        level.setLevel1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_level='D' and year(sign_up_start)="+year+"";
        level.setLevel1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_level='E' and year(sign_up_start)="+year+"";
        level.setLevel1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_category='体育类' and year(sign_up_start)="+year+"";
        category.setCategory1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_category='艺术类' and year(sign_up_start)="+year+"";
        category.setCategory2(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_category='科技类' and year(sign_up_start)="+year+"";
        category.setCategory3(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_category='电子类' and year(sign_up_start)="+year+"";
        category.setCategory4(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=1 and year(date)="+year+"";
        YearOut.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=2 and year(date)="+year+"";
        YearOut.setAward2(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=3 and year(date)="+year+"";
        YearOut.setAward3(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=4 and year(date)="+year+"";
        YearOut.setAwardother(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=0 and year(date)="+year+"";
        YearOut.setAward0(jdbcTemplate.queryForObject(sql,Integer.class));
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
