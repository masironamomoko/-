package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.patent_io.patent_add_in;
import com.compe.competition_demo1.cdata.patent_io.patent_idsearch_out;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

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
}
