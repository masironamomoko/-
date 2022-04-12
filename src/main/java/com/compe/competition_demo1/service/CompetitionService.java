package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.competitionsth.Competition;
import com.compe.competition_demo1.cdata.competitionsth.cate.cateCom_in;
import com.compe.competition_demo1.cdata.competitionsth.add.addCom_in;
import com.compe.competition_demo1.cdata.competitionsth.add.addCom_out;
import com.compe.competition_demo1.cdata.competitionsth.control.controlCom_in;
import com.compe.competition_demo1.cdata.competitionsth.date.dateCom_in;
import com.compe.competition_demo1.cdata.competitionsth.delete.deleteCom_in;
import com.compe.competition_demo1.cdata.competitionsth.delete.deleteCom_out;
import com.compe.competition_demo1.cdata.competitionsth.findall.findallCom_in;
import com.compe.competition_demo1.cdata.competitionsth.findall.findallCom_out;
import com.compe.competition_demo1.cdata.competitionsth.hot.hotCom_out;
import com.compe.competition_demo1.cdata.competitionsth.id.idCom_out;
import com.compe.competition_demo1.cdata.competitionsth.idsign.idsignCom_out;
import com.compe.competition_demo1.cdata.competitionsth.idward.idwardCom_out;
import com.compe.competition_demo1.cdata.competitionsth.key.keyCom_in;
import com.compe.competition_demo1.cdata.competitionsth.keyCom_out;
import com.compe.competition_demo1.cdata.competitionsth.level.levelCom_in;
import com.compe.competition_demo1.cdata.competitionsth.major.majorCom_in;
import com.compe.competition_demo1.cdata.competitionsth.middate.middateCom_in;
import com.compe.competition_demo1.cdata.competitionsth.middate.middateCom_out;
import com.compe.competition_demo1.cdata.competitionsth.searchpass.searchpassCom_out;
import com.compe.competition_demo1.cdata.competitionsth.sign.signCom_in;
import com.compe.competition_demo1.cdata.competitionsth.update.updateCom_in;
import com.compe.competition_demo1.cdata.competitionsth.update.updateCom_out;
import com.compe.competition_demo1.cdata.competitionsth.year.yearCom_in;
import com.compe.competition_demo1.cdata.competitionsth.year.yearCom_out;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface CompetitionService {
    hotCom_out Comhot()throws SQLException;  //热点竞赛展示
    keyCom_out keyCom(keyCom_in keycom_in)throws SQLException;  //关键词搜索
    keyCom_out dateCom(dateCom_in datecom_in)throws SQLException; //日期搜索
    keyCom_out levelCom(levelCom_in levelcom_in)throws SQLException; //赛事级别
    keyCom_out majorCom(majorCom_in majorcom_in)throws SQLException; //专业搜索
    keyCom_out cateCom(cateCom_in catecom_in)throws SQLException; //赛事类别
    findallCom_out findallCom(findallCom_in findallcom_in)throws SQLException;//竞赛分页展示 按时间排序
    idCom_out idCom(Integer com_id)throws SQLException;//id查询
    addCom_out addCom(addCom_in addcom_in)throws SQLException;//发布竞赛
    deleteCom_out deleteCom(deleteCom_in deletecom_in)throws SQLException;    //删除竞赛
    updateCom_out updateCom(updateCom_in updatecom_in)throws SQLException;  //修改竞赛
    idsignCom_out idsignCom(Integer com_id)throws SQLException;    //用竞赛id查询报名信息
    idwardCom_out idwardCom(Integer com_id)throws SQLException;    //用竞赛id查询获奖信息
    searchpassCom_out SearchConnopass()throws SQLException;//管理员未审核竞赛
    searchpassCom_out SearchConpass()throws SQLException;//管理员已审核竞赛
    int Control(controlCom_in controlcom_in)throws SQLException;//审核竞赛
    searchpassCom_out searchNoPass(Integer user_id)throws SQLException;//查询负责人发布的未审核竞赛
    searchpassCom_out searchPass(Integer user_id)throws SQLException;//查询负责人发布的已审核竞赛
    //批量导入报名信息
    int sign(signCom_in signcom_in) throws SQLException;//用户报名
    middateCom_out middate(middateCom_in middatecom_in)throws SQLException;//根据时间范围返回竞赛
    yearCom_out year(yearCom_in yearcom_in)throws SQLException;
    List<Competition> stu_nocomplete(int id)throws SQLException; //查询学生报名的未完成竞赛
    List<Competition> stu_complete(int id)throws SQLException;
}
