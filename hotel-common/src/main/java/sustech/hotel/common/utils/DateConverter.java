package sustech.hotel.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static java.sql.Date convertStringToDate(String date) throws ParseException {
        java.util.Date temp = simpleDateFormat.parse(date);
        return new java.sql.Date(temp.getTime());
    }
}
