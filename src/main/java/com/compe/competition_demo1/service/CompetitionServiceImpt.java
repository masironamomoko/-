package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.Competition;
import com.compe.competition_demo1.cdata.competitions_os.add.addCom_in;
import com.compe.competition_demo1.cdata.competitions_os.add.addCom_out;
import com.compe.competition_demo1.cdata.competitions_os.cate.cateCom_in;
import com.compe.competition_demo1.cdata.competitions_os.date.dateCom_in;
import com.compe.competition_demo1.cdata.competitions_os.delete.deleteCom_in;
import com.compe.competition_demo1.cdata.competitions_os.delete.deleteCom_out;
import com.compe.competition_demo1.cdata.competitions_os.findall.findallCom_out;
import com.compe.competition_demo1.cdata.competitions_os.id.idCom_out;
import com.compe.competition_demo1.cdata.competitions_os.key.keyCom_in;
import com.compe.competition_demo1.cdata.competitions_os.keyCom_out;
import com.compe.competition_demo1.cdata.competitions_os.level.levelCom_in;
import com.compe.competition_demo1.cdata.competitions_os.major.majorCom_in;
import com.compe.competition_demo1.cdata.competitions_os.update.updateCom_in;
import com.compe.competition_demo1.cdata.competitions_os.update.updateCom_out;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class CompetitionServiceImpt implements CompetitionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //热点竞赛
    @Override
    public List<Competition> Comhot() throws SQLException{
        String sql = "select com_id,com_mainname,com_status,com_manager,sign_up_start,sign_up_end from competition order by com_id desc limit 0,5";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Competition>(Competition.class));
    }

    //关键词搜索
    @Override
    public keyCom_out keyCom(keyCom_in keycom_in)throws SQLException{
        keyCom_out key = new keyCom_out();
        String sql1 = "select com_id,com_mainname,com_status,com_manager,sign_up_start,sign_up_end from competiton where com_mainname like '%"+keycom_in.getKey()+"%'  order by com_id desc limit ?,?";
        key.setComList(jdbcTemplate.query(sql1,new BeanPropertyRowMapper<Competition>(Competition.class),(keycom_in.getPageNum()-1)*keycom_in.getPageSize(),keycom_in.getPageNum()*keycom_in.getPageSize()));
        String sql2 = "select count(*) from competition where com_mainname like'%"+keycom_in.getKey()+"%'";
        key.setTotal(jdbcTemplate.queryForObject(sql2,Integer.class));
        return key;
    }
    //日期搜索
    @Override
    public keyCom_out dateCom(dateCom_in datecom_in) throws SQLException{
        keyCom_out key = new keyCom_out();
        String sql1 = "select com_id,com_mainname,com_status,com_manager,sign_up_start,sign_up_end from competiton where com_date ='%"+datecom_in.getDate()+"%' between sign_up_start and sign_up_end order by com_id desc limit ?,?";
        key.setComList(jdbcTemplate.query(sql1,new BeanPropertyRowMapper<Competition>(Competition.class),(datecom_in.getPageNum()-1)*datecom_in.getPageSize(),datecom_in.getPageNum()*datecom_in.getPageSize()));
        String sql2 = "select count(*) from competition where com_date ='%"+datecom_in.getDate()+"%' between sign_up_start and sign_up_end";
        key.setTotal(jdbcTemplate.queryForObject(sql2,Integer.class));
        return key;
    }

    //赛事级别
    @Override
    public keyCom_out levelCom(levelCom_in levelcom_in) throws SQLException {
        keyCom_out key = new keyCom_out();
        String sql1 = "select com_id,com_mainname,com_status,com_manager,sign_up_start,sign_up_end from competiton where com_level like '%"+levelcom_in.getLevel()+"%'  order by com_id desc limit ?,?";
        key.setComList(jdbcTemplate.query(sql1,new BeanPropertyRowMapper<Competition>(Competition.class),(levelcom_in.getPageNum()-1)*levelcom_in.getPageSize(),levelcom_in.getPageNum()*levelcom_in.getPageSize()));
        String sql2 = "select count(*) from competition where com_level like'%"+levelcom_in.getLevel()+"%'";
        key.setTotal(jdbcTemplate.queryForObject(sql2,Integer.class));
        return key;
    }

    //专业
    @Override
    public keyCom_out majorCom(majorCom_in majorcom_in) throws SQLException {
        keyCom_out key = new keyCom_out();
        String sql1 = "select com_id,com_mainname,com_status,com_manager,sign_up_start,sign_up_end from competiton where com_major like '%"+majorcom_in.getMajor()+"%'  order by com_id desc limit ?,?";
        key.setComList(jdbcTemplate.query(sql1,new BeanPropertyRowMapper<Competition>(Competition.class),(majorcom_in.getPageNum()-1)*majorcom_in.getPageSize(),majorcom_in.getPageNum()*majorcom_in.getPageSize()));
        String sql2 = "select count(*) from competition where com_major like'%"+majorcom_in.getMajor()+"%'";
        key.setTotal(jdbcTemplate.queryForObject(sql2,Integer.class));
        return key;
    }

    //赛事类别
    @Override
    public keyCom_out cateCom(cateCom_in catecom_in) throws SQLException {
        keyCom_out key = new keyCom_out();
        String sql1 = "select com_id,com_mainname,com_status,com_manager,sign_up_start,sign_up_end from competiton where com_category like '%"+catecom_in.getCategory()+"%'  order by com_id desc limit ?,?";
        key.setComList(jdbcTemplate.query(sql1,new BeanPropertyRowMapper<Competition>(Competition.class),(catecom_in.getPageNum()-1)*catecom_in.getPageSize(),catecom_in.getPageNum()*catecom_in.getPageSize()));
        String sql2 = "select count(*) from competition where com_category like'%"+catecom_in.getCategory()+"%'";
        key.setTotal(jdbcTemplate.queryForObject(sql2,Integer.class));
        return key;
    }

    //id查询
    public idCom_out idCom(Integer com_id) throws SQLException {
        String sql1="select com_id,com_mainname,com_status,com_manager,sign_up_start,sign_up_end from announcement where com_id='"+com_id+"'";
        idCom_out id =new idCom_out();
        jdbcTemplate.queryForObject(sql1,new BeanPropertyRowMapper<Competition>(Competition.class));
        String sql2 = "select count(*) from competition where com_id = '%"+com_id+"%'";
        int count = jdbcTemplate.queryForObject(sql2,Integer.class);
        if(count!=0)
            id.setCode(666);
        else id.setCode(700);
        return id;
    }

    //竞赛分页展示 按时间排序
    @Override
    public findallCom_out findallCom(Integer pageNum, Integer pageSize) throws SQLException{
        findallCom_out fin = new findallCom_out();
        String sql1 = "select * from competition order by com_id desc limit ?,?";
        fin.setComList(jdbcTemplate.query(sql1,new BeanPropertyRowMapper<Competition>(Competition.class),(pageNum-1)*pageSize,pageNum*pageSize));
        String sql2 = "select count(*) from competition";
        fin.setTotal(jdbcTemplate.queryForObject(sql2,Integer.class));
        return fin;
    }

    //发布
    @Override
    public addCom_out addCom(addCom_in addcom_in) throws SQLException {
        addCom_out add = new addCom_out();
        String sql1 = "insert into competition(com_mainname,com_manager,com_subname,com_level,com_major,com_category,com_information,sign_up_start,sign_up_end,preliminary_start,preliminary_end,repecharge_start,repecharge_end,finals_start,finals_end,attachment1,attachment2,attachment3,com_status,com_schedule,sign_num,award1,award2,award3,award_other,com_check) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,null,null,null,null,null,null,null,null,null,null,null)";
        jdbcTemplate.update(sql1, addcom_in.getCom_mainname(),addcom_in.getCom_manager(),addcom_in.getCom_subname(),addcom_in.getCom_level(),addcom_in.getCom_major(),addcom_in.getCom_category(),addcom_in.getCom_information(),addcom_in.getSign_up_start(),addcom_in.getSign_up_end(),addcom_in.getPreliminary_start(),addcom_in.getPreliminary_end(),addcom_in.getRepecharge_start(),addcom_in.getRepecharge_end(),addcom_in.getFinals_start(),addcom_in.getFinals_end());
        String sql2 = "select count(*) from competition where com_mainname = '"+addcom_in.getCom_mainname()+"'and com_manager = '"+addcom_in.getCom_manager()+"'";
        int count  = jdbcTemplate.queryForObject(sql2,Integer.class);
        if(count==0)
            add.setCode(700);
        else
            add.setCode(666);
        return add;
    }

    //删除
    @Override
    public deleteCom_out deleteCom(deleteCom_in deletecom_in) throws SQLException{
        deleteCom_out delete = new deleteCom_out();
        String sql1="delete * in competition where com_id=?";
        jdbcTemplate.update(sql1,deletecom_in.getCom_id());
        String sql2 = "select count(*) from competition where com_id = '"+deletecom_in.getCom_id()+"'";
        int count = jdbcTemplate.queryForObject(sql2,Integer.class);
        if(count==0)
            delete.setCode(666);
        else
            delete.setCode(700);
        return delete;
    }

    //修改
    @Override
    public updateCom_out updateCom(updateCom_in updatecom_in) throws SQLException{
        updateCom_out up = new updateCom_out();
        String sql1="update competition set com_mainname=?,com_subname=?,com_level=?,com_major=?,com_category=?,com_information=?,sign_up_start=?,sign_up_end=?,preliminary_start=?,preliminary_end=?,repecharge_start=?,repecharge_end=?,finals_start=?,finals_end=? where com_id = ?";
        jdbcTemplate.update(sql1,updatecom_in.getCom_mainname(),updatecom_in.getCom_subname(),updatecom_in.getCom_level(),updatecom_in.getCom_major(),updatecom_in.getCom_category(),updatecom_in.getCom_information(),updatecom_in.getSign_up_start(),updatecom_in.getSign_up_end(),updatecom_in.getPreliminary_start(),updatecom_in.getPreliminary_end(),updatecom_in.getRepecharge_start(),updatecom_in.getRepecharge_end(),updatecom_in.getFinals_start(),updatecom_in.getFinals_end(),updatecom_in.getCom_id());
        String sql2 = "select count(*) from competition where com_id = '"+updatecom_in.getCom_id()+"'and com_mainname='"+updatecom_in.getCom_mainname()+"'";
        int count = jdbcTemplate.queryForObject(sql2,Integer.class);
        if(count==0)
            up.setCode(700);
        else
            up.setCode(666);
        return up;
    }

    //用竞赛id查询报名信息
    @Override
    public List<Competition> idsignCom(Integer com_id) throws SQLException {
        String sql="select user_id,user_name,user_num,user_phone from competition where com_id='"+com_id+"'";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Competition>(Competition.class));
    }

    //用竞赛id查询获奖信息
    @Override
    public List<Competition> idwardCom(Integer com_id) throws SQLException {
        String sql="select user_name,user_num,com_id,com_mainname,award from competition where com_id='"+com_id+"'";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Competition>(Competition.class));
    }

    //管理员未审核竞赛
    @Override
    public List<Competition> SearchConnopass() throws SQLException {
        String sql="select com_id,com_date,com_mainname,com_manager from competition where com_check=0";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Competition>(Competition.class));
    }

    //管理员已审核竞赛
    @Override
    public List<Competition> SearchConpass() throws SQLException {
        String sql="select com_id,com_date,com_mainname,com_manager from competition where com_check=!0";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Competition>(Competition.class));
    }

    //审核竞赛
    @Override
    public int Control(Integer com_id,Integer com_check)throws SQLException{
        String sql1="update competition set com_check=? where com_id=?";
        jdbcTemplate.update(sql1,com_check,com_id);
        String sql2="select count(*) from competition where com_check='"+com_check+"' and com_id='"+com_id+"'";
        int count=jdbcTemplate.queryForObject(sql2,Integer.class);
        if(count!=1)
            return 700;
        return 666;
    }

    //查询负责人发布的未审核竞赛
    @Override
    public List<Competition> searchNoPass(Integer user_id) throws SQLException {
        String sql="select com_id,com_date,com_mainname,com_manager from competition where com_check=0 and com_manager =?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Competition>(Competition.class),user_id);
    }

    //查询负责人发布的已审核竞赛
    @Override
    public List<Competition> searchPass(Integer user_id) throws SQLException {
        String sql="select com_id,com_date,com_mainname,com_manager from competition where com_check!=0 and com_manager=?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Competition>(Competition.class),user_id);
    }

    //批量导入报名信息

    //用户报名
    @Override
    public int sign(Integer com_id, Integer user_id) throws SQLException{
        String sql1="update competition_management set com_id=?where user_id=?";
        jdbcTemplate.update(sql1,com_id,user_id);
        String sql2 = "select count(*) from competition_management where com_id='"+com_id+"'and user_id='"+user_id+"'";
        int count = jdbcTemplate.queryForObject(sql2,Integer.class);
        if(count == 1)
            return 666;
        else return 700;
    }

    //根据时间范围返回竞赛
    @Override
    public List<Competition> middate(Date date1, Date date2)throws SQLException{
        String sql="select com_id,com_mainname,com_manager,sign_up_start,com_level,com_category,com_major from competition where date1 >= sign_up_start and date2 <= sign_up_end";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Competition>(Competition.class),date1,date2);
    }
}