package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.cate_io.data;
import com.compe.competition_demo1.cdata.cate_io.recate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
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
    public List<String> FindAll() {
        String sql="select cate_name from category";
        List<data> data;
        data=jdbcTemplate.query(sql,new BeanPropertyRowMapper<data>(data.class));
        List<String> datas=new LinkedList<>();
        for(int i=0;i<data.size();i++){
            datas.add(data.get(i).getCate_name());
        }
        return datas;
    }

    @Override
    public List<recate> ReFindall() {
        String sql="select cate_name from category";
        List<data> data;
        data=jdbcTemplate.query(sql,new BeanPropertyRowMapper<data>(data.class));
        List<recate> cates=new LinkedList<>();
        for(int i=0;i<data.size();i++){
            recate cate=new recate();
            cate.setText(data.get(i).getCate_name());
            cate.setValue(data.get(i).getCate_name());
            cates.add(cate);
        }
        return cates;
    }
}
