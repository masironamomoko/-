package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewsServicelmpt implements NewsService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addNews(News news) {
        String sql="insert into news(news_id,title,news_essay,news_author,date) values(null,?,?,?,?)";
        jdbcTemplate.update(sql,news.getTitle(),news.getNews_essay(),news.getNews_author(),news.getDate());
    }

    @Override
    public void deleteNews(int news_id) {
        String sql="delete from news where news_id=?";
        jdbcTemplate.update(sql,news_id);
    }

    @Override
    public void updateNews(News news){
        System.out.println(news.toString());
        String sql="update news set title=?, news_essay=?,news_author=?, date=? where news_id=?";
        jdbcTemplate.update(sql,news.getTitle(),news.getNews_essay(),news.getNews_author(),news.getDate(),news.getNews_id());
    }

    @Override
    public List<News> newsFindAll() throws SQLException {
        String sql="select * from news";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<News>(News.class));
    }
}
