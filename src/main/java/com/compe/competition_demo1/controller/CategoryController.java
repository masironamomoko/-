package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.cdata.cate_io.cate_find_out;
import com.compe.competition_demo1.cdata.cate_io.data;
import com.compe.competition_demo1.cdata.cate_io.recate;
import com.compe.competition_demo1.service.AwardService;
import com.compe.competition_demo1.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cate")
public class CategoryController {
    @Autowired
    @Resource
    private CateService service;
    @RequestMapping(value="add")
    public int AddCategory(@RequestBody Map<String,Object> param){
        String cate_name=(String) param.get("cate_name");
        return service.AddCategory(cate_name);
    }
    @RequestMapping(value="findall")
    public List<String> FindAll(){
        return service.FindAll();
    }
    @RequestMapping(value="refindall")
    public List<recate>  ReFindall(){
        return service.ReFindall();
    }
    @RequestMapping(value="find")
    public List<cate_find_out>  Find(){ return service.Find();}
    @RequestMapping(value="delete")
    public int Delete(@RequestBody Map<String,Object> param){
        Integer cate_id=Integer.parseInt(param.get("cate_id").toString());
        return service.Delete(cate_id);
    }
}
