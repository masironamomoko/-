package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.JsonResult;
import com.compe.competition_demo1.cdata.announcement;
import com.compe.competition_demo1.service.announcementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/inform")
public class announcementController {
    @Autowired
    private announcementService service;
    @RequestMapping(value = "add")
    public JsonResult<Integer> addAnnounce(@RequestBody announcement ann, HttpServletResponse response){
        return new JsonResult<>(service.addAnnounce(ann));
    }

    @GetMapping(value = "delete")
    public JsonResult<Integer> deleteAnnounce(String inform_id){
        return new JsonResult<>(service.deleteAnnounce(inform_id));
    }

    @RequestMapping(value = "update")
    public JsonResult<Integer> updateAnnounce(@RequestBody announcement ann, HttpServletResponse response) {
        return new JsonResult<>(service.updateAnnounce(ann));
    }

    @RequestMapping(value = "findall")
    public List<announcement> findAllAnnounce(int pageNum, int pageSize, HttpServletResponse response) throws SQLException {
        return service.announceFindAll(pageNum,pageSize);
    }

    @RequestMapping(value="leader")
    public List<announcement> inquireInform(HttpServletResponse response) throws SQLException {
        return service.informInquire();
    }

    @RequestMapping(value="keysearch")
    public List<announcement> keySearchInform(int pageNum, int pageSize, String key, HttpServletResponse response) throws SQLException {
        return service.informKeySearch(pageNum,pageSize,key);
    }

    @RequestMapping(value="datesearch")
    public List<announcement> dateSearchInform(int pageNum, int pageSize, String date, HttpServletResponse response) throws SQLException {
        return service.informDateSearch(pageNum,pageSize,date);
    }

    @RequestMapping(value="idsearch")
    public JsonResult<announcement> idSearchInform(String id, HttpServletResponse response) throws SQLException {
        return new JsonResult<>(service.informIdSearch(id));
    }

    @RequestMapping(value="userfind_nopass")
    public List<announcement> SearchNoPass(String user_id,HttpServletResponse response)throws SQLException{
        return service.InformNoPassSearch(user_id);
    }

    @RequestMapping(value="userfind_pass")
    public List<announcement> SearchPass(String user_id,HttpServletResponse response)throws SQLException{
        return service.InformPassSearch(user_id);
    }

    @RequestMapping(value="con_nopass")
    public List<announcement> SearchConnopass(HttpServletResponse response)throws SQLException{
        return service.InformConnopassSearch();
    }

    @RequestMapping(value="con_pass")
    public List<announcement> SearchConpass(HttpServletResponse response)throws SQLException{
        return service.InformConpassSearch();
    }

    @RequestMapping(value ="control")
    public JsonResult<Integer> ControlNews(int inform_id,int check)throws SQLException{
        return new JsonResult<>(service.InformControl(inform_id,check));
    }
}
