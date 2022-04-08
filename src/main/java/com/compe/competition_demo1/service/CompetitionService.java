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

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface CompetitionService {
    List<Competition> Comhot()throws SQLException;  //热点竞赛展示
    keyCom_out keyCom(keyCom_in keycom_in)throws SQLException;  //关键词搜索
    keyCom_out dateCom(dateCom_in datecom_in)throws SQLException; //日期搜索
    keyCom_out levelCom(levelCom_in levelcom_in)throws SQLException; //赛事级别
    keyCom_out majorCom(majorCom_in majorcom_in)throws SQLException; //专业搜索
    keyCom_out cateCom(cateCom_in catecom_in)throws SQLException; //赛事类别
    findallCom_out findallCom(Integer pageNum, Integer pageSize)throws SQLException;//竞赛分页展示 按时间排序
    idCom_out idCom(Integer com_id)throws SQLException;//id查询
    addCom_out addCom(addCom_in addcom_in)throws SQLException;//发布竞赛
    deleteCom_out deleteCom(deleteCom_in deletecom_in)throws SQLException;    //删除竞赛
    updateCom_out updateCom(updateCom_in updatecom_in)throws SQLException;  //修改竞赛
    List<Competition> idsignCom(Integer com_id)throws SQLException;    //用竞赛id查询报名信息
    List<Competition> idwardCom(Integer com_id)throws SQLException;    //用竞赛id查询获奖信息
    List<Competition> SearchConnopass()throws SQLException;//管理员未审核竞赛
    List<Competition> SearchConpass()throws SQLException;//管理员已审核竞赛
    int Control(Integer com_id,Integer com_check)throws SQLException;//审核竞赛
    List<Competition> searchNoPass(Integer user_id)throws SQLException;//查询负责人发布的未审核竞赛
    List<Competition> searchPass(Integer user_id)throws SQLException;//查询负责人发布的已审核竞赛
    //批量导入报名信息
    int sign(Integer com_id, Integer user_id) throws SQLException;//用户报名
    List<Competition> middate(Date date1, Date date2)throws SQLException;//根据时间范围返回竞赛
}
