package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.News;
import com.compe.competition_demo1.cdata.announcement;

import java.sql.SQLException;
import java.util.List;

public interface NewsService {
    int NewsTotalDate(String date);
    int NewsTotalKey(String key);
    int addNews(News news);
    int deleteNews(String news_id);
    int updateNews(News news);
    List<News> NewsFindAll(int pageNum,int pageSize) throws SQLException;
    List<News> NewsInquire() throws SQLException;
    List<News> NewsKeySearch(int pageNum, int pageSize, String key) throws SQLException;
    List<News> NewsDateSearch(int pageNum, int pageSize, String date) throws SQLException;
    News NewsIdSearch(String id) throws SQLException;
    List<News> NewsNoPassSearch(String user_id) throws SQLException;
    List<News> NewsPassSearch(String user_id) throws SQLException;
    List<News> NewsConnopassSearch()throws SQLException;
    List<News> NewsConpassSearch()throws SQLException;
    int NewsControl(int news_id,int check);
}
