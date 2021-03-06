package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.cdata.competitionsth.add.addCom_in;
import com.compe.competition_demo1.cdata.competitionsth.add.addCom_out;
import com.compe.competition_demo1.cdata.competitionsth.cate.cateCom_in;
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
import com.compe.competition_demo1.service.CompetitionService;
import com.compe.competition_demo1.util.ReadRegExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/com")

public class CompetitionController {
    @Autowired
    @Resource
    private CompetitionService service;

    //????????????
    @RequestMapping(value = "hot")
    public hotCom_out hotCom(HttpServletResponse response)throws SQLException{
        return service.Comhot();
    }

    //???????????????
    @RequestMapping(value = "keysearch")
    public keyCom_out keyCom(@RequestBody keyCom_in keycom_in, HttpServletResponse response) throws SQLException{
        return service.keyCom(keycom_in);
    }

    //????????????
    @RequestMapping(value = "datesearch")
    public keyCom_out dateCom(@RequestBody dateCom_in datecom_in, HttpServletResponse response) throws SQLException{
        return service.dateCom(datecom_in);
    }

    //??????????????????
    @RequestMapping(value = "levelsearch")
    public keyCom_out levelCom(@RequestBody levelCom_in levelcom_in, HttpServletResponse response) throws SQLException{
        return service.levelCom(levelcom_in);
    }

    //????????????
    @RequestMapping(value = "majorsearch")
    public keyCom_out majorCom(@RequestBody majorCom_in majorcom_in, HttpServletResponse response) throws SQLException{
        return service.majorCom(majorcom_in);
    }

    //??????????????????
    @RequestMapping(value = "catesearch")
    public keyCom_out cateCom(@RequestBody cateCom_in catecom_in, HttpServletResponse response) throws SQLException{
        return service.cateCom(catecom_in);
    }

    //?????????????????? ???????????????
    @RequestMapping(value = "findall")
    public findallCom_out findallCom(@RequestBody findallCom_in findallcom_in, HttpServletResponse response) throws SQLException{
        return service.findallCom(findallcom_in);
    }

    //id??????
    @RequestMapping(value = "idsearch")
    public idCom_out idCom(@RequestBody Map<String,Object> param) throws SQLException {
        Integer com_id=Integer.parseInt(param.get("com_id").toString());
        return service.idCom(com_id);
    }

    //????????????
    @RequestMapping(value = "add")
    public addCom_out addCom(@RequestBody addCom_in addcom_in, HttpServletResponse response)throws SQLException{
        return service.addCom(addcom_in);
    }

    @RequestMapping(value = "delete")    //????????????
    public deleteCom_out deleteCom(@RequestBody deleteCom_in deletecom_in, HttpServletResponse response)throws SQLException{
        return service.deleteCom(deletecom_in);
    }

    @RequestMapping(value = "update") //??????
    public updateCom_out updateCom(@RequestBody updateCom_in updatecom_in, HttpServletResponse response)throws SQLException{
        return service.updateCom(updatecom_in);
    }

    //?????????id??????????????????
    @RequestMapping(value = "idsign")
    public idsignCom_out idsignCom(@RequestBody Map<String,Object> param, HttpServletResponse response) throws SQLException{
        Integer com_id=Integer.parseInt(param.get("com_id").toString());
        return service.idsignCom(com_id);
    }

    //?????????id??????????????????
    @RequestMapping(value = "idaward")
    public idwardCom_out idwardCom(@RequestBody Map<String,Object> param, HttpServletResponse response) throws SQLException{
        Integer com_id=Integer.parseInt(param.get("com_id").toString());
        return service.idwardCom(com_id);
    }

    //????????????????????????
    @RequestMapping(value = "con_nopass")
    public searchpassCom_out SearchConnopass(HttpServletResponse response)throws SQLException{
        return service.SearchConnopass();
    }

    //????????????????????????
    @RequestMapping(value = "con_pass")
    public searchpassCom_out SearchConpass(HttpServletResponse response)throws SQLException{
        return service.SearchConpass();
    }

    //????????????
    @RequestMapping(value = "control")
    public int Control(@RequestBody controlCom_in controlcom_in, HttpServletResponse response)throws SQLException{
        return service.Control(controlcom_in);
    }

    //???????????????????????????????????????
    @RequestMapping(value = "userfind_nopass")
    public searchpassCom_out searchNoPass(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.searchNoPass(user_id);
    }

    //???????????????????????????????????????
    @RequestMapping(value = "userfind_pass")
    public searchpassCom_out searchPass(@RequestBody Map<String,Object> param,HttpServletResponse response)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.searchPass(user_id);
    }


    //????????????
    @RequestMapping(value = "sign")
    public int sign(@RequestBody signCom_in signcom_in, HttpServletResponse response) throws SQLException{
        return service.sign(signcom_in);
    }
    //??????????????????????????????
    @RequestMapping(value = "middate")
    public middateCom_out middate(@RequestBody middateCom_in middatecom_in, HttpServletResponse response)throws SQLException{
        return service.middate(middatecom_in);
    }
    //????????????????????????????????????
    @RequestMapping(value="stu_nocomplete")
    public stunoCom_out stu_nocomplete(@RequestBody Map<String,Object> param) throws SQLException {
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.stu_nocomplete(user_id);
    }
    @RequestMapping(value="stu_complete")
    public stuCom_out stu_complete(@RequestBody Map<String,Object> param)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.stu_complete(user_id);
    }
    //????????????????????????
    @RequestMapping(value="signall")
    public int ExcelIn(HttpServletRequest req) throws SQLException {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) req);
        MultipartFile file=((MultipartHttpServletRequest) req).getFile("file");
        Integer com_id= Integer.valueOf(params.getParameter("com_id"));
        List<String> list=new ArrayList();
        Map<String,Object> res=new HashMap<>();
        List<reg> excelInfo= ReadRegExcelUtil.getExcelInfo(file);
        for(reg RegInfo : excelInfo){
            RegInfo.setCom_id(com_id);
            System.out.println(RegInfo);
            service.AddExcel(RegInfo);
        }
        if(list.size()>0){
            res.put("log",list);
        }
        return 666;
    }
    //????????????????????????????????????
    @RequestMapping(value="passcom")
    public int PassCom() throws SQLException {return service.PassCom();}
}
