package com.compe.competition_demo1.controller;


import com.compe.competition_demo1.cdata.award_io.*;
import com.compe.competition_demo1.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    public award_major_out MajorAnalysis(@RequestBody award_date_in awardDateIn)throws SQLException{
        return service.AnalysisMajor(awardDateIn);
    }
    @RequestMapping(value="category")
    public award_category_out CategoryAnalysis(@RequestBody award_date_in awardDateIn)throws SQLException{
        return service.AnalysisCategory(awardDateIn);
    }
    @RequestMapping(value="level")
    public award_level_out LevelAnalysis(@RequestBody award_date_in awardDateIn)throws SQLException{
        return service.AnalysisLevel(awardDateIn);
    }
    @RequestMapping(value="add",method=RequestMethod.POST)
    @ResponseBody
    public int add(HttpServletRequest req){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) req);
        MultipartFile file=((MultipartHttpServletRequest) req).getFile("award_prove");
        award_add_in awardAddIn=new award_add_in();
        awardAddIn.setAward_prove(file);
        awardAddIn.setAward_level(params.getParameter("award_level"));
        awardAddIn.setCate_name(params.getParameter("cate_name"));
        awardAddIn.setCom_num(params.getParameter("com_num"));
        awardAddIn.setUser_id(Integer.valueOf(params.getParameter("user_id")));
        return service.AddAward(awardAddIn);
    }
    @RequestMapping(value="delete")
    public int delete(@RequestBody Map<String,Object> param){
        Integer id=Integer.parseInt(param.get("award_id").toString());
        return service.DeleteAward(id);
    }
    @RequestMapping(value="idsearch")
    public award_idsearch_out idsearch(@RequestBody Map<String,Object> param){
        Integer id=Integer.parseInt(param.get("award_id").toString());
        return service.IdSearch(id);
    }

    //学生未审核获奖
    @RequestMapping(value="stu_nopass")
    public List<award_stunopass_out> awastunopass(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.awastunopass(user_id);
    }
    @RequestMapping(value="stu_pass")
    public List<award_stupass_out> awastupass(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.awastupass(user_id);
    }
    @RequestMapping(value="stu_nonopass")
    public List<award_stupass_out> awastunonopass(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.awastunonopass(user_id);
    }

    //竞赛负责人的未审核获奖
    @RequestMapping(value="man_nopass")
    public List<award_mannopass_out> awamannopass(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.awamannopass(user_id);
    }
    @RequestMapping(value="man_pass")
    public List<award_manpass_out> awamanpass(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.awamanpass(user_id);
    }

    //项目管理员的未审核获奖
    @RequestMapping(value = "con_nopass")
    public List<award_mannopass_out> awaconnopass(HttpServletResponse response){
        return service.awaconnopass();
    }
    @RequestMapping(value = "con_pass")
    public List<award_manpass_out> awaconpass(HttpServletResponse response){
        return service.awaconpass();
    }

    //审核获奖信息
    @RequestMapping(value = "check")
    public int awacheck(award_check_in award_check_in,HttpServletResponse response){
        return service.awacheck(award_check_in);
    }

    //学生的未审核获奖个数
    @RequestMapping(value="stu_count")
    public int StuCount(@RequestBody Map<String,Object> param){
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.StuCount(user_id);
    }
    //竞赛负责人的未审核获奖个数
    @RequestMapping(value="man_count")
    public int ManCount(@RequestBody Map<String,Object> param){
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.ManCount(user_id);
    }
    //所有的未审核获奖个数
    @RequestMapping(value = "con_count")
    public int ConCount(){
        return service.ConCount();
    }
}
