package com.twitter.app.utill;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Common {

    private static final Logger log = LoggerFactory.getLogger(Common.class);

    private static final Integer randomLength = 20;

    public static String getAlphaNumericString() {
        Integer length = randomLength;
        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString = new String(array, StandardCharsets.UTF_8);

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < randomString.length(); k++) {

            char ch = randomString.charAt(k);

            if (((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9'))
                    && (length > 0)) {

                r.append(ch);
                length--;
            }
        }

        // return the resultant string
        return r.toString();
    }


    public static String getModelToString(Object o) throws JsonProcessingException {
        String content = new ObjectMapper().writeValueAsString(o);
        JSONObject jsonContent = new JSONObject();
        if (content.charAt(0) == '[') {
            JSONArray jsonArray = new JSONArray(content);
            jsonContent.put("obj", jsonArray.toString());
        } else {
            JSONObject jsonObject = new JSONObject(content);
            jsonObject.remove("password");
            jsonObject.remove("jwt");
            jsonContent.put("obj", jsonObject.toString());
        }
        content = (jsonContent.toString()).replace("\"", "").replace("\\", "");
        return content;
    }


    public static Date getStartingTimeOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        date = calendar.getTime();
        return date;
    }

    public static Date getEndingTimeOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        date = calendar.getTime();
        return date;
    }

    public static Date getStartingDateTimeOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        date = calendar.getTime();
        return date;
    }

    public static Date getEndingDateTimeOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        date = calendar.getTime();
        return date;
    }

    public static String getStringFormatDate(String format, Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static Date getDateFromString(String format, String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(date);
    }

    public static void setAllBorderToPoiStyle(CellStyle cellStyle) {
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
    }

    public static String formatToCurrency(BigDecimal bigDecimal) {
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");

        return bigDecimal.compareTo(BigDecimal.ZERO) == 0 ? "0.00" : decimalFormat.format(bigDecimal);
    }

}
