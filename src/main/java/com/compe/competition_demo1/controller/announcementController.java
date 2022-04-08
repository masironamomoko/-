package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.cdata.*;
import com.compe.competition_demo1.cdata.inform_io.*;
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
import java.util.Map;

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
    public int deleteAnnounce(@RequestBody Map<String,Object> param){
        Integer id=Integer.parseInt(param.get("inform_id").toString());
        return service.deleteAnnounce(id);
    }

    @RequestMapping(value = "update")
    public int updateAnnounce(@RequestBody inform_update_in ann, HttpServletResponse response) {
        return service.updateAnnounce(ann);
    }

    @RequestMapping(value = "findall")
    public inform_list_out findAllAnnounce(@RequestBody inform_findall_in inform, HttpServletResponse response) throws SQLException {
        return service.announceFindAll(inform);
    }

    @RequestMapping(value="leader")
    public List<announcement> inquireInform(HttpServletResponse response) throws SQLException {
        return service.informInquire();
    }

    @RequestMapping(value="keysearch")
    public inform_list_out keySearchInform(@RequestBody inform_key_in informKeyIn, HttpServletResponse response) throws SQLException {
        return service.informKeySearch(informKeyIn);
    }

    @RequestMapping(value="datesearch")
    public inform_list_out dateSearchInform(@RequestBody inform_date_in informDateIn, HttpServletResponse response) throws SQLException {
        return service.informDateSearch(informDateIn);
    }

    @RequestMapping(value="idsearch")
    public inform_id_out idSearchNews(@RequestBody Map<String,Object> param) throws SQLException {
        Integer id=Integer.parseInt(param.get("inform_id").toString());
        return service.informIdSearch(id);
    }

    @RequestMapping(value="userfind_nopass")
    public List<announcement> SearchNoPass(@RequestBody Map<String,Object> param)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.InformNoPassSearch(user_id);
    }

    @RequestMapping(value="userfind_pass")
    public List<announcement> SearchPass(@RequestBody Map<String,Object> param)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
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
    public int ControlNews(@RequestBody inform_check_in informCheckIn)throws SQLException{
        return service.InformControl(informCheckIn);
    }
}
