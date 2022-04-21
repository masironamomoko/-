package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.cdata.thesis_io.thesis_add_in;
import com.compe.competition_demo1.cdata.thesis_io.thesis_idsearch_out;
import com.compe.competition_demo1.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
}
