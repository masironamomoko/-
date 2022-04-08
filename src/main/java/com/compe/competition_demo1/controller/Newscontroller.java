package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.cdata.*;
import com.compe.competition_demo1.cdata.news_io.*;
import com.compe.competition_demo1.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/news")
public class Newscontroller {
    @Autowired
    @Resource private NewsService service;
    @RequestMapping(value = "add")
    public int addNews(@RequestBody news_add_in news, HttpServletResponse response){
        return service.addNews(news);
    }

    @GetMapping(value = "delete")
    public int deleteAnnounce(@RequestBody Map<String,Object> param){
        Integer id=Integer.parseInt(param.get("news_id").toString());
        return service.deleteNews(id);
    }

    @RequestMapping(value = "update")
    public int updateNews(@RequestBody news_update_in news, HttpServletResponse response) {
        return service.updateNews(news);
    }

    @RequestMapping(value = "findall")
    public news_list_out findAllNews(@RequestBody news_findall_in newsFindallIn) throws SQLException {
        return service.NewsFindAll(newsFindallIn);
    }

    @RequestMapping(value="leader")
    public List<News> inquireNews(HttpServletResponse response) throws SQLException {
        return service.NewsInquire();
    }

    @RequestMapping(value="keysearch")
    public news_list_out keySearchNews(@RequestBody news_key_in news, HttpServletResponse response) throws SQLException {
        return service.NewsKeySearch(news);
    }

    @RequestMapping(value="datesearch")
    public news_list_out dateSearchNews(@RequestBody news_date_in news, HttpServletResponse response) throws SQLException {
        return service.NewsDateSearch(news);
    }

    @RequestMapping(value="idsearch")
    public news_id_out idSearchNews(@RequestBody Map<String,Object> param) throws SQLException {
        Integer id=Integer.parseInt(param.get("news_id").toString());
        return service.NewsIdSearch(id);
    }

    @RequestMapping(value="userfind_nopass")
    public List<News> SearchNoPassNews(@RequestBody Map<String,Object> param)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.NewsNoPassSearch(user_id);
    }

    @RequestMapping(value="userfind_pass")
    public List<News> SearchPassNews(@RequestBody Map<String,Object> param)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
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
    public int ControlNews(news_check_in newsCheckIn)throws SQLException{
        return service.NewsControl(newsCheckIn);
    }
}

