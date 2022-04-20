package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.cdata.*;
import com.compe.competition_demo1.cdata.news_io.*;
import com.compe.competition_demo1.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

    @RequestMapping(value = "delete")
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
    public int ControlNews(@RequestBody news_check_in newsCheckIn)throws SQLException{
        return service.NewsControl(newsCheckIn);
    }
    @RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        String name=params.getParameter("name");
        System.out.println("name:"+name);
        String id=params.getParameter("id");
        System.out.println("id:"+id);
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "You failed to upload " + i + " => "
                            + e.getMessage();
                }
            } else {
                return "You failed to upload " + i
                        + " because the file was empty.";
            }
        }
        return "upload successful";
    }
}

