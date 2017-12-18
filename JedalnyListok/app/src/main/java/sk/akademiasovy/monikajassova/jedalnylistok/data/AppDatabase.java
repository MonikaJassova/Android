package sk.akademiasovy.monikajassova.jedalnylistok.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import sk.akademiasovy.monikajassova.jedalnylistok.data.model.AddOnCategory;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.AddOnCategoryDAO;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.Addon;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.AddonDAO;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.DBTypeConverter;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategory;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategoryDAO;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealWithAddonsDAO;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.Meal;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealDAO;

/**
 * Created by monika.jassova on 11/29/2017.
 */

@Database(entities = {MealCategory.class, Meal.class, AddOnCategory.class, Addon.class}, version = 1) //Entities listed here
@TypeConverters(DBTypeConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MealCategoryDAO mealCategoryDAO();
    public abstract MealDAO mealDAO();
    public abstract AddOnCategoryDAO addOnCategoryDAO();
    public abstract AddonDAO addonDAO();
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
                            AppDatabase.class, AppDatabase.DATABASE_NAME).allowMainThreadQueries().build();
                }
            }
        }
        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

}
