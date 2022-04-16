package com.compe.competition_demo1.service;


import com.compe.competition_demo1.cdata.award_io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.compe.competition_demo1.cdata.award_io.award_year_out.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
        sql="select count(*) from competition where com_level='A类' and year(sign_up_start)="+year+"";
        level.setLevel1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_level='B类' and year(sign_up_start)="+year+"";
        level.setLevel1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_level='C类' and year(sign_up_start)="+year+"";
        level.setLevel1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_level='D类' and year(sign_up_start)="+year+"";
        level.setLevel1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_level='E类' and year(sign_up_start)="+year+"";
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
        YearOut.setAwardOther(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=0 and year(date)="+year+"";
        YearOut.setAward0(jdbcTemplate.queryForObject(sql,Integer.class));
        return YearOut;
    }

    @Override
    public award_major_out AnalysisMajor(award_date_in awardDateIn) {
        award_major_out awardMajorOut=new award_major_out();
        award_major_out.major major=awardMajorOut.new major();
        award_major_out.major.major1 m1=major.new major1();
        award_major_out.major.major2 m2=major.new major2();
        award_major_out.major.major3 m3=major.new major3();
        String sql="select count(*) from competition where sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"'";
        awardMajorOut.setAllc(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where date >='"+awardDateIn.getDate1()+"' and date <='"+awardDateIn.getDate2()+"'";
        awardMajorOut.setAllp(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_major='计算机科学与技术' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"'";
        m1.setNum(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_major='物联网' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"'";
        m2.setNum(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_major='信息安全技术' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"'";
        m3.setNum(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where com_id in (select com_id from competition where com_major='计算机科学与技术' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        m1.setSign(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where com_id in (select com_id from competition where com_major='物联网' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        m2.setSign(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where com_id in (select com_id from competition where com_major='信息安全技术' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        m3.setSign(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=1 and com_id in (select com_id from competition where com_major='计算机科学与技术' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        m1.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=2 and com_id in (select com_id from competition where com_major='计算机科学与技术' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        m1.setAward2(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=3 and com_id in (select com_id from competition where com_major='计算机科学与技术' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        m1.setAward3(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=4 and com_id in (select com_id from competition where com_major='计算机科学与技术' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        m1.setAwardOther(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=1 and com_id in (select com_id from competition where com_major='物联网' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        m2.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=2 and com_id in (select com_id from competition where com_major='物联网' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        m2.setAward2(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=3 and com_id in (select com_id from competition where com_major='物联网' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        m2.setAward3(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=4 and com_id in (select com_id from competition where com_major='物联网' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        m2.setAwardOther(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=1 and com_id in (select com_id from competition where com_major='信息安全技术' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        m3.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=2 and com_id in (select com_id from competition where com_major='信息安全技术' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        m3.setAward2(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=3 and com_id in (select com_id from competition where com_major='信息安全技术' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        m3.setAward3(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=4 and com_id in (select com_id from competition where com_major='信息安全技术' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        m3.setAwardOther(jdbcTemplate.queryForObject(sql,Integer.class));
        return (awardMajorOut);
    }

    @Override
    public award_category_out AnalysisCategory(award_date_in awardDateIn) {
        award_category_out awardCategoryOut=new award_category_out();
        award_category_out.category category=awardCategoryOut.new category();
        award_category_out.category.class1 c1=category.new class1();
        award_category_out.category.class2 c2=category.new class2();
        award_category_out.category.class3 c3=category.new class3();
        award_category_out.category.class4 c4=category.new class4();
        String sql="select count(*) from competition where sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"'";
        awardCategoryOut.setAllc(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where date >='"+awardDateIn.getDate1()+"' and date <='"+awardDateIn.getDate2()+"'";
        awardCategoryOut.setAllp(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_catagory='体育类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"'";
        c1.setNum(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_catagory='艺术类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"'";
        c2.setNum(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_catagory='科技类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"'";
        c3.setNum(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_catagory='电子类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"'";
        c4.setNum(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where com_id in (select com_id from competition where com_category='体育类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c1.setSign(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where com_id in (select com_id from competition where com_category='艺术类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c2.setSign(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where com_id in (select com_id from competition where com_category='科技类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c3.setSign(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where com_id in (select com_id from competition where com_category='电子类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c4.setSign(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=1 and com_id in (select com_id from competition where com_category='体育类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c1.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=2 and com_id in (select com_id from competition where com_category='体育类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c1.setAward2(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=3 and com_id in (select com_id from competition where com_category='体育类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c1.setAward3(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=4 and com_id in (select com_id from competition where com_category='体育类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c1.setAwardOther(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=1 and com_id in (select com_id from competition where com_category='艺术类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c2.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=2 and com_id in (select com_id from competition where com_category='艺术类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c2.setAward2(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=3 and com_id in (select com_id from competition where com_category='艺术类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c2.setAward3(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=4 and com_id in (select com_id from competition where com_category='艺术类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c2.setAwardOther(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=1 and com_id in (select com_id from competition where com_category='科技类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c3.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=2 and com_id in (select com_id from competition where com_category='科技类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c3.setAward2(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=3 and com_id in (select com_id from competition where com_category='科技类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c3.setAward3(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=4 and com_id in (select com_id from competition where com_category='科技类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c3.setAwardOther(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=1 and com_id in (select com_id from competition where com_category='电子类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c4.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=2 and com_id in (select com_id from competition where com_category='电子类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c4.setAward2(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=3 and com_id in (select com_id from competition where com_category='电子类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c4.setAward3(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=4 and com_id in (select com_id from competition where com_category='电子类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        c4.setAwardOther(jdbcTemplate.queryForObject(sql,Integer.class));
        return awardCategoryOut;
    }

    @Override
    public award_level_out AnalysisLevel(award_date_in awardDateIn) {
        award_level_out awardLevelOut=new award_level_out();
        award_level_out.level level=awardLevelOut.new level();
        award_level_out.level.level1 l1=level.new level1();
        award_level_out.level.level2 l2=level.new level2();
        award_level_out.level.level3 l3=level.new level3();
        award_level_out.level.level4 l4=level.new level4();
        award_level_out.level.level5 l5=level.new level5();
        String sql="select count(*) from competition where sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"'";
        awardLevelOut.setAllc(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where date >='"+awardDateIn.getDate1()+"' and date <='"+awardDateIn.getDate2()+"'";
        awardLevelOut.setAllp(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_level='A类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"'";
        l1.setNum(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_level='B类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"'";
        l2.setNum(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_level='C类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"'";
        l3.setNum(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_level='D类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"'";
        l4.setNum(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from competition where com_level='E类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"'";
        l5.setNum(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where com_id in (select com_id from competition where com_level='A类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l1.setSign(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where com_id in (select com_id from competition where com_level='B类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l2.setSign(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where com_id in (select com_id from competition where com_level='C类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l3.setSign(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where com_id in (select com_id from competition where com_level='D类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l4.setSign(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where com_id in (select com_id from competition where com_level='E类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l5.setSign(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=1 and com_id in (select com_id from competition where com_level='A类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l1.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=1 and com_id in (select com_id from competition where com_level='B类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l2.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=1 and com_id in (select com_id from competition where com_level='C类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l3.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=1 and com_id in (select com_id from competition where com_level='D类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l4.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=1 and com_id in (select com_id from competition where com_level='E类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l5.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=2 and com_id in (select com_id from competition where com_level='A类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l1.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=2 and com_id in (select com_id from competition where com_level='B类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l2.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=2 and com_id in (select com_id from competition where com_level='C类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l3.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=2 and com_id in (select com_id from competition where com_level='D类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l4.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=2 and com_id in (select com_id from competition where com_level='E类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l5.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=3 and com_id in (select com_id from competition where com_level='A类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l1.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=3 and com_id in (select com_id from competition where com_level='B类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l2.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=3 and com_id in (select com_id from competition where com_level='C类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l3.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=3 and com_id in (select com_id from competition where com_level='D类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l4.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=3 and com_id in (select com_id from competition where com_level='E类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l5.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=4 and com_id in (select com_id from competition where com_level='A类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l1.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=4 and com_id in (select com_id from competition where com_level='B类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l2.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=4 and com_id in (select com_id from competition where com_level='C类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l3.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=4 and com_id in (select com_id from competition where com_level='D类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l4.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        sql="select count(*) from registration_management where award=4 and com_id in (select com_id from competition where com_level='E类' and sign_up_start >='"+awardDateIn.getDate1()+"' and sign_up_start <='"+awardDateIn.getDate2()+"')";
        l5.setAward1(jdbcTemplate.queryForObject(sql,Integer.class));
        return awardLevelOut;
    }

    @Override
    public int AddAward(award_add_in awardAddIn) {
        Integer format = awardAddIn.getUser_id();
        // File folder = new File(realPath + format);

        File folder=new File("D:/图片/"+format);
        if (!folder.isDirectory())
        {
            if (!folder.mkdirs())
            {
                return 700;
            }
        }
        String oldName = awardAddIn.getAward_prove().getOriginalFilename();
        assert oldName != null;
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try
        {
            awardAddIn.getAward_prove().transferTo(new File(folder, newName));
            //以上都是普通代码, 这里的/files/ 对应的就是你在WebMvcConfig设置的地址映射
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        String path="D:/图片/"+format+"/"+newName;
        String sql="select cate_id from category where cate_name='"+awardAddIn.getCate_name()+"'";
        Integer cate_id=jdbcTemplate.queryForObject(sql,Integer.class);
        sql="select com_id from competition where cate_id='"+cate_id+"' and com_num='"+awardAddIn.getCom_num()+"'";
        Integer com_id=jdbcTemplate.queryForObject(sql,Integer.class);
        sql="insert into award(award_id,user_id,award_check,award_prove,com_id,award_level) values(null,?,0,?,?,?)";
        jdbcTemplate.update(sql,awardAddIn.getUser_id(),path,com_id,awardAddIn.getAward_level());
        sql="select count(*) from award where user_id='"+awardAddIn.getUser_id()+"' and award_prove='"+path+"' and com_id='"+com_id+"' and award_level='"+awardAddIn.getAward_level()+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count==1)
            return 666;
        return 700;
    }

    @Override
    public int DeleteAward(Integer id) {
        String sql="delete from award where award_id=?";
        jdbcTemplate.update(sql,id);
        sql="select count(*) from award where award_id='"+id+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=0)
            return 700;
        return 666;
    }

    @Override
    public int UpdateAward(award_update_in awardUpdateIn) {
        String sql="select cate_id from category where cate_name='"+awardUpdateIn.getCate_name()+"'";
        Integer cate_id=jdbcTemplate.queryForObject(sql,Integer.class);
        sql="select com_id from competition where cate_id='"+cate_id+"' and com_num='"+awardUpdateIn.getCom_num()+"'";
        Integer com_id=jdbcTemplate.queryForObject(sql,Integer.class);
        sql="update award set com_id=?, award_level=?, award_prove=? where award_id=?";
        jdbcTemplate.update(sql,com_id,awardUpdateIn.getAward_level(),awardUpdateIn.getAward_prove(),awardUpdateIn.getAward_id());
        sql="select count(*) from award where award_prove='"+awardUpdateIn.getAward_prove()+"' and com_id='"+com_id+"' and award_level='"+awardUpdateIn.getAward_level()+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count==1)
            return 666;
        return 700;
    }

    @Override
    public award_idsearch_out IdSearch(Integer id) {
        String sql="select * from award where award_id='"+id+"'";
        award_idsearch_out awardIdsearchOut=new award_idsearch_out();
        awardIdsearchOut=jdbcTemplate.queryForObject(sql,award_idsearch_out.class);
        return awardIdsearchOut;
    }
}
