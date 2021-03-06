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
import com.compe.competition_demo1.cdata.competitionsth.reg;
import com.compe.competition_demo1.cdata.competitionsth.searchpass.searchpassCom_out;
import com.compe.competition_demo1.cdata.competitionsth.sign.signCom_in;
import com.compe.competition_demo1.cdata.competitionsth.stuCom.stuCom_out;
import com.compe.competition_demo1.cdata.competitionsth.stuCom.stunoCom_out;
import com.compe.competition_demo1.cdata.competitionsth.update.updateCom_in;
import com.compe.competition_demo1.cdata.competitionsth.update.updateCom_out;
import com.compe.competition_demo1.cdata.competitionsth.year.yearCom_in;
import com.compe.competition_demo1.cdata.competitionsth.year.yearCom_out;

import java.sql.SQLException;
import java.util.List;

public interface CompetitionService {
    hotCom_out Comhot()throws SQLException;  //??????????????????
    keyCom_out keyCom(keyCom_in keycom_in)throws SQLException;  //???????????????
    keyCom_out dateCom(dateCom_in datecom_in)throws SQLException; //????????????
    keyCom_out levelCom(levelCom_in levelcom_in)throws SQLException; //????????????
    keyCom_out majorCom(majorCom_in majorcom_in)throws SQLException; //????????????
    keyCom_out cateCom(cateCom_in catecom_in)throws SQLException; //????????????
    findallCom_out findallCom(findallCom_in findallcom_in)throws SQLException;//?????????????????? ???????????????
    idCom_out idCom(Integer com_id)throws SQLException;//id??????
    addCom_out addCom(addCom_in addcom_in)throws SQLException;//????????????
    deleteCom_out deleteCom(deleteCom_in deletecom_in)throws SQLException;    //????????????
    updateCom_out updateCom(updateCom_in updatecom_in)throws SQLException;  //????????????
    idsignCom_out idsignCom(Integer com_id)throws SQLException;    //?????????id??????????????????
    idwardCom_out idwardCom(Integer com_id)throws SQLException;    //?????????id??????????????????
    searchpassCom_out SearchConnopass()throws SQLException;//????????????????????????
    searchpassCom_out SearchConpass()throws SQLException;//????????????????????????
    int Control(controlCom_in controlcom_in)throws SQLException;//????????????
    searchpassCom_out searchNoPass(Integer user_id)throws SQLException;//???????????????????????????????????????
    searchpassCom_out searchPass(Integer user_id)throws SQLException;//???????????????????????????????????????
    //????????????????????????
    int sign(signCom_in signcom_in) throws SQLException;//????????????
    middateCom_out middate(middateCom_in middatecom_in)throws SQLException;//??????????????????????????????
    yearCom_out year(yearCom_in yearcom_in)throws SQLException;
    stunoCom_out stu_nocomplete(int user_id)throws SQLException; //????????????????????????????????????
    stuCom_out stu_complete(int user_id)throws SQLException;
    int AddExcel(reg reg)throws SQLException;
    int PassCom()throws SQLException;
}
