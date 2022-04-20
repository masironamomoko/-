package com.compe.competition_demo1.controller;

import com.compe.competition_demo1.service.SQLmakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@RestController
@RequestMapping("/data")

public class SQLmakerController {
    @Autowired
    @Resource
    private SQLmakerService service;

    //创建所有数据
    @RequestMapping (value = "createall")
    public void createall(HttpServletResponse response)throws SQLException {
        service.createall();
    }

    //清除所有数据
    @RequestMapping(value = "deleteall")
    public void deleteall(HttpServletResponse response)throws SQLException {
        service.deleteall();
    }
}
