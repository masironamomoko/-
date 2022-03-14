package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.News;

import java.sql.SQLException;
import java.util.List;

public interface NewsService {
    void addNews(News news);
    void deleteNews(int news_id);
    void updateNews(News news);
    List<News> newsFindAll() throws SQLException;
}
