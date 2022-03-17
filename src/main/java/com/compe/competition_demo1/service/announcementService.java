package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.announcement;

import java.util.Date;
import java.sql.SQLException;
import java.util.List;

public interface announcementService {
    int informTotalDate(String date);
    int informTotalKey(String key);
    int addAnnounce(announcement ann);
    int deleteAnnounce(String inform_id);
    int updateAnnounce(announcement ann);
    List<announcement> announceFindAll(int pageNum,int pageSize) throws SQLException;
    List<announcement> informInquire() throws SQLException;
    List<announcement> informKeySearch(int pageNum, int pageSize, String key) throws SQLException;
    List<announcement> informDateSearch(int pageNum, int pageSize, String date) throws SQLException;
    announcement informIdSearch(String id) throws SQLException;
    List<announcement> InformNoPassSearch(String user_id) throws SQLException;
    List<announcement> InformPassSearch(String user_id) throws SQLException;
    List<announcement> InformConnopassSearch()throws SQLException;
    List<announcement> InformConpassSearch()throws SQLException;
    int InformControl(int inform_id,int check);
}
