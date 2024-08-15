package hexlet.code.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class UtilsDateTime {

    public static String dateFormatter(Timestamp timestamp) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(timestamp);
    }
}
