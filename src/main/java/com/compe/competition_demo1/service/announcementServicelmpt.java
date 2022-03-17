package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class announcementServicelmpt implements announcementService{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int informTotalDate(String date) {
        String sql="SELECT count(*) FROM announcement WHERE ( datediff ( date , '"+date+"' ) = 0 )";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public int informTotalKey(String key) {
        String sql="SELECT count(*) FROM announcement where title like '%"+key+"%'";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public int addAnnounce(announcement ann){
        String sql="insert into announcement(inform_id,date,essay,title,author,inform_check) values(null,?,?,?,?,0)";
        jdbcTemplate.update(sql,ann.getDate(),ann.getEssay(),ann.getTitle(),ann.getAuthor());
        sql="select count(*) from announcement where title='"+ann.getTitle()+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=1)
            return 0;
        return 1;//成功add时，返回1，jsonresult接收到数据会返回666，否则未接受到数据返回700
    }

    @Override
    public int deleteAnnounce(String inform_id) {
        String sql="delete from announcement where inform_id=?";
        jdbcTemplate.update(sql,inform_id);
        sql="select count(*) from announcement where inform_id='"+inform_id+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=0)
            return 0;
        return 1;
    }

    @Override
    public int updateAnnounce(announcement ann) {
        String sql="update announcement set title=?, essay=?, date=? where inform_id=?";
        jdbcTemplate.update(sql,ann.getTitle(),ann.getEssay(),ann.getDate(),ann.getInform_id());
        sql="select count(*) from announcement where inform_id='"+ann.getInform_id()+"' and title='"+ann.getTitle()+"' and essay='"+ann.getEssay()+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=1)
            return 0;
        return 1;
    }

    @Override
    public List<announcement> announceFindAll(int pageNum, int pageSize) throws SQLException {
        String sql="select * from announcement order by inform_id desc limit ?,?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<announcement>(announcement.class),(pageNum-1)*pageSize,pageNum*pageSize);
    }

    @Override
    public List<announcement> informInquire() throws SQLException {
        String sql="SELECT inform_id,date,title FROM announcement order by inform_id desc limit 0,5";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<announcement>(announcement.class));
    }

    @Override
    public List<announcement> informKeySearch(int pageNum, int pageSize, String key) throws SQLException {
        String sql="select inform_id,date,author,title from announcement where title like '%"+key+"%' order by inform_id desc limit ?,?";
        informTotalKey(key);
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<announcement>(announcement.class),(pageNum-1)*pageSize,pageNum*pageSize);
    }

    @Override
    public List<announcement> informDateSearch(int pageNum, int pageSize, String date) throws SQLException{
        String sql="SELECT inform_id,date,author,title FROM announcement WHERE ( datediff ( date , '"+date+"' ) = 0 ) order by inform_id desc limit ?,?";
        informTotalDate(date);
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<announcement>(announcement.class),(pageNum-1)*pageSize,pageNum*pageSize);
    }

    @Override
    public announcement informIdSearch(String id) throws SQLException {
        String sql="select inform_id,date,author,title from announcement where inform_id='"+id+"'";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<announcement>(announcement.class));
    }

    @Override
    public List<announcement> InformNoPassSearch(String user_id) throws SQLException{
        String sql="select inform_id,date,author,title from announcement where inform_check=0 and author =?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<announcement>(announcement.class),user_id);
    }

    @Override
    public List<announcement> InformPassSearch(String user_id) throws SQLException{
        String sql="select inform_id,date,author,title from announcement where inform_check!=0 and author=?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<announcement>(announcement.class),user_id);
    }

    @Override
    public List<announcement> InformConnopassSearch() throws SQLException {
        String sql="select inform_id,date,author,title from announcement where inform_check=0";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<announcement>(announcement.class));
    }

    @Override
    public List<announcement> InformConpassSearch() throws SQLException {
        String sql="select inform_id,date,author,title from announcement where inform_check=!0";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<announcement>(announcement.class));
    }

    @Override
    public int InformControl(int inform_id, int check) {
        String sql="update announcement set inform_check=? where inform_id=?";
        jdbcTemplate.update(sql,check,inform_id);
        sql="select count(*) from announcement where inform_check='"+check+"' and inform_id='"+inform_id+"'";
        int success=jdbcTemplate.queryForObject(sql,Integer.class);
        if(success!=1)
            return 0;
        return 1;
    }
}
