package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.cdata.patent_io.*;
import com.compe.competition_demo1.service.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patent")
public class PatentController {
    @Autowired
    @Resource private PatentService service;
    @RequestMapping(value="add",method = RequestMethod.POST)
    @ResponseBody
    public int add(HttpServletRequest req){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) req);
        MultipartFile file=((MultipartHttpServletRequest) req).getFile("patent_prove");
        patent_add_in patent=new patent_add_in();
        patent.setPatent_prove(file);
        patent.setPatent_name(params.getParameter("patent_name"));
        patent.setCate_name(params.getParameter("cate_name"));
        patent.setCom_num(params.getParameter("com_num"));
        patent.setUser_id(Integer.valueOf(params.getParameter("user_id")));
        return service.AddPatent(patent);
    }
    @RequestMapping(value="delete")
    public int delete(@RequestBody Map<String,Object> param){
        Integer id=Integer.parseInt(param.get("patent_id").toString());
        return service.DeletePatent(id);
    }
    @RequestMapping(value="idsearch")
    public patent_idsearch_out idsearch(@RequestBody Map<String,Object> param){
        Integer id=Integer.parseInt(param.get("patent_id").toString());
        return service.IdSearch(id);
    }
    //学生未审核专利
    @RequestMapping(value="stu_nopass")
    public List<patent_stunopass_out> pastunopass(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException {
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.pastunopass(user_id);
    }
    @RequestMapping(value="stu_pass")
    public List<patent_stupass_out> pastupass(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException {
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.pastupass(user_id);
    }
    @RequestMapping(value="stu_nonopass")
    public List<patent_stupass_out> pastunonopass(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.pastunonopass(user_id);
    }

    //竞赛负责人的未审核专利
    @RequestMapping(value="man_nopass")
    public List<patent_mannopass_out> pamannopass(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.pamannopass(user_id);
    }
    @RequestMapping(value="man_pass")
    public List<patent_manpass_out> pamanpass(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.pamanpass(user_id);
    }

    //项目管理员的未审核专利
    @RequestMapping(value = "con_nopass")
    public List<patent_mannopass_out> paconnopass(HttpServletResponse response){
        return service.paconnopass();
    }
    @RequestMapping(value = "con_pass")
    public List<patent_manpass_out> paconpass(HttpServletResponse response){
        return service.paconpass();
    }

    //审核专利信息
    @RequestMapping(value = "check")
    public int pacheck(@RequestBody patent_check_in patent_check_in, HttpServletResponse response){
        return service.pacheck(patent_check_in);
    }
    //学生的未审核获奖个数
    @RequestMapping(value="stu_count")
    public int StuCount(@RequestBody Map<String,Object> param){
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.StuCount(user_id);
    }
    @RequestMapping(value="stu_nocount")
    public int StunoCount(@RequestBody Map<String,Object> param){
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.StunoCount(user_id);
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
