package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.*;

import java.sql.SQLException;
import java.util.List;

public interface NewsService {
    int addNews(news_add_in news);
    int deleteNews(String news_id);
    int updateNews(news_update_in news);
    news_list_out NewsFindAll(int pageNum, int pageSize) throws SQLException;
    List<News> NewsInquire() throws SQLException;
    news_list_out NewsKeySearch(news_key_in news) throws SQLException;
    news_list_out NewsDateSearch(news_date_in news) throws SQLException;
    news_id_out NewsIdSearch(String id) throws SQLException;
    List<News> NewsNoPassSearch(String user_id) throws SQLException;
    List<News> NewsPassSearch(String user_id) throws SQLException;
    List<News> NewsConnopassSearch()throws SQLException;
    List<News> NewsConpassSearch()throws SQLException;
    int NewsControl(int news_id,int check);
}
