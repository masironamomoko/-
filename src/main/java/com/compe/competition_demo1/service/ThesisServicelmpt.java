package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.thesis_io.thesis_add_in;
import com.compe.competition_demo1.cdata.thesis_io.thesis_idsearch_out;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Service
public class ThesisServicelmpt implements ThesisService{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int AddThesis(thesis_add_in thesis) {
        String sql="select cate_id from category where cate_name='"+thesis.getCate_name()+"'";
        Integer cate_id=jdbcTemplate.queryForObject(sql,Integer.class);
        sql="select count(*) from competition where cate_id="+cate_id+" and com_num='"+thesis.getCom_num()+"'";
        int count1=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count1!=1)
            return 702;
        String format = thesis.getCate_name()+thesis.getCom_num()+ thesis.getUser_id();
        // File folder = new File(realPath + format);
        File folder=new File("D:/图片/thesis");
        if (!folder.isDirectory())
        {
            if (!folder.mkdirs())
            {
                return 700;
            }
        }
        String oldName = thesis.getThesis_essay().getOriginalFilename();
        assert oldName != null;
        String newName = format + oldName.substring(oldName.lastIndexOf("."));
        try
        {
            thesis.getThesis_essay().transferTo(new File(folder, newName));
            //以上都是普通代码, 这里的/files/ 对应的就是你在WebMvcConfig设置的地址映射
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        String path="D:/图片/thesis/"+newName;
        sql="select count(*) from thesis where thesis_essay='"+path+"'";
        int n=jdbcTemplate.queryForObject(sql,Integer.class);
        if(n!=0)
            return 701;
        sql="select com_id from competition where cate_id='"+cate_id+"' and com_num='"+thesis.getCom_num()+"'";
        Integer com_id=jdbcTemplate.queryForObject(sql,Integer.class);
        sql="insert into thesis(thesis_id,user_id,thesis_check,thesis_essay,com_id,thesis_name) values(null,?,0,?,?,?)";
        jdbcTemplate.update(sql,thesis.getUser_id(),path,com_id,thesis.getThesis_name());
        sql="select count(*) from thesis where user_id='"+thesis.getUser_id()+"' and thesis_essay='"+path+"' and com_id='"+com_id+"' and thesis_name='"+thesis.getThesis_name()+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count==1)
            return 666;
        return 700;
    }

    @Override
    public int DeleteThesis(Integer id) {
        String sql="select thesis_essay from thesis where thesis_id='"+id+"'";
        String path=jdbcTemplate.queryForObject(sql,String.class);
        File file=new File(path);
        boolean OK=file.exists();
        if(OK)
            file.delete();
        sql="delete from thesis where thesis_id=?";
        jdbcTemplate.update(sql,id);
        sql="select count(*) from thesis where thesis_id='"+id+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=0)
            return 700;
        return 666;
    }

    @Override
    public thesis_idsearch_out IdSearch(Integer id) {
        thesis_idsearch_out thesisIdsearchOut = new thesis_idsearch_out();
        String sql="select cate_name from category where cate_id=(select cate_id from competition where com_id=(select com_id from thesis where thesis_id="+id+"))";
        thesisIdsearchOut.setCate_name(jdbcTemplate.queryForObject(sql,String.class));
        sql="select com_num from competition where com_id=(select com_id from thesis where thesis_id="+id+")";
        thesisIdsearchOut.setCom_num(jdbcTemplate.queryForObject(sql,String.class));
        sql="select user_name from user where user_id=(select user_id from thesis where thesis_id="+id+")";
        thesisIdsearchOut.setUser_name(jdbcTemplate.queryForObject(sql,String.class));
        sql="select user_num from user where user_id=(select user_id from thesis where thesis_id="+id+")";
        thesisIdsearchOut.setUser_num(jdbcTemplate.queryForObject(sql,String.class));
        sql="select user_phone from user where user_id=(select user_id from thesis where thesis_id="+id+")";
        thesisIdsearchOut.setUser_phone(jdbcTemplate.queryForObject(sql,String.class));
        sql="select thesis_name from thesis where thesis_id="+id+"";
        thesisIdsearchOut.setThesis_name(jdbcTemplate.queryForObject(sql,String.class));
        sql="select thesis_essay from thesis where thesis_id="+id+"";
        String path=jdbcTemplate.queryForObject(sql,String.class);
        String base=getBaseImg(path);
        thesisIdsearchOut.setThesis_essay(base);
        return thesisIdsearchOut;
    }
    /**
     * 将图片base64转码
     *
     * @param imgPath 图片路径
     * @return base64编码
     */
    public String getBaseImg(String imgPath) {
        InputStream in;
        byte[] data = null;
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //进行Base64编码
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(data);
    }
}
