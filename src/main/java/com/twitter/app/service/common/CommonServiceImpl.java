package com.twitter.app.service.common;

import com.twitter.app.dao.common.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Date;

@Repository("CommonService")
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public Date getCurrentDate() throws SQLException {
        return commonDAO.getCurrentDate();
    }

    @Override
    public Date getCurrentTimestamp() throws SQLException {
        return commonDAO.getCurrentTimestamp();
    }
}