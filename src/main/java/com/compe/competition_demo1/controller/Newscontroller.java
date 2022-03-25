package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.cdata.*;
import com.compe.competition_demo1.service.NewsService;
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
    public int addNews(@RequestBody news_add_in news, HttpServletResponse response){
        return service.addNews(news);
    }

    @GetMapping(value = "delete")
    public int deleteNews(String news_id){
        return service.deleteNews(news_id);
    }

    @RequestMapping(value = "update")
    public int updateNews(@RequestBody news_update_in news, HttpServletResponse response) {
        return service.updateNews(news);
    }

    @RequestMapping(value = "findall")
    public news_list_out findAllNews(int pageNum, int pageSize, HttpServletResponse response) throws SQLException {
        return service.NewsFindAll(pageNum,pageSize);
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
    public news_id_out idSearchNews(String id, HttpServletResponse response) throws SQLException {
        return service.NewsIdSearch(id);
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
    public int ControlNews(int news_id,int check)throws SQLException{
        return service.NewsControl(news_id,check);
    }
}

