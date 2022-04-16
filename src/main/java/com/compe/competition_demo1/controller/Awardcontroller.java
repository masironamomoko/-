package com.compe.competition_demo1.controller;


import com.compe.competition_demo1.cdata.award_io.*;
import com.compe.competition_demo1.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @RequestMapping(value="add")
    public int add(@RequestBody award_add_in awardAddIn){
        return service.AddAward(awardAddIn);
    }
    @GetMapping(value="delete")
    public int delete(@RequestBody Map<String,Object> param){
        Integer id=Integer.parseInt(param.get("award_id").toString());
        return service.DeleteAward(id);
    }
    @RequestMapping(value="update")
    public int update(@RequestBody award_update_in awardUpdateIn){
        return service.UpdateAward(awardUpdateIn);
    }
    @RequestMapping(value="idsearch")
    public award_idsearch_out idsearch(@RequestBody Map<String,Object> param){
        Integer id=Integer.parseInt(param.get("award_id").toString());
        return service.IdSearch(id);
    }
    @PostMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest req)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
        String format = sdf.format(new Date());
        // File folder = new File(realPath + format);

        File folder=new File("D:/图片/"+format);
        if (!folder.isDirectory())
        {
            if (!folder.mkdirs())
            {
                return "文件夹创建失败";
            }
        }
        String oldName = file.getOriginalFilename();
        assert oldName != null;
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try
        {
            file.transferTo(new File(folder, newName));
            //以上都是普通代码, 这里的/files/ 对应的就是你在WebMvcConfig设置的地址映射
            return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/files/" + format + newName;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return "上传失败！";
    }
}
