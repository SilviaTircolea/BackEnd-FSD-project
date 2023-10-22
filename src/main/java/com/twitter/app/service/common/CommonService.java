package com.twitter.app.service.common;



import java.sql.SQLException;
import java.util.Date;

public interface CommonService {

    Date getCurrentDate() throws SQLException;

    Date getCurrentTimestamp() throws SQLException;

}