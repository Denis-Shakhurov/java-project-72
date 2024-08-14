package hexlet.code.util;

import java.time.LocalDateTime;

public class UtilsDateTime {

    public static String dateFormatter(LocalDateTime lcd) {
        String date = lcd.toLocalDate().toString();
        String time = lcd.toLocalTime().toString().substring(0, 5);
        return date + " " + time;
    }
}
