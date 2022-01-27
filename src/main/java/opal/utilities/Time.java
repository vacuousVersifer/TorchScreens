package opal.utilities;

import java.time.*;
import java.util.Date;

public class Time {
    private static final float timeStarted = System.nanoTime();

    private static final Date date = new Date();
    private static final LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


    public static float getTime() {
        return (float) ((System.nanoTime() - timeStarted) * 1E-9);
    }

    public static int getYear() {
        return localDate.getYear();
    }

    public static int getMonth() {
        return localDate.getMonthValue();
    }
}
