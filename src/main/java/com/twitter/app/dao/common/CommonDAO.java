package com.twitter.app.dao.common;

/*
 * @Author            : ROSHEN DILSHAN
 * @File Name         : CommonDAO
 * @Created Date Time : 9/5/2019 10:21 AM
 */

import java.sql.SQLException;
import java.util.Date;

public interface CommonDAO {

    Date getCurrentDate() throws SQLException;

    Date getCurrentTimestamp() throws SQLException;

}