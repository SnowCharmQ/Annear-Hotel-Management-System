package sustech.hotel.common.utils;

import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.others.InvalidDateException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static java.sql.Date convertStringToDate(String date) {
        try {
            java.util.Date temp = simpleDateFormat.parse(date);
            return new java.sql.Date(temp.getTime());
        } catch (ParseException e) {
            throw new InvalidDateException(ExceptionCodeEnum.INVALID_DATE_EXCEPTION);
        }
    }

    public static java.sql.Date currentDate() {
        try {
            return new Date(simpleDateFormat.parse(simpleDateFormat.format(new java.util.Date().getTime())).getTime());
        } catch (ParseException e) {
            throw new InvalidDateException(ExceptionCodeEnum.INVALID_DATE_EXCEPTION);
        }
    }
}
