package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.cdata.News;
import com.compe.competition_demo1.service.NewsService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@RestController

@RequestMapping("/news")
public class Newscontroller {
    @Autowired
    @Resource private NewsService service;
    @PostMapping(value = "add")
    public boolean addNews(@RequestBody News news, HttpServletResponse response){
        service.addNews(news);
        return true;
    }
    @GetMapping(value = "delete")
    public void deleteNews(String news_id){
        int id=Integer.parseInt(news_id);
        service.deleteNews(id);
    }
    @RequestMapping(value = "update")
    public void updateNews(@RequestBody News news, HttpServletResponse response) {
        service.updateNews(news);
    }

    @RequestMapping(value = "findAll")
    public List<News> findAllNews(HttpServletResponse response) throws SQLException {
        return service.newsFindAll();
    }
}

