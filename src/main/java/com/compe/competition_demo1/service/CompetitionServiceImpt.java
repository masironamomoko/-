package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.competitionsth.Competition;
import com.compe.competition_demo1.cdata.competitionsth.cate.cateCom_in;
import com.compe.competition_demo1.cdata.competitionsth.control.controlCom_in;
import com.compe.competition_demo1.cdata.competitionsth.findall.findallCom_in;
import com.compe.competition_demo1.cdata.competitionsth.id.idCom_out;
import com.compe.competition_demo1.cdata.competitionsth.idsign.idsignCom;
import com.compe.competition_demo1.cdata.competitionsth.idsign.idsignCom_out;
import com.compe.competition_demo1.cdata.competitionsth.idward.idwardCom;
import com.compe.competition_demo1.cdata.competitionsth.idward.idwardCom_out;
import com.compe.competition_demo1.cdata.competitionsth.middate.middateCom_in;
import com.compe.competition_demo1.cdata.competitionsth.middate.middateCom_out;
import com.compe.competition_demo1.cdata.competitionsth.middate.middateout;
import com.compe.competition_demo1.cdata.competitionsth.searchpass.searchpassCom;
import com.compe.competition_demo1.cdata.competitionsth.sign.signCom_in;
import com.compe.competition_demo1.cdata.competitionsth.year.yearCom;
import com.compe.competition_demo1.cdata.competitionsth.year.yearCom_in;
import com.compe.competition_demo1.cdata.competitionsth.year.yearCom_out;
import com.compe.competition_demo1.service.CompetitionService;
import com.compe.competition_demo1.cdata.competitionsth.add.addCom_in;
import com.compe.competition_demo1.cdata.competitionsth.add.addCom_out;
import com.compe.competition_demo1.cdata.competitionsth.date.dateCom_in;
import com.compe.competition_demo1.cdata.competitionsth.delete.deleteCom_in;
import com.compe.competition_demo1.cdata.competitionsth.delete.deleteCom_out;
import com.compe.competition_demo1.cdata.competitionsth.findall.findallCom_out;
import com.compe.competition_demo1.cdata.competitionsth.hot.hotCom;
import com.compe.competition_demo1.cdata.competitionsth.hot.hotCom_out;
import com.compe.competition_demo1.cdata.competitionsth.key.keyCom_in;
import com.compe.competition_demo1.cdata.competitionsth.keyCom_out;
import com.compe.competition_demo1.cdata.competitionsth.level.levelCom_in;
import com.compe.competition_demo1.cdata.competitionsth.major.majorCom_in;
import com.compe.competition_demo1.cdata.competitionsth.searchpass.searchpassCom_out;
import com.compe.competition_demo1.cdata.competitionsth.update.updateCom_in;
import com.compe.competition_demo1.cdata.competitionsth.update.updateCom_out;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CompetitionServiceImpt implements CompetitionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //热点竞赛
    @Override
    public hotCom_out Comhot() throws SQLException{
        hotCom_out hot = new hotCom_out();
        String sql = "select com_id,com_mainname,com_status,com_manager,sign_up_start,sign_up_end from competition order by com_id desc limit 0,5";
        hot.setData(jdbcTemplate.query(sql, new BeanPropertyRowMapper<hotCom>(hotCom.class)));
        return hot;
    }

    //关键词搜索
    @Override
    public keyCom_out keyCom(keyCom_in keycom_in)throws SQLException{
        keyCom_out key = new keyCom_out();
        String sql1 = "select com_id,com_mainname,com_status,com_manager,sign_up_start,sign_up_end from competiton where com_mainname like '%"+keycom_in.getKey()+"%'  order by com_id desc limit "+(keycom_in.getPageNum()-1)*keycom_in.getPageSize()+","+keycom_in.getPageSize()+"";
        key.setComList(jdbcTemplate.query(sql1,new BeanPropertyRowMapper<Competition>(Competition.class)));
        String sql2 = "select count(*) from competition where com_mainname like'%"+keycom_in.getKey()+"%'";
        key.setTotal(jdbcTemplate.queryForObject(sql2,Integer.class));
        return key;
    }

    //日期搜索,报名时间在这个之后的全部
    @Override
    public keyCom_out dateCom(dateCom_in datecom_in) throws SQLException{
        keyCom_out key = new keyCom_out();
        String sql1 = "select com_id,com_mainname,com_status,com_manager,sign_up_start,sign_up_end from competition where com_date ='"+datecom_in.getDate()+"' between sign_up_start and sign_up_end order by com_id desc limit "+(datecom_in.getPageNum()-1)*datecom_in.getPageSize()+","+datecom_in.getPageSize()+"";
        key.setComList(jdbcTemplate.query(sql1,new BeanPropertyRowMapper<Competition>(Competition.class)));
        String sql2 = "select count(*) from competition where com_date ='"+datecom_in.getDate()+"' between sign_up_start and sign_up_end";
        key.setTotal(jdbcTemplate.queryForObject(sql2,Integer.class));
        return key;
    }

    //赛事级别
    @Override
    public keyCom_out levelCom(levelCom_in levelcom_in) throws SQLException {
        keyCom_out key = new keyCom_out();
        String sql1 = "select com_id,com_mainname,com_status,com_manager,sign_up_start,sign_up_end from competiton where com_level = '"+levelcom_in.getLevel()+"' order by com_id desc limit "+(levelcom_in.getPageNum()-1)*levelcom_in.getPageSize()+","+levelcom_in.getPageSize()+"";
        key.setComList(jdbcTemplate.query(sql1,new BeanPropertyRowMapper<Competition>(Competition.class)));
        String sql2 = "select count(*) from competition where com_level='"+levelcom_in.getLevel()+"'";
        key.setTotal(jdbcTemplate.queryForObject(sql2,Integer.class));
        return key;
    }

    //专业
    @Override
    public keyCom_out majorCom(majorCom_in majorcom_in) throws SQLException {
        keyCom_out key = new keyCom_out();
        String sql1 = "select com_id,com_mainname,com_status,com_manager,sign_up_start,sign_up_end from competiton where com_major = '"+majorcom_in.getMajor()+"' order by com_id desc limit "+(majorcom_in.getPageNum()-1)*majorcom_in.getPageSize()+","+majorcom_in.getPageSize()+"";
        key.setComList(jdbcTemplate.query(sql1,new BeanPropertyRowMapper<Competition>(Competition.class)));
        String sql2 = "select count(*) from competition where com_major ='"+majorcom_in.getMajor()+"'";
        key.setTotal(jdbcTemplate.queryForObject(sql2,Integer.class));
        return key;
    }

    //赛事类别
    @Override
    public keyCom_out cateCom(cateCom_in catecom_in) throws SQLException {
        keyCom_out key = new keyCom_out();
        String sql1 = "select com_id,com_mainname,com_status,com_manager,sign_up_start,sign_up_end from competiton where com_category = '"+catecom_in.getCategory()+"' order by com_id desc limit "+(catecom_in.getPageNum()-1)*catecom_in.getPageSize()+","+catecom_in.getPageSize()+"";
        key.setComList(jdbcTemplate.query(sql1,new BeanPropertyRowMapper<Competition>(Competition.class)));
        String sql2 = "select count(*) from competition where com_category = '"+catecom_in.getCategory()+"'";
        key.setTotal(jdbcTemplate.queryForObject(sql2,Integer.class));
        return key;
    }

    //id查询
    public idCom_out idCom(Integer com_id) throws SQLException {
        String sql1="select com_id,com_date,user_name,com_manager,com_subname,com_level,com_major,com_category,com_information,sign_up_start,sign_up_end,preliminary_start,preliminary_end,repecharge_start,repecharge_end,finals_start,finals_end,attachment1,attachment2,attachment3,com_status,com_schedule,sign_num,award1,award2,award3,award_other,com_check from competition c left join user u on c.com_manager = u.user_id where c.com_id="+com_id+"";
        idCom_out id =new idCom_out();
        id.setCompetition(jdbcTemplate.queryForObject(sql1,new BeanPropertyRowMapper<Competition>(Competition.class)));
        String sql2 = "select count(*) from competition where com_id = "+com_id+"";
        int count = jdbcTemplate.queryForObject(sql2,Integer.class);
        if(count!=0)
            id.setCode(666);
        else id.setCode(700);
        return id;
    }

    //竞赛分页展示 按时间排序
    @Override
    public findallCom_out findallCom(findallCom_in findallcom_in) throws SQLException{
        findallCom_out fin = new findallCom_out();
        String sql1 = "select * from competition order by com_date desc limit "+(findallcom_in.getPageNum()-1)*findallcom_in.getPageSize()+","+findallcom_in.getPageSize()+"";
        fin.setComList(jdbcTemplate.query(sql1,new BeanPropertyRowMapper<Competition>(Competition.class)));
        String sql2 = "select count(*) from competition";
        fin.setTotal(jdbcTemplate.queryForObject(sql2,Integer.class));
        return fin;
    }

    //发布
    @Override
    public addCom_out addCom(addCom_in addcom_in) throws SQLException {
        addCom_out add = new addCom_out();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date com_date = new Date(System.currentTimeMillis());
        formatter.format(com_date);
        String sql1 = "insert into competition(com_date,com_mainname,com_manager,com_num,com_level,com_major,cate_name,com_information,sign_up_start,sign_up_end,preliminary_start,preliminary_end,repecharge_start,repecharge_end,finals_start,finals_end,attachment1,attachment2,attachment3,com_status,com_schedule,sign_num,award1,award2,award3,award_other,com_check) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,null,null,null,0,0,0,0,0,0,0,0)";
        jdbcTemplate.update(sql1, com_date,addcom_in.getCom_num()+addcom_in.getCate_name(),addcom_in.getCom_manager(),addcom_in.getCom_num(),addcom_in.getCom_level(),addcom_in.getCom_major(),addcom_in.getCate_name(),addcom_in.getCom_information(),addcom_in.getSign_up_start(),addcom_in.getSign_up_end(),addcom_in.getPreliminary_start(),addcom_in.getPreliminary_end(),addcom_in.getRepecharge_start(),addcom_in.getRepecharge_end(),addcom_in.getFinals_start(),addcom_in.getFinals_end());
        String sql2 = "select count(*) from competition where cate_name = '"+addcom_in.getCate_name()+"'and com_manager = "+addcom_in.getCom_manager()+"and com_num = '"+addcom_in.getCom_num()+"'";
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
        String sql2 = "select count(*) from competition where com_id = "+deletecom_in.getCom_id()+"";
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
        String sql2 = "select count(*) from competition where com_id = "+updatecom_in.getCom_id()+"and com_mainname='"+updatecom_in.getCom_mainname()+"'";
        int count = jdbcTemplate.queryForObject(sql2,Integer.class);
        if(count==0)
            up.setCode(700);
        else
            up.setCode(666);
        return up;
    }

    //用竞赛id查询报名信息
    @Override
    public idsignCom_out idsignCom(Integer com_id) throws SQLException {
        idsignCom_out idsign = new idsignCom_out();
        String sql="select user_id,user_name,user_num,user_phone from user u left join registration_management r on u.user_id = r.user_id where r.com_id="+com_id+"";
        idsign.setData(jdbcTemplate.query(sql,new BeanPropertyRowMapper<idsignCom>(idsignCom.class)));
        return idsign;
    }

    //用竞赛id查询获奖信息,多表
    @Override
    public idwardCom_out idwardCom(Integer com_id) throws SQLException {
        idwardCom_out idward = new idwardCom_out();
        String sql="select user_name,user_num,com_id,com_mainname,award from (registration_management r left join user u on r.user_id = u.user_id) left join competition c on r.com_id = c.com_id where com_id="+com_id+"";
        idward.setData(jdbcTemplate.query(sql,new BeanPropertyRowMapper<idwardCom>(idwardCom.class)));
        return idward;
    }

    //管理员未审核竞赛
    @Override
    public searchpassCom_out SearchConnopass() throws SQLException {
        searchpassCom_out pass = new searchpassCom_out();
        String sql="select com_id,com_date,com_mainname,user_name from competition c left join user u on c.com_manager = u.user_id where c.com_check=0";
        pass.setData(jdbcTemplate.query(sql,new BeanPropertyRowMapper<searchpassCom>(searchpassCom.class)));
        return pass;
    }

    //管理员已审核竞赛
    @Override
    public searchpassCom_out SearchConpass() throws SQLException {
        searchpassCom_out pass = new searchpassCom_out();
        String sql="select com_id,com_date,com_mainname,user_name from competition c left join user u on c.com_manager = u.user_id where c.com_check!=0";
        pass.setData(jdbcTemplate.query(sql,new BeanPropertyRowMapper<searchpassCom>(searchpassCom.class)));
        return pass;
    }

    //审核竞赛
    @Override
    public int Control(controlCom_in controlcom_in)throws SQLException{
        String sql1="update competition set com_check=? where com_id=?";
        jdbcTemplate.update(sql1,controlcom_in.getCom_check(),controlcom_in.getCom_id());
        String sql2="select count(*) from competition where com_check="+controlcom_in.getCom_check()+" and com_id="+controlcom_in.getCom_id()+"";
        int count=jdbcTemplate.queryForObject(sql2,Integer.class);
        if(count!=1)
            return 700;
        return 666;
    }

    //查询负责人发布的未审核竞赛
    @Override
    public searchpassCom_out searchNoPass(Integer user_id) throws SQLException {
        searchpassCom_out pass = new searchpassCom_out();
        String sql="select com_id,com_date,com_mainname,user_name from competition c left join user u on c.com_manager = u.user_id where com_check=0 and com_manager =?";
        pass.setData(jdbcTemplate.query(sql,new BeanPropertyRowMapper<searchpassCom>(searchpassCom.class),user_id));
        return pass;
    }

    //查询负责人发布的已审核竞赛
    @Override
    public searchpassCom_out searchPass(Integer user_id) throws SQLException {
        searchpassCom_out pass = new searchpassCom_out();
        String sql="select com_id,com_date,com_mainname,user_name from competition c left join user u on c.com_manager = u.user_id where com_check!=0 and com_manager=?";
        pass.setData(jdbcTemplate.query(sql,new BeanPropertyRowMapper<searchpassCom>(searchpassCom.class),user_id));
        return pass;
    }

    //批量导入报名信息

    //用户报名
    @Override
    public int sign(signCom_in signcom_in) throws SQLException{
        String sql1="update competition_management set com_id=?where user_id=?";
        jdbcTemplate.update(sql1,signcom_in.getCom_id(),signcom_in.getUser_id());
        String sql2 = "select count(*) from competition_management where com_id="+signcom_in.getCom_id()+"and user_id="+signcom_in.getUser_id()+"";
        int count = jdbcTemplate.queryForObject(sql2,Integer.class);
        if(count == 1)
            return 666;
        else return 700;
    }

    //根据时间范围返回竞赛
    @Override
    public middateCom_out middate(middateCom_in middatecom_in)throws SQLException{
        middateCom_out mid = new middateCom_out();
        String sql="select com_id,com_mainname,user_name,sign_up_start,com_level,com_category,com_major from competition c left join user u on c.com_manager = u.user_id where sign_up_start<= '"+middatecom_in.getDate1()+"'and sign_up_end>= '"+middatecom_in.getDate2()+"'";
        mid.setMiddateCom_out(jdbcTemplate.query(sql,new BeanPropertyRowMapper<middateout>(middateout.class)));
        return mid;
    }

    //年份查询，当年全部
    public yearCom_out year(yearCom_in yearcom_in)throws SQLException{
        yearCom_out year = new yearCom_out();
        String sql="select com_id,com_mainname,user_name,sign_up_start,com_level,com_category,com_major from competition c left join user u on c.com_manager = u.user_id where sign_up_start like '%"+yearcom_in.getCom_date()+"%'";
        year.setYearCom_out(jdbcTemplate.query(sql,new BeanPropertyRowMapper<yearCom>(yearCom.class)));
        return year;
    }

    //查询学生报名的未完成竞赛
    @Override
    public List<Competition> stu_nocomplete(int id) throws SQLException {
        String sql="select com_id,com_date,com_mainname,com_status,com_manager from competition where com_id in (select com_id from registration_management where user_id="+id+") and com_status!=2";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Competition>(Competition.class));
    }

    @Override
    public List<Competition> stu_complete(int id) throws SQLException {
        String sql="select com_id,com_date,com_mainname,com_status,com_schedule,com_manager from competition where com_id in(select com_id from registration_management where user_id="+id+") and com_status=2";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Competition>(Competition.class));
    }
}
