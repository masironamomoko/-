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
public class announcementServicelmpt implements announcementService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addAnnounce(announcement ann){
        String sql="insert into announcement(announcement_id,date,announcement_essay,title) values(null,?,?,?)";
        jdbcTemplate.update(sql,ann.getDate(),ann.getAnnouncement_essay(),ann.getTitle());
    }

    @Override
    public void deleteAnnounce(int announcement_id) {
        String sql="delete from announcement where announcement_id=?";
        jdbcTemplate.update(sql,announcement_id);
    }

    @Override
    public void updateAnnounce(announcement ann) {
        String sql="update announcement set title=?, announcement_essay=?, date=? where announcement_id=?";
        jdbcTemplate.update(sql,ann.getTitle(),ann.getAnnouncement_essay(),ann.getDate(),ann.getAnnouncement_id());
    }

    @Override
    public List<announcement> announceFindAll() throws SQLException {
        String sql="select * from announcement";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<announcement>(announcement.class));
    }


}
