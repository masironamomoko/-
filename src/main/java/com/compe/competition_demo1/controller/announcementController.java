package com.compe.competition_demo1.controller;

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
    public void addAnnounce(@RequestBody announcement ann, HttpServletResponse response){
        service.addAnnounce(ann);
    }
    @GetMapping(value = "delete")
    public void deleteAnnounce(String announcement_id){
        int id=Integer.parseInt(announcement_id);
        service.deleteAnnounce(id);
    }
    @RequestMapping(value = "update")
    public void updateNews(@RequestBody announcement ann, HttpServletResponse response) {
        service.updateAnnounce(ann);
    }
    @RequestMapping(value = "findAll")
    public List<announcement> findAllNews(HttpServletResponse response) throws SQLException {
        return service.announceFindAll();
    }
}
