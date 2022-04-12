package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.*;
import com.compe.competition_demo1.cdata.inform_io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class announcementServicelmpt implements announcementService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addAnnounce(inform_add_in ann){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        formatter.format(date);
        String sql="insert into announcement(inform_id,date,essay,title,author,inform_check) values(null,?,?,?,?,0)";
        jdbcTemplate.update(sql,date,ann.getEssay(),ann.getTitle(),ann.getAuthor());
        sql="select count(*) from announcement where title='"+ann.getTitle()+"' and author='"+ann.getAuthor()+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=1)
            return 700;
        return 666;//成功add时，返回666，否则未接受到数据返回700
    }

    @Override
    public int deleteAnnounce(Integer inform_id) {
        String sql="delete from announcement where inform_id=?";
        jdbcTemplate.update(sql,inform_id);
        sql="select count(*) from announcement where inform_id='"+inform_id+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=0)
            return 700;
        return 666;
    }

    @Override
    public int updateAnnounce(inform_update_in ann) {
        String sql="update announcement set title=?, essay=? where inform_id=?";
        jdbcTemplate.update(sql,ann.getTitle(),ann.getEssay(),ann.getInform_id());
        sql="select count(*) from announcement where inform_id='"+ann.getInform_id()+"' and title='"+ann.getTitle()+"' and essay='"+ann.getEssay()+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=1)
            return 700;
        return 666;
    }

    @Override
    public inform_list_out announceFindAll(inform_findall_in inform) throws SQLException {
        String sql="select * from announcement order by inform_id desc limit "+(inform.getPageNum()-1)*inform.getPageSize()+","+inform.getPageSize()+"";
        inform_list_out informListOut=new inform_list_out();
        informListOut.setAnnouncementList(jdbcTemplate.query(sql,new BeanPropertyRowMapper<announcement>(announcement.class)));
        sql="select count(*) from announcement";
        informListOut.setTotal(jdbcTemplate.queryForObject(sql,Integer.class));
        return informListOut;
    }

    @Override
    public List<announcement> informInquire() throws SQLException {
        String sql="SELECT inform_id,date,title FROM announcement order by inform_id desc limit 0,5";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<announcement>(announcement.class));
    }

    @Override
    public inform_list_out informKeySearch(inform_key_in informKeyIn) throws SQLException {
        String sql="select inform_id,date,author,title from announcement where title like '%"+informKeyIn.getKey()+"%' order by inform_id desc limit "+(informKeyIn.getPageNum()-1)*informKeyIn.getPageSize()+","+informKeyIn.getPageSize()+"";
        inform_list_out informkOut=new inform_list_out();
        informkOut.setAnnouncementList(jdbcTemplate.query(sql,new BeanPropertyRowMapper<announcement>(announcement.class)));
        sql="select count(*) from announcement where title like '%"+informKeyIn.getKey()+"%'";
        informkOut.setTotal(jdbcTemplate.queryForObject(sql,Integer.class));
        return informkOut;
    }

    @Override
    public inform_list_out informDateSearch(inform_date_in informDateIn) throws SQLException{
        String sql="SELECT inform_id,date,author,title FROM announcement WHERE ( datediff ( date , '"+informDateIn.getDate()+"' ) = 0 ) order by inform_id desc limit "+(informDateIn.getPageNum()-1)*informDateIn.getPageSize()+","+informDateIn.getPageSize()+"";
        inform_list_out informdOut=new inform_list_out();
        informdOut.setAnnouncementList(jdbcTemplate.query(sql,new BeanPropertyRowMapper<announcement>(announcement.class)));
        sql="SELECT count(*) FROM announcement WHERE ( datediff ( date , '"+informDateIn.getDate()+"' ) = 0 )";
        informdOut.setTotal(jdbcTemplate.queryForObject(sql,Integer.class));
        return informdOut;
    }

    @Override
    public inform_id_out informIdSearch(Integer id) throws SQLException {
        String sql="select date,title,author,essay from announcement where inform_id='"+id+"'";
        inform_id_out informIdOut=new inform_id_out();
        informIdOut.setAnn(jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<announcement>(announcement.class)));
        if(informIdOut.getAnn()!=null)
            informIdOut.setCode(666);
        else informIdOut.setCode(700);
        return informIdOut;
    }

    @Override
    public List<announcement> InformNoPassSearch(Integer user_id) throws SQLException{
        String sql="select inform_id,date,author,title from announcement where inform_check=0 and author =?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<announcement>(announcement.class),user_id);
    }

    @Override
    public List<announcement> InformPassSearch(Integer user_id) throws SQLException{
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
        String sql="select inform_id,date,author,title,inform_check from announcement where inform_check!=0";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<announcement>(announcement.class));
    }

    @Override
    public int InformControl(inform_check_in informCheckIn) {
        String sql="update announcement set inform_check=? where inform_id=?";
        jdbcTemplate.update(sql,informCheckIn.getCheck(),informCheckIn.getInform_id());
        sql="select count(*) from announcement where inform_check='"+informCheckIn.getCheck()+"' and inform_id='"+informCheckIn.getInform_id()+"'";
        int success=jdbcTemplate.queryForObject(sql,Integer.class);
        if(success!=1)
            return 700;
        return 666;
    }
}
