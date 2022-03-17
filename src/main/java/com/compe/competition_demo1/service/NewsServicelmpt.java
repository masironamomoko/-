package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.News;
import com.compe.competition_demo1.cdata.announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class NewsServicelmpt implements NewsService{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int NewsTotalDate(String date) {
        String sql="SELECT count(*) FROM news WHERE ( datediff ( date , '"+date+"' ) = 0 )";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public int NewsTotalKey(String key) {
        String sql="SELECT count(*) FROM news where title like '%"+key+"%'";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public int addNews(News news){
        String sql="insert into news(news_id,date,essay,title,author,news_check) values(null,?,?,?,?,0)";
        jdbcTemplate.update(sql,news.getDate(),news.getEssay(),news.getTitle(),news.getAuthor());
        sql="select count(*) from news where title='"+news.getTitle()+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=1)
            return 0;
        return 1;//成功add时，返回1，jsonresult接收到数据会返回666，否则未接受到数据返回700
    }

    @Override
    public int deleteNews(String news_id) {
        String sql="delete from news where news_id=?";
        jdbcTemplate.update(sql,news_id);
        sql="select count(*) from news where news_id='"+news_id+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=0)
            return 0;
        return 1;
    }

    @Override
    public int updateNews(News news) {
        String sql="update news set title=?, essay=?, date=? where news_id=?";
        jdbcTemplate.update(sql,news.getTitle(),news.getEssay(),news.getDate(),news.getNews_id());
        sql="select count(*) from news where news_id='"+news.getNews_id()+"' and title='"+news.getTitle()+"' and essay='"+news.getEssay()+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=1)
            return 0;
        return 1;
    }

    @Override
    public List<News> NewsFindAll(int pageNum, int pageSize) throws SQLException {
        String sql="select * from news order by news_id desc limit ?,?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<News>(News.class),(pageNum-1)*pageSize,pageNum*pageSize);
    }

    @Override
    public List<News> NewsInquire() throws SQLException {
        String sql="SELECT news_id,date,title FROM news order by news_id desc limit 0,5";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<News>(News.class));
    }

    @Override
    public List<News> NewsKeySearch(int pageNum, int pageSize, String key) throws SQLException {
        String sql="select news_id,date,author,title from news where title like '%"+key+"%' order by news_id desc limit ?,?";
        NewsTotalKey(key);
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<News>(News.class),(pageNum-1)*pageSize,pageNum*pageSize);
    }

    @Override
    public List<News> NewsDateSearch(int pageNum, int pageSize, String date) throws SQLException{
        String sql="SELECT news_id,date,author,title FROM news WHERE ( datediff ( date , '"+date+"' ) = 0 ) order by news_id desc limit ?,?";
        NewsTotalDate(date);
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<News>(News.class),(pageNum-1)*pageSize,pageNum*pageSize);
    }

    @Override
    public News NewsIdSearch(String id) throws SQLException {
        String sql="select news_id,date,author,title from news where news_id='"+id+"'";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<News>(News.class));
    }

    @Override
    public List<News> NewsNoPassSearch(String user_id) throws SQLException{
        String sql="select news_id,date,author,title from news where news_check=0 and author =?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<News>(News.class),user_id);
    }

    @Override
    public List<News> NewsPassSearch(String user_id) throws SQLException{
        String sql="select news_id,date,author,title from news where inform_check!=0 and author=?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<News>(News.class),user_id);
    }

    @Override
    public List<News> NewsConnopassSearch() throws SQLException {
        String sql="select news_id,date,author,title from news where news_check=0";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<News>(News.class));
    }

    @Override
    public List<News> NewsConpassSearch() throws SQLException {
        String sql="select news_id,date,author,title from news where news_check=!0";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<News>(News.class));
    }

    @Override
    public int NewsControl(int news_id, int check) {
        String sql="update news set news_check=? where news_id=?";
        jdbcTemplate.update(sql,check,news_id);
        sql="select count(*) from news where news_check='"+check+"' and news_id='"+news_id+"'";
        int success=jdbcTemplate.queryForObject(sql,Integer.class);
        if(success!=1)
            return 0;
        return 1;
    }
}
