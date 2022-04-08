package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.*;
import com.compe.competition_demo1.cdata.inform_io.*;

import java.sql.SQLException;
import java.util.List;

public interface announcementService {
    int addAnnounce(inform_add_in ann);
    int deleteAnnounce(Integer inform_id);
    int updateAnnounce(inform_update_in ann);
    inform_list_out announceFindAll(inform_findall_in inform) throws SQLException;
    List<announcement> informInquire() throws SQLException;
    inform_list_out informKeySearch(inform_key_in informKeyIn) throws SQLException;
    inform_list_out informDateSearch(inform_date_in informDateIn) throws SQLException;
    inform_id_out informIdSearch(Integer id) throws SQLException;
    List<announcement> InformNoPassSearch(Integer user_id) throws SQLException;
    List<announcement> InformPassSearch(Integer user_id) throws SQLException;
    List<announcement> InformConnopassSearch()throws SQLException;
    List<announcement> InformConpassSearch()throws SQLException;
    int InformControl(inform_check_in informCheckIn);
}
