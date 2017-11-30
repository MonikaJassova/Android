package sk.akademiasovy.monikajassova.jedalnylistok.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.AddOnCategory;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.AddOnCategoryDAO;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.DBTypeConverter;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.MealCategory;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.MealCategoryDAO;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.MealWithAddonsDAO;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.Mealm;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.MealmDAO;

/**
 * Created by monika.jassova on 11/29/2017.
 */

@Database(entities = {MealCategory.class, Mealm.class, AddOnCategory.class}, version = 1) //Entities listed here
@TypeConverters(DBTypeConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MealCategoryDAO mealCategoryDAO();
    public abstract MealmDAO mealmDAO();
    public abstract AddOnCategoryDAO addOnCategoryDAO();
    public abstract MealWithAddonsDAO mealWithAddonsDAO();


    private static final String DATABASE_NAME = "jedalnylistok";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static volatile AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, AppDatabase.DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }

}
