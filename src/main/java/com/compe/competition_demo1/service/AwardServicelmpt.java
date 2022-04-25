package com.compe.competition_demo1.service;


import com.compe.competition_demo1.cdata.Award;
import com.compe.competition_demo1.cdata.award_io.*;
import com.compe.competition_demo1.cdata.award_io.award_all.*;
import com.compe.competition_demo1.cdata.award_io.award_category.Cate;
import com.compe.competition_demo1.cdata.award_io.award_category.award_category_in;
import com.compe.competition_demo1.cdata.award_io.award_category.award_category_out;
import com.compe.competition_demo1.cdata.cate_io.data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

import java.io.*;

@Service
public class AwardServicelmpt implements AwardService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //届数获奖分析
    @Override
    public award_category_out awardcate(award_category_in award_category_in){
        award_category_out cate_out=new award_category_out();
        Cate cate = new Cate();
        String sql2,sql3,sql4;
        List<data1> data1;
        String sql1 = "select c.com_num from competition c where c.cate_id in (select ca.cate_id from category ca where ca.cate_name = '"+award_category_in.getCate_name()+"')";
        data1 = jdbcTemplate.query(sql1,new BeanPropertyRowMapper<data1>(data1.class));
        List<Integer> award1 = new LinkedList<>(),award2 = new LinkedList<>(),award3 = new LinkedList<>();
        List<String> datas1 = new LinkedList<>();
        for (int i=0;i<data1.size();i++){
            datas1.add(data1.get(i).getCom_num());
        }
        for(int i=0;i<datas1.size();i++) {
            sql2 = "select sum(award1) from competition where com_num = '"+datas1.get(i)+"'";
            Integer sn1 = jdbcTemplate.queryForObject(sql2,Integer.class);
            award1.add(sn1);
            sql3 = "select sum(award2) from competition where com_num = '"+datas1.get(i)+"'";
            Integer sn2 = jdbcTemplate.queryForObject(sql3,Integer.class);
            award2.add(sn2);
            sql4 = "select sum(award3) from competition where com_num = '"+datas1.get(i)+"'";
            Integer sn3 = jdbcTemplate.queryForObject(sql4,Integer.class);
            award3.add(sn3);
        }
        cate_out.setCom_mun(datas1);
        cate.setAward1(award1);
        cate.setAward2(award2);
        cate.setAward3(award3);
        cate_out.setCate(cate);
        return cate_out;
    }

    //总获奖分析
    @Override
    public award_all_out awardall(){
        //allc
        award_all_out all_out = new award_all_out();
        String sql1 = "select count(*) from category";
        Integer allc = jdbcTemplate.queryForObject(sql1,Integer.class);
        all_out.setAllc(allc);
        //cates
        String sql="select cate_name from category";
        List<data> data;
        data=jdbcTemplate.query(sql,new BeanPropertyRowMapper<data>(data.class));
        List<String> datas=new LinkedList<>();;
        for(int i=0;i<data.size();i++){
            datas.add(data.get(i).getCate_name());
        }
        all_out.setCates(datas);
        //levels
        String sql2 = "select distinct com_level from competition";
        List comlevel = jdbcTemplate.query(sql2,new BeanPropertyRowMapper<String>(String.class));
        all_out.setLevels(comlevel);
        //cate
        Cateall cateall = new Cateall();
        List<Integer> num = new LinkedList<>();
        List<String>award1 = new LinkedList<>(),award2 = new LinkedList<>(),award3 = new LinkedList<>();
        for (int i=0;i<datas.size();i++) {
            String sql3 = "select count(*) from category where cate_name = '"+datas.get(i)+"'";
            Integer num1 = jdbcTemplate.queryForObject(sql3,Integer.class);
            num.add(num1);
            String sql4 = "select sum(c.award1) from competition c where c.cate_id in (select ca.cate_id from category ca where ca.cate_name = '"+datas.get(i)+"')" ;
            String sn1 = jdbcTemplate.queryForObject(sql4,String.class);
            award1.add(sn1);
            String sql5 = "select sum(c.award2) from competition c where c.cate_id in (select ca.cate_id from category ca where ca.cate_name = '"+datas.get(i)+"')";
            String sn2 = jdbcTemplate.queryForObject(sql5,String.class);
            award2.add(sn2);
            String sql6 = "select sum(c.award3) from competition c where c.cate_id in (select ca.cate_id from category ca where ca.cate_name = '"+datas.get(i)+"')";
            String sn3 = jdbcTemplate.queryForObject(sql6,String.class);
            award3.add(sn3);
        }
        cateall.setNum(num);
        cateall.setAward1(award1);
        cateall.setAward2(award2);
        cateall.setAward3(award3);
        all_out.setCate((List<Integer>) cateall);
        //cateValue
        CateValue cateValue = new CateValue();
        for (int i=0;i<datas.size();i++) {
            cateValue.setNum(num.get(i));
            cateValue.setCate_name(datas.get(i));
        }
        all_out.setCateValue(Collections.singletonList(cateValue));
        //levelValue
        LevelValue levelValue = new LevelValue();
        for (int i=0;i<datas.size();i++) {
            String sql7 = "select count(*) from competition where com_level = '"+comlevel.get(i)+"'";
            Integer sn4 = jdbcTemplate.queryForObject(sql7,Integer.class);
            levelValue.setNum(sn4);
            levelValue.setCom_level(datas.get(i));
        }
        all_out.setLevelValue(Collections.singletonList(levelValue));
        return all_out;
    }

    @Override
    public int AddAward(award_add_in awardAddIn) {
        String sql="select cate_id from category where cate_name='"+awardAddIn.getCate_name()+"'";
        Integer cate_id=jdbcTemplate.queryForObject(sql,Integer.class);
        sql="select count(*) from competition where cate_id="+cate_id+" and com_num='"+awardAddIn.getCom_num()+"'";
        int count1=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count1==0)
            return 702;
        String format = awardAddIn.getCate_name()+awardAddIn.getCom_num()+ awardAddIn.getUser_id();
        // File folder = new File(realPath + format);
        File folder=new File("D:/图片/award");
        if (!folder.isDirectory())
        {
            if (!folder.mkdirs())
            {
                return 700;
            }
        }
        String oldName = awardAddIn.getAward_prove().getOriginalFilename();
        assert oldName != null;
        String newName = format + oldName.substring(oldName.lastIndexOf("."));
        try
        {
            awardAddIn.getAward_prove().transferTo(new File(folder, newName));
            //以上都是普通代码, 这里的/files/ 对应的就是你在WebMvcConfig设置的地址映射
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        String path="D:/图片/award/"+newName;
        sql="select count(*) from award where award_prove='"+path+"'";
        int n=jdbcTemplate.queryForObject(sql,Integer.class);
        if(n!=0)
            return 701;
        sql="select com_id from competition where cate_id='"+cate_id+"' and com_num='"+awardAddIn.getCom_num()+"'";
        Integer com_id=jdbcTemplate.queryForObject(sql,Integer.class);
        sql="insert into award(award_id,user_id,award_check,award_prove,com_id,award_level) values(null,?,0,?,?,?)";
        jdbcTemplate.update(sql,awardAddIn.getUser_id(),path,com_id,awardAddIn.getAward_level());
        sql="select count(*) from award where user_id='"+awardAddIn.getUser_id()+"' and award_prove='"+path+"' and com_id='"+com_id+"' and award_level='"+awardAddIn.getAward_level()+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count==1)
            return 666;
        return 700;
    }

    @Override
    public int DeleteAward(Integer id) {
        String sql="select award_prove from award where award_id='"+id+"'";
        String path=jdbcTemplate.queryForObject(sql,String.class);
        File file=new File(path);
        boolean OK=file.exists();
        if(OK)
            file.delete();
        sql="delete from award where award_id=?";
        jdbcTemplate.update(sql,id);
        sql="select count(*) from award where award_id='"+id+"'";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        if(count!=0)
            return 700;
        return 666;
    }

    @Override
    public award_idsearch_out IdSearch(Integer id) {
        award_idsearch_out awardIdsearchOut = new award_idsearch_out();
        String sql="select cate_name from category where cate_id=(select cate_id from competition where com_id=(select com_id from award where award_id="+id+"))";
        awardIdsearchOut.setCate_name(jdbcTemplate.queryForObject(sql,String.class));
        sql="select com_num from competition where com_id=(select com_id from award where award_id="+id+")";
        awardIdsearchOut.setCom_num(jdbcTemplate.queryForObject(sql,String.class));
        sql="select user_name from user where user_id=(select user_id from award where award_id="+id+")";
        awardIdsearchOut.setUser_name(jdbcTemplate.queryForObject(sql,String.class));
        sql="select user_num from user where user_id=(select user_id from award where award_id="+id+")";
        awardIdsearchOut.setUser_num(jdbcTemplate.queryForObject(sql,String.class));
        sql="select user_phone from user where user_id=(select user_id from award where award_id="+id+")";
        awardIdsearchOut.setUser_phone(jdbcTemplate.queryForObject(sql,String.class));
        sql="select award_level from award where award_id="+id+"";
        awardIdsearchOut.setAward_level(jdbcTemplate.queryForObject(sql,String.class));
        sql="select award_prove from award where award_id="+id+"";
        String path=jdbcTemplate.queryForObject(sql,String.class);
        String base=getBaseImg(path);
        awardIdsearchOut.setAward_prove(base);
        return awardIdsearchOut;
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

    //学生的未审核获奖
    @Override
    public List<award_stunopass_out> awastunopass(Integer user_id){
        List<award_stunopass_out> awno;
        String sql2 = "select award_id,cate_name,com_num,award_level from (award a left join competition c on a.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id where a.user_id = "+user_id+" and a.award_check =0";
        awno = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<award_stunopass_out>(award_stunopass_out.class));
        return awno;
    }
    //审核成功
    @Override
    public List<award_stupass_out> awastupass(Integer user_id){
        List<award_stupass_out> aw;
        String sql2 = "select award_id,cate_name,com_num,award_level,award_check from (award a left join competition c on a.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id  where a.user_id = "+user_id+" and a.award_check =1";
        aw = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<award_stupass_out>(award_stupass_out.class));
        return aw;
    }
    //审核失败
    @Override
    public List<award_stupass_out> awastunonopass(Integer user_id){
        List<award_stupass_out> aw;
        String sql2 = "select award_id,cate_name,com_num,award_level,award_check from (award a left join competition c on a.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id  where a.user_id = "+user_id+" and a.award_check =2";
        aw = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<award_stupass_out>(award_stupass_out.class));
        return aw;
    }

    //竞赛负责人的未审核获奖
    @Override
    public List<award_mannopass_out> awamannopass(Integer user_id) {
        List<award_mannopass_out> awno;
        String sql2 = "select award_id,user_name,user_num,user_phone,cate_name,com_num,award_level from ((award a left join user u on a.user_id = u.user_id) left join competition c on a.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id where c.com_manager = "+user_id+" and a.award_check !=1";
        awno = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<award_mannopass_out>(award_mannopass_out.class));
        return awno;
    }

    @Override
    public List<award_manpass_out> awamanpass(Integer user_id) {
        List<award_manpass_out> aw;
        String sql2 = "select award_id,user_name,user_num,user_phone,cate_name,com_num,award_level,award_check from ((award a left join user u on a.user_id = u.user_id) left join competition c on a.com_id = c.com_id) left join category ca on c.cate_id = ca.cate_id where c.com_manager = "+user_id+" and a.award_check =1";
        aw = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<award_manpass_out>(award_manpass_out.class));
        return aw;
    }

    //项目管理员的未审核获奖
    @Override
    public List<award_mannopass_out> awaconnopass(){
        List<award_mannopass_out> awno;
        String sql2 = "select award_id,user_name,user_num,user_phone,cate_name,com_num,award_level from ((award a left join user u on a.user_id = u.user_id) left join competition c on c.com_id = a.com_id) left join category ca on c.cate_id = ca.cate_id where a.award_check !=1";
        awno = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<award_mannopass_out>(award_mannopass_out.class));
        return awno;
    }

    @Override
    public List<award_manpass_out> awaconpass(){
        List<award_manpass_out> aw;
        String sql2 = "select award_id,user_name,user_num,user_phone,cate_name,com_num,award_level,award_check from ((award a left join user u on a.user_id = u.user_id) left join competition c on c.com_id = a.com_id) left join category ca on c.cate_id = ca.cate_id where a.award_check =1";
        aw = jdbcTemplate.query(sql2,new BeanPropertyRowMapper<award_manpass_out>(award_manpass_out.class));
        return aw;
    }

    //审核获奖信息
    @Override
    public int awacheck(award_check_in award_check_in){
        String sql1 = "update award set award_check = ? where award_id = ?";
        jdbcTemplate.update(sql1,award_check_in.getAward_check(),award_check_in.getAward_id());
        String sql2 = "select count(*) from award where award_check = "+award_check_in.getAward_check()+" and award_id = '"+award_check_in.getAward_id()+"'";
        int count=jdbcTemplate.queryForObject(sql2,Integer.class);
        if(count!=1)
            return 700;
        return 666;
    }

    @Override
    public int StuCount(Integer user_id) {
        int count;
        String sql="select count(*) from award where user_id="+user_id+" and check=0";
        count=jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }
    @Override
    public int StunoCount(Integer user_id) {
        int count;
        String sql="select count(*) from award where user_id="+user_id+" and check=2";
        count=jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }

    @Override
    public int ManCount(Integer user_id) {
        String sql = "select count(*) from ((award a left join user u on a.user_id = u.user_id) left join competition c on c.com_id = a.com_id) left join category ca on c.cate_id = ca.cate_id where com_manager = "+user_id+" and a.award_check =0";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }

    @Override
    public int ConCount() {
        String sql="select count(*) from award where award_check=0";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }

    @Override
    public int AddExcel(Award award) {
        String sql="select user_id from user where user_num="+award.getUser_num()+"";
        Integer user_id=jdbcTemplate.queryForObject(sql,Integer.class);
        sql="select cate_id from category where cate_name='"+award.getCate_name()+"'";
        Integer cate_id=jdbcTemplate.queryForObject(sql,Integer.class);
        sql="select com_id from competition where cate_id='"+cate_id+"' and com_num='"+award.getCom_num()+"'";
        Integer com_id=jdbcTemplate.queryForObject(sql,Integer.class);
        sql="insert into award(award_id,user_id,award_check,award_prove,com_id,award_level) values(null,?,0,null,?,?)";
        jdbcTemplate.update(sql,user_id,com_id,award.getAward_level());
        return 666;
    }

}
