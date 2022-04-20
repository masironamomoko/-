package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.cate_io.data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CateServicelmpt implements CateService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int AddCategory(String cate_name) {
        String sql="insert into category(cate_id,cate_name) values(null,'"+cate_name+"')";
        jdbcTemplate.update(sql);
        int code;
        sql="select count(*) from category where cate_name='"+cate_name+"'";
        if(jdbcTemplate.queryForObject(sql,Integer.class)==1)
            code=666;
        else code=700;
        return code;
    }

    @Override
    public List<data> FindAll() {
        String sql="select * from category";
        List<data> data;
        data=jdbcTemplate.query(sql,new BeanPropertyRowMapper<data>(data.class));
        return data;
    }
}
