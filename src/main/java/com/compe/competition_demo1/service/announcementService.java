package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.announcement;

import java.sql.SQLException;
import java.util.List;

public interface announcementService {
    void addAnnounce(announcement ann);
    void deleteAnnounce(int announcement_id);
    void updateAnnounce(announcement ann);
    List<announcement> announceFindAll() throws SQLException;
}
