package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.cdata.competitionsth.Competition;
import com.compe.competition_demo1.cdata.competitionsth.cate.cateCom_in;
import com.compe.competition_demo1.cdata.competitionsth.control.controlCom_in;
import com.compe.competition_demo1.cdata.competitionsth.findall.findallCom_in;
import com.compe.competition_demo1.cdata.competitionsth.id.idCom_out;
import com.compe.competition_demo1.cdata.competitionsth.idsign.idsignCom_out;
import com.compe.competition_demo1.cdata.competitionsth.idward.idwardCom_out;
import com.compe.competition_demo1.cdata.competitionsth.level.levelCom_in;
import com.compe.competition_demo1.cdata.competitionsth.middate.middateCom_in;
import com.compe.competition_demo1.cdata.competitionsth.middate.middateCom_out;
import com.compe.competition_demo1.cdata.competitionsth.sign.signCom_in;
import com.compe.competition_demo1.service.CompetitionService;
import com.compe.competition_demo1.cdata.competitionsth.add.addCom_in;
import com.compe.competition_demo1.cdata.competitionsth.add.addCom_out;
import com.compe.competition_demo1.cdata.competitionsth.date.dateCom_in;
import com.compe.competition_demo1.cdata.competitionsth.delete.deleteCom_in;
import com.compe.competition_demo1.cdata.competitionsth.delete.deleteCom_out;
import com.compe.competition_demo1.cdata.competitionsth.findall.findallCom_out;
import com.compe.competition_demo1.cdata.competitionsth.hot.hotCom_out;
import com.compe.competition_demo1.cdata.competitionsth.key.keyCom_in;
import com.compe.competition_demo1.cdata.competitionsth.keyCom_out;
import com.compe.competition_demo1.cdata.competitionsth.major.majorCom_in;
import com.compe.competition_demo1.cdata.competitionsth.searchpass.searchpassCom_out;
import com.compe.competition_demo1.cdata.competitionsth.update.updateCom_in;
import com.compe.competition_demo1.cdata.competitionsth.update.updateCom_out;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/com")

public class CompetitionController {
    @Autowired
    @Resource
    private CompetitionService service;

    //热点竞赛
    @RequestMapping(value = "hot")
    public hotCom_out hotCom(HttpServletResponse response)throws SQLException{
        return service.Comhot();
    }

    //关键词搜索
    @RequestMapping(value = "keysearch")
    public keyCom_out keyCom(@RequestBody keyCom_in keycom_in, HttpServletResponse response) throws SQLException{
        return service.keyCom(keycom_in);
    }

    //日期搜索
    @RequestMapping(value = "datesearch")
    public keyCom_out dateCom(@RequestBody dateCom_in datecom_in, HttpServletResponse response) throws SQLException{
        return service.dateCom(datecom_in);
    }

    //赛事级别搜索
    @RequestMapping(value = "levelsearch")
    public keyCom_out levelCom(@RequestBody levelCom_in levelcom_in, HttpServletResponse response) throws SQLException{
        return service.levelCom(levelcom_in);
    }

    //专业搜索
    @RequestMapping(value = "majorsearch")
    public keyCom_out majorCom(@RequestBody majorCom_in majorcom_in, HttpServletResponse response) throws SQLException{
        return service.majorCom(majorcom_in);
    }

    //赛事类别搜索
    @RequestMapping(value = "catesearch")
    public keyCom_out cateCom(@RequestBody cateCom_in catecom_in, HttpServletResponse response) throws SQLException{
        return service.cateCom(catecom_in);
    }

    //竞赛分页展示 按时间排序
    @RequestMapping(value = "findall")
    public findallCom_out findallCom(@RequestBody findallCom_in findallcom_in, HttpServletResponse response) throws SQLException{
        return service.findallCom(findallcom_in);
    }

    //id搜索
    @RequestMapping(value = "idsearch")
    public idCom_out idCom(@RequestBody Map<String,Object> param) throws SQLException {
        Integer com_id=Integer.parseInt(param.get("com_id").toString());
        return service.idCom(com_id);
    }

    //发布竞赛
    @PostMapping(value = "add")
    public addCom_out addCom(@RequestBody addCom_in addcom_in, HttpServletResponse response)throws SQLException{
        return service.addCom(addcom_in);
    }

    @GetMapping(value = "delete")    //删除竞赛
    public deleteCom_out deleteCom(@RequestBody deleteCom_in deletecom_in, HttpServletResponse response)throws SQLException{
        return service.deleteCom(deletecom_in);
    }

    @RequestMapping(value = "update") //修改
    public updateCom_out updateCom(@RequestBody updateCom_in updatecom_in, HttpServletResponse response)throws SQLException{
        return service.updateCom(updatecom_in);
    }

    //用竞赛id查询报名信息
    @RequestMapping(value = "idsign")
    public idsignCom_out idsignCom(@RequestBody Map<String,Object> param, HttpServletResponse response) throws SQLException{
        Integer com_id=Integer.parseInt(param.get("com_id").toString());
        return service.idsignCom(com_id);
    }

    //用竞赛id查询获奖信息
    @RequestMapping(value = "idward")
    public idwardCom_out idwardCom(@RequestBody Map<String,Object> param, HttpServletResponse response) throws SQLException{
        Integer com_id=Integer.parseInt(param.get("com_id").toString());
        return service.idwardCom(com_id);
    }

    //管理员未审核竞赛
    @RequestMapping(value = "con_nopass")
    public searchpassCom_out SearchConnopass(HttpServletResponse response)throws SQLException{
        return service.SearchConnopass();
    }

    //管理员已审核竞赛
    @RequestMapping(value = "con_pass")
    public searchpassCom_out SearchConpass(HttpServletResponse response)throws SQLException{
        return service.SearchConpass();
    }

    //审核竞赛
    @RequestMapping(value = "control")
    public int Control(@RequestBody controlCom_in controlcom_in, HttpServletResponse response)throws SQLException{
        return service.Control(controlcom_in);
    }

    //查询负责人发布的未审核竞赛
    @RequestMapping(value = "userfind_nopass")
    public searchpassCom_out searchNoPass(@RequestBody Map<String,Object> param, HttpServletResponse response)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.searchNoPass(user_id);
    }

    //查询负责人发布的已审核竞赛
    @RequestMapping(value = "userfind_pass")
    public searchpassCom_out searchPass(@RequestBody Map<String,Object> param,HttpServletResponse response)throws SQLException{
        Integer user_id=Integer.parseInt(param.get("user_id").toString());
        return service.searchPass(user_id);
    }

    //批量导入报名信息

    //用户报名
    @RequestMapping(value = "sign")
    public int sign(@RequestBody signCom_in signcom_in, HttpServletResponse response) throws SQLException{
        return service.sign(signcom_in);
    }
    //根据时间范围返回竞赛
    @RequestMapping(value = "middate")
    public middateCom_out middate(@RequestBody middateCom_in middatecom_in, HttpServletResponse response)throws SQLException{
        return service.middate(middatecom_in);
    }
    //查询学生报名的未完成竞赛
    @RequestMapping(value="stu_nocomplete")
    public List<Competition> stu_nocomplete(@RequestBody Map<String,Object> param) throws SQLException {
        Integer id=Integer.parseInt(param.get("user_id").toString());
        return service.stu_nocomplete(id);
    }
    @RequestMapping(value="stu_complete")
    public List<Competition> stu_complete(@RequestBody Map<String,Object> param)throws SQLException{
        Integer id=Integer.parseInt(param.get("user_id").toString());
        return service.stu_complete(id);
    }
}
