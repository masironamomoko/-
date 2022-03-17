package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.JsonResult;
import com.compe.competition_demo1.cdata.News;
import com.compe.competition_demo1.cdata.announcement;
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
    @RequestMapping(value = "add")
    public JsonResult<Integer> addNews(@RequestBody News news, HttpServletResponse response){
        return new JsonResult<>(service.addNews(news));
    }

    @GetMapping(value = "delete")
    public JsonResult<Integer> deleteNews(String news_id){
        return new JsonResult<>(service.deleteNews(news_id));
    }

    @RequestMapping(value = "update")
    public JsonResult<Integer> updateNews(@RequestBody News news, HttpServletResponse response) {
        return new JsonResult<>(service.updateNews(news));
    }

    @RequestMapping(value = "findall")
    public List<News> findAllNews(int pageNum, int pageSize, HttpServletResponse response) throws SQLException {
        return service.NewsFindAll(pageNum,pageSize);
    }

    @RequestMapping(value="leader")
    public List<News> inquireNews(HttpServletResponse response) throws SQLException {
        return service.NewsInquire();
    }

    @RequestMapping(value="keysearch")
    public List<News> keySearchNews(int pageNum, int pageSize, String key, HttpServletResponse response) throws SQLException {
        return service.NewsKeySearch(pageNum,pageSize,key);
    }

    @RequestMapping(value="datesearch")
    public List<News> dateSearchNews(int pageNum, int pageSize, String date, HttpServletResponse response) throws SQLException {
        return service.NewsDateSearch(pageNum,pageSize,date);
    }

    @RequestMapping(value="idsearch")
    public JsonResult<News> idSearchNews(String id, HttpServletResponse response) throws SQLException {
        return new JsonResult<>(service.NewsIdSearch(id));
    }

    @RequestMapping(value="userfind_nopass")
    public List<News> SearchNoPassNews(String user_id,HttpServletResponse response)throws SQLException{
        return service.NewsNoPassSearch(user_id);
    }

    @RequestMapping(value="userfind_pass")
    public List<News> SearchPassNews(String user_id,HttpServletResponse response)throws SQLException{
        return service.NewsPassSearch(user_id);
    }

    @RequestMapping(value="con_nopass")
    public List<News> SearchConnopassNews(HttpServletResponse response)throws SQLException{
        return service.NewsConnopassSearch();
    }

    @RequestMapping(value="con_pass")
    public List<News> SearchConpassNews(HttpServletResponse response)throws SQLException{
        return service.NewsConpassSearch();
    }

    @RequestMapping(value ="control")
    public JsonResult<Integer> ControlNews(int news_id,int check)throws SQLException{
        return new JsonResult<>(service.NewsControl(news_id,check));
    }
}

