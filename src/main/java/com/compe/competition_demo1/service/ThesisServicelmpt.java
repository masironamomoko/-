package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.thesis_io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

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

    @Override
    public String essay(Integer id) {
        String sql="select thesis_essay from thesis where thesis_id="+id+"";
        String path=jdbcTemplate.queryForObject(sql,String.class);
        String base=getBaseImg(path);
        return base;
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
    //学生的未审核论文
    @Override
    public List<thesis_stunopass_out> thestunopass(Integer user_id) {
        List<thesis_stunopass_out> thno;
        String sql2 = "select thesis_id,cate_name,com_num,thesis_name from (thesis t left join competition c on t.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id where t.user_id = "+user_id+" and t.thesis_check =0";
        thno = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<thesis_stunopass_out>(thesis_stunopass_out.class));
        return thno;
    }
    //审核成功
    @Override
    public List<thesis_stupass_out> thestupass(Integer user_id) {
        List<thesis_stupass_out> th;
        String sql2 = "select thesis_id,cate_name,com_num,thesis_name,thesis_check from (thesis t left join competition c on t.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id where t.user_id = "+user_id+" and t.thesis_check =1";
        th = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<thesis_stupass_out>(thesis_stupass_out.class));
        return th;
    }
    //审核失败
    @Override
    public List<thesis_stupass_out> thestunonopass(Integer user_id) {
        List<thesis_stupass_out> th;
        String sql2 = "select thesis_id,cate_name,com_num,thesis_name,thesis_check from (thesis t left join competition c on t.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id where t.user_id = "+user_id+" and t.thesis_check =2";
        th = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<thesis_stupass_out>(thesis_stupass_out.class));
        return th;
    }

    //竞赛负责人的未审核论文
    @Override
    public List<thesis_mannopass_out> themannopass(Integer user_id) {
        List<thesis_mannopass_out> thno;
        String sql2 = "select thesis_id,user_name,user_num,user_phone,cate_name,com_num,thesis_name from ((thesis t left join user u on t.user_id = u.user_id) left join competition c on t.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id where c.com_manager = "+user_id+" and t.thesis_check =0";
        thno = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<thesis_mannopass_out>(thesis_mannopass_out.class));
        return thno;
    }

    @Override
    public List<thesis_manpass_out> themanpass(Integer user_id) {
        List<thesis_manpass_out>th;
        String sql2 = "select thesis_id,user_name,user_num,user_phone,cate_name,com_num,thesis_name,thesis_check from ((thesis t left join user u on t.user_id = u.user_id) left join competition c on t.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id where c.com_manager = "+user_id+" and t.thesis_check =1";
        th = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<thesis_manpass_out>(thesis_manpass_out.class));
        return th;
    }

    //项目管理员的未审核论文
    @Override
    public List<thesis_mannopass_out> theconnopass() {
        List<thesis_mannopass_out> thno;
        String sql2 = "select thesis_id,user_name,user_num,user_phone,cate_name,com_num,thesis_name from ((thesis t left join user u on t.user_id = u.user_id) left join competition c on t.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id where t.thesis_check =0";
        thno = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<thesis_mannopass_out>(thesis_mannopass_out.class));
        return thno;
    }

    @Override
    public List<thesis_manpass_out> theconpass() {
        List<thesis_manpass_out> th;
        String sql2 = "select thesis_id,user_name,user_num,user_phone,cate_name,com_num,thesis_name,thesis_check from ((thesis t left join user u on t.user_id = u.user_id) left join competition c on t.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id where t.thesis_check =1";
        th = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<thesis_manpass_out>(thesis_manpass_out.class));
        return th;
    }

    //审核论文信息
    @Override
    public int thecheck(thesis_check_in thesis_check_in) {
        String sql1 = "update thesis set thesis_check = ? where thesis_id = ?";
        jdbcTemplate.update(sql1,thesis_check_in.getThesis_check(),thesis_check_in.getThesis_id());
        String sql2 = "select count(*) from thesis where thesis_check ="+thesis_check_in.getThesis_check()+" and thesis_id ="+thesis_check_in.getThesis_id()+"";
        int count=jdbcTemplate.queryForObject(sql2,Integer.class);
        if(count!=1)
            return 700;
        return 666;
    }

    @Override
    public int StuCount(Integer user_id) {
        int count;
        String sql="select count(*) from thesis where user_id="+user_id+" and thesis_check=0";
        count=jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }
    @Override
    public int StunoCount(Integer user_id) {
        int count;
        String sql="select count(*) from thesis where user_id="+user_id+" and thesis_check=2";
        count=jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }

    @Override
    public int ManCount(Integer user_id) {
        String sql = "select count(*) from ((thesis a left join user u on a.user_id = u.user_id) left join competition c on c.com_id = a.com_id) left join category ca on c.cate_id = ca.cate_id where com_manager = "+user_id+" and a.thesis_check =0";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }

    @Override
    public int ConCount() {
        String sql="select count(*) from thesis where thesis_check=0";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }
}
