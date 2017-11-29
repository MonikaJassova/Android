package sk.akademiasovy.monikajassova.jedalnylistok.data.model2;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by monika.jassova on 11/29/2017.
 */

public class DBTypeConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
