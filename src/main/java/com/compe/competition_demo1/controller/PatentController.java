package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.cdata.patent_io.patent_add_in;
import com.compe.competition_demo1.cdata.patent_io.patent_idsearch_out;
import com.compe.competition_demo1.service.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
}
