package com.twitter.app.dao.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Date;

/*
 * @Author            : ROSHEN DILSHAN
 * @File Name         : CommonDAOImpl
 * @Created Date Time : 9/5/2019 10:21 AM
 */

@Repository("CommonDAO")
public class CommonDAOImpl implements CommonDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Date getCurrentDate() throws SQLException {
        return jdbcTemplate.queryForObject("SELECT CURRENT_DATE()", Date.class);
    }

    @Override
    public Date getCurrentTimestamp() throws SQLException {
        return jdbcTemplate.queryForObject("SELECT CURRENT_TIMESTAMP()", Date.class);
    }
}