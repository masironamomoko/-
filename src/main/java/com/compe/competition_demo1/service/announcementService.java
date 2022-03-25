package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.*;

import java.util.Date;
import java.sql.SQLException;
import java.util.List;

public interface announcementService {
    int addAnnounce(inform_add_in ann);
    int deleteAnnounce(String inform_id);
    int updateAnnounce(inform_update_in ann);
    inform_list_out announceFindAll(int pageNum, int pageSize) throws SQLException;
    List<announcement> informInquire() throws SQLException;
    inform_list_out informKeySearch(int pageNum, int pageSize, String key) throws SQLException;
    inform_list_out informDateSearch(int pageNum, int pageSize, String date) throws SQLException;
    inform_id_out informIdSearch(String id) throws SQLException;
    List<announcement> InformNoPassSearch(String user_id) throws SQLException;
    List<announcement> InformPassSearch(String user_id) throws SQLException;
    List<announcement> InformConnopassSearch()throws SQLException;
    List<announcement> InformConpassSearch()throws SQLException;
    int InformControl(int inform_id,int check);
}
