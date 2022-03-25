package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.JsonResult;
import com.compe.competition_demo1.cdata.*;
import com.compe.competition_demo1.service.announcementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/inform")
public class announcementController {
    @Autowired
    @Resource private announcementService service;
    @RequestMapping(value = "add")
    public int addAnnounce(@RequestBody inform_add_in ann, HttpServletResponse response){
        return service.addAnnounce(ann);
    }

    @GetMapping(value = "delete")
    public int deleteAnnounce(String inform_id){
        return service.deleteAnnounce(inform_id);
    }

    @RequestMapping(value = "update")
    public int updateAnnounce(@RequestBody inform_update_in ann, HttpServletResponse response) {
        return service.updateAnnounce(ann);
    }

    @RequestMapping(value = "findall")
    public inform_list_out findAllAnnounce(int pageNum, int pageSize, HttpServletResponse response) throws SQLException {
        return service.announceFindAll(pageNum,pageSize);
    }

    @RequestMapping(value="leader")
    public List<announcement> inquireInform(HttpServletResponse response) throws SQLException {
        return service.informInquire();
    }

    @RequestMapping(value="keysearch")
    public inform_list_out keySearchInform(int pageNum, int pageSize, String key, HttpServletResponse response) throws SQLException {
        return service.informKeySearch(pageNum,pageSize,key);
    }

    @RequestMapping(value="datesearch")
    public inform_list_out dateSearchInform(int pageNum, int pageSize, String date, HttpServletResponse response) throws SQLException {
        return service.informDateSearch(pageNum,pageSize,date);
    }

    @RequestMapping(value="idsearch")
    public inform_id_out idSearchInform(String id, HttpServletResponse response) throws SQLException {
        return service.informIdSearch(id);
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
    public int ControlNews(int inform_id,int check)throws SQLException{
        return service.InformControl(inform_id,check);
    }
}
