package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.*;
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
    public int addNews(news_add_in news){
        String sql="insert into news(news_id,date,essay,title,author,news_check) values(null,null,?,?,?,0)";
        jdbcTemplate.update(sql,news.getEssay(),news.getTitle(),news.getAuthor());
        sql="select count(*) from news where title='"+news.getTitle()+"' and author='"+news.getAuthor()+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=1)
            return 700;
        return 666;//成功add时，返回1，jsonresult接收到数据会返回666，否则未接受到数据返回700
    }

    @Override
    public int deleteNews(String news_id) {
        String sql="delete from news where news_id=?";
        jdbcTemplate.update(sql,news_id);
        sql="select count(*) from news where news_id='"+news_id+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=0)
            return 700;
        return 666;
    }

    @Override
    public int updateNews(news_update_in news) {
        String sql="update news set title=?, essay=? where news_id=?";
        jdbcTemplate.update(sql,news.getTitle(),news.getEssay(),news.getNews_id());
        sql="select count(*) from news where news_id='"+news.getNews_id()+"' and title='"+news.getTitle()+"' and essay='"+news.getEssay()+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=1)
            return 700;
        return 666;
    }

    @Override
    public news_list_out NewsFindAll(int pageNum, int pageSize) throws SQLException {
        String sql="select * from news order by news_id desc limit ?,?";
        news_list_out newsOut=new news_list_out();
        newsOut.setNewsList(jdbcTemplate.query(sql,new BeanPropertyRowMapper<News>(News.class),(pageNum-1)*pageSize,pageNum*pageSize));
        sql="select count(*) from news";
        newsOut.setTotal(jdbcTemplate.queryForObject(sql,Integer.class));
        return newsOut;
    }

    @Override
    public List<News> NewsInquire() throws SQLException {
        String sql="SELECT news_id,date,title FROM news order by news_id desc limit 0,5";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<News>(News.class));
    }

    @Override
    public news_list_out NewsKeySearch(news_key_in news) throws SQLException {
        String sql="select news_id,date,author,title from news where title like '%"+news.getKey()+"%' order by news_id desc limit ?,?";
        news_list_out newskOut=new news_list_out();
        newskOut.setNewsList(jdbcTemplate.query(sql,new BeanPropertyRowMapper<News>(News.class),(news.getPageNum()-1)*news.getPageSize(),news.getPageNum()*news.getPageSize()));
        sql="SELECT count(*) FROM news where title like '%"+news.getKey()+"%'";
        newskOut.setTotal(jdbcTemplate.queryForObject(sql,Integer.class));
        return newskOut;
    }

    @Override
    public news_list_out NewsDateSearch(news_date_in news) throws SQLException{
        String sql="SELECT news_id,date,author,title FROM news WHERE ( datediff ( date , '"+news.getDate()+"' ) = 0 ) order by news_id desc limit ?,?";
        news_list_out newsdOut=new news_list_out();
        newsdOut.setNewsList(jdbcTemplate.query(sql,new BeanPropertyRowMapper<News>(News.class),(news.getPageNum()-1)*news.getPageSize(),news.getPageNum()*news.getPageSize()));
        sql="SELECT count(*) FROM news WHERE ( datediff ( date , '"+news.getDate()+"' ) = 0 )";
        newsdOut.setTotal(jdbcTemplate.queryForObject(sql,Integer.class));
        return newsdOut;
    }

    @Override
    public news_id_out NewsIdSearch(String id) throws SQLException {
        String sql="select news_id,date,author,title from news where news_id='"+id+"'";
        news_id_out newsIdOut=new news_id_out();
        newsIdOut.setNews(jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<News>(News.class)));
        if(newsIdOut.getNews()!=null)
            newsIdOut.setCode(666);
        else newsIdOut.setCode(700);
        return newsIdOut;
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
            return 700;
        return 666;
    }
}
