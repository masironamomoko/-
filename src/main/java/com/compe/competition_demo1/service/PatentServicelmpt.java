package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.patent_io.*;
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
public class PatentServicelmpt implements PatentService{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int AddPatent(patent_add_in patent) {
        String sql="select cate_id from category where cate_name='"+patent.getCate_name()+"'";
        Integer cate_id=jdbcTemplate.queryForObject(sql,Integer.class);
        sql="select count(*) from competition where cate_id="+cate_id+" and com_num='"+patent.getCom_num()+"'";
        int count1=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count1!=1)
            return 702;
        String format = patent.getCate_name()+patent.getCom_num()+ patent.getUser_id();
        // File folder = new File(realPath + format);
        File folder=new File("D:/图片/patent");
        if (!folder.isDirectory())
        {
            if (!folder.mkdirs())
            {
                return 700;
            }
        }
        String oldName = patent.getPatent_prove().getOriginalFilename();
        assert oldName != null;
        String newName = format + oldName.substring(oldName.lastIndexOf("."));
        try
        {
            patent.getPatent_prove().transferTo(new File(folder, newName));
            //以上都是普通代码, 这里的/files/ 对应的就是你在WebMvcConfig设置的地址映射
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        String path="D:/图片/patent/"+newName;
        sql="select count(*) from patent where patent_prove='"+path+"'";
        int n=jdbcTemplate.queryForObject(sql,Integer.class);
        if(n!=0)
            return 701;
        sql="select com_id from competition where cate_id='"+cate_id+"' and com_num='"+patent.getCom_num()+"'";
        Integer com_id=jdbcTemplate.queryForObject(sql,Integer.class);
        sql="insert into patent(patent_id,user_id,patent_check,patent_prove,com_id,patent_name) values(null,?,0,?,?,?)";
        jdbcTemplate.update(sql,patent.getUser_id(),path,com_id,patent.getPatent_name());
        sql="select count(*) from patent where user_id='"+patent.getUser_id()+"' and patent_prove='"+path+"' and com_id='"+com_id+"' and patent_name='"+patent.getPatent_name()+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count==1)
            return 666;
        return 700;
    }

    @Override
    public int DeletePatent(Integer id) {
        String sql="select patent_prove from patent where patent_id='"+id+"'";
        String path=jdbcTemplate.queryForObject(sql,String.class);
        File file=new File(path);
        boolean OK=file.exists();
        if(OK)
            file.delete();
        sql="delete from patent where patent_id=?";
        jdbcTemplate.update(sql,id);
        sql="select count(*) from patent where patent_id='"+id+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=0)
            return 700;
        return 666;
    }

    @Override
    public patent_idsearch_out IdSearch(Integer id) {
        patent_idsearch_out patentIdsearchOut = new patent_idsearch_out();
        String sql="select cate_name from category where cate_id=(select cate_id from competition where com_id=(select com_id from patent where patent_id="+id+"))";
        patentIdsearchOut.setCate_name(jdbcTemplate.queryForObject(sql,String.class));
        sql="select com_num from competition where com_id=(select com_id from patent where patent_id="+id+")";
        patentIdsearchOut.setCom_num(jdbcTemplate.queryForObject(sql,String.class));
        sql="select user_name from user where user_id=(select user_id from patent where patent_id="+id+")";
        patentIdsearchOut.setUser_name(jdbcTemplate.queryForObject(sql,String.class));
        sql="select user_num from user where user_id=(select user_id from patent where patent_id="+id+")";
        patentIdsearchOut.setUser_num(jdbcTemplate.queryForObject(sql,String.class));
        sql="select user_phone from user where user_id=(select user_id from patent where patent_id="+id+")";
        patentIdsearchOut.setUser_phone(jdbcTemplate.queryForObject(sql,String.class));
        sql="select patent_name from patent where patent_id="+id+"";
        patentIdsearchOut.setPatent_name(jdbcTemplate.queryForObject(sql,String.class));
        sql="select patent_prove from patent where patent_id="+id+"";
        String path=jdbcTemplate.queryForObject(sql,String.class);
        String base=getBaseImg(path);
        patentIdsearchOut.setPatent_prove(base);
        return patentIdsearchOut;
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
    public List<patent_stunopass_out> pastunopass(Integer user_id) {
        List<patent_stunopass_out> pano;
        String sql2 = "select patent_id,cate_name,com_num,patent_essay,patent_name from (patent p left join competition c on p.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id where p.user_id = "+user_id+" and p.patent_check =0";
        pano = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<patent_stunopass_out>(patent_stunopass_out.class));
        return pano;
    }
    //审核成功
    @Override
    public List<patent_stupass_out> pastupass(Integer user_id) {
        List<patent_stupass_out> pa;
        String sql2 = "select patent_id,cate_name,com_num,patent_name,patent_check from (patent p left join competition c on t.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id where p.user_id = "+user_id+" and p.patent_check =1";
        pa = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<patent_stupass_out>(patent_stupass_out.class));
        return pa;
    }
    //审核失败
    @Override
    public List<patent_stupass_out> pastunonopass(Integer user_id) {
        List<patent_stupass_out> pa;
        String sql2 = "select patent_id,cate_name,com_num,patent_name,patent_check from (patent p left join competition c on p.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id where p.user_id = "+user_id+" and p.patent_check =2";
        pa = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<patent_stupass_out>(patent_stupass_out.class));
        return pa;
    }

    //竞赛负责人的未审核论文
    @Override
    public List<patent_mannopass_out> pamannopass(Integer user_id) {
        List<patent_mannopass_out> pano;
        String sql2 = "select patent_id,user_name,user_num,user_phone,cate_name,com_num,patent_essay,patent_name from ((patent p left join user u on p.user_id = u.user_id) left join competition c on p.com_id = p.com_id) left join category ca on c.cate_id = ca.cate_id where c.com_manager = "+user_id+" and p.patent_check !=1";
        pano = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<patent_mannopass_out>(patent_mannopass_out.class));
        return pano;
    }

    @Override
    public List<patent_manpass_out> pamanpass(Integer user_id) {
        List<patent_manpass_out> pa;
        String sql2 = "select patent_id,user_name,user_num,user_phone,cate_name,com_num,patent_essay,patent_name,patent_check from ((patent p left join user u on p.user_id = u.user_id) left join competition c on p.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id where c.com_manager = "+user_id+" and p.patent_check =1";
        pa = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<patent_manpass_out>(patent_manpass_out.class));
        return pa;
    }

    //项目管理员的未审核论文
    @Override
    public List<patent_mannopass_out> paconnopass() {
        List<patent_mannopass_out> pano;
        String sql2 = "select patent_id,user_name,user_num,user_phone,cate_name,com_num,patent_essay,patent_name from ((patent p left join user u on p.user_id = u.user_id) left join competition c on p.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id where p.patent_check !=1";
        pano = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<patent_mannopass_out>(patent_mannopass_out.class));
        return pano;
    }

    @Override
    public List<patent_manpass_out> paconpass() {
        List<patent_manpass_out> pa;
        String sql2 = "select patent_id,user_name,user_num,user_phone,cate_name,com_num,patent_essay,patent_name,patent_check from ((patent p left join user u on p.user_id = u.user_id) left join competition c on p.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id where p.patent_check =1";
        pa = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<patent_manpass_out>(patent_manpass_out.class));
        return pa;
    }

    //审核论文信息
    @Override
    public int pacheck(patent_check_in patent_check_in) {
        String sql1 = "update patent set patent_check = ? where patent_id = ?";
        jdbcTemplate.update(sql1,patent_check_in.getPatent_check(),patent_check_in.getPatent_id());
        String sql2 = "select count(*) from patent where patent_check ="+patent_check_in.getPatent_check()+" and patent_id ="+patent_check_in.getPatent_id()+"";
        int count=jdbcTemplate.queryForObject(sql2,Integer.class);
        if(count!=1)
            return 700;
        return 666;
    }

    @Override
    public int StuCount(Integer user_id) {
        int count;
        String sql="select count(*) from patent where user_id="+user_id+" and check=0";
        count=jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }
    @Override
    public int StunoCount(Integer user_id) {
        int count;
        String sql="select count(*) from patent where user_id="+user_id+" and check=2";
        count=jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }

    @Override
    public int ManCount(Integer user_id) {
        String sql = "select count(*) from ((patent a left join user u on a.user_id = u.user_id) left join competition c on c.com_id = a.com_id) left join category ca on c.cate_id = ca.cate_id where com_manager = "+user_id+" and a.patent_check =0";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }

    @Override
    public int ConCount() {
        String sql="select count(*) from patent where patent_check=0";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }
}
