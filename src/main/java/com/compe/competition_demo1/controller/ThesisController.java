package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.cdata.thesis_io.*;
import com.compe.competition_demo1.service.ThesisService;
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
@RequestMapping("/thesis")
public class ThesisController {
    @Autowired
    @Resource private ThesisService service;
    @RequestMapping(value="add",method = RequestMethod.POST)
    @ResponseBody
    public int add(HttpServletRequest req){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) req);
        MultipartFile file=((MultipartHttpServletRequest) req).getFile("thesis_essay");
        thesis_add_in thesis=new thesis_add_in();
        thesis.setThesis_essay(file);
        thesis.setThesis_name(params.getParameter("thesis_name"));
        thesis.setCate_name(params.getParameter("cate_name"));
        thesis.setCom_num(params.getParameter("com_num"));
        thesis.setUser_id(Integer.valueOf(params.getParameter("user_id")));
        return service.AddThesis(thesis);
    }
    @RequestMapping(value="delete")
    public int delete(@RequestBody Map<String,Object> param){
        Integer id=Integer.parseInt(param.get("thesis_id").toString());
        return service.DeleteThesis(id);
    }
    @RequestMapping(value="idsearch")
    public thesis_idsearch_out idsearch(@RequestBody Map<String,Object> param){
        Integer id=Integer.parseInt(param.get("thesis_id").toString());
        return service.IdSearch(id);
    }
    //学生未审核论文
    @RequestMapping(value="stu_nopass")
    public List<thesis_stunopass_out> thestunopass(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException {
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.thestunopass(user_id);
    }
    @RequestMapping(value="stu_pass")
    public List<thesis_stupass_out> thestupass(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.thestupass(user_id);
    }
    @RequestMapping(value="stu_nonopass")
    public List<thesis_stupass_out> thestunonopass(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.thestunonopass(user_id);
    }

    //竞赛负责人的未审核论文
    @RequestMapping(value="man_nopass")
    public List<thesis_mannopass_out> themannopass(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.themannopass(user_id);
    }
    @RequestMapping(value="man_pass")
    public List<thesis_manpass_out> themanpass(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException {
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.themanpass(user_id);
    }

    //项目管理员的未审核获奖
    @RequestMapping(value = "con_nopass")
    public List<thesis_mannopass_out> theconnopass(HttpServletResponse response){
        return service.theconnopass();
    }
    @RequestMapping(value = "con_pass")
    public List<thesis_manpass_out> theconpass(HttpServletResponse response){
        return service.theconpass();
    }

    //审核获奖信息
    @RequestMapping(value = "check")
    public int thecheck(@RequestBody thesis_check_in thesis_check_in, HttpServletResponse response){
        return service.thecheck(thesis_check_in);
    }
}
