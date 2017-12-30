package sk.akademiasovy.monikajassova.jedalnylistok.data.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by monika.jassova on 11/29/2017.
 */

@Dao
public interface MealCategoryDAO {
    // Returns a list of all mealcategories in the database
    @Query("SELECT * FROM mealcategories")
    LiveData<List<MealCategory>> getAll();

    @Query("SELECT * FROM mealcategories WHERE id LIKE :id")
    MealCategory findById(String id);

    // Inserts multiple mealcategories
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(List<MealCategory> mealCategories);

    @Query("SELECT COUNT(id) FROM mealcategories WHERE date >= :date")
    int countAllFutureWeather(Date date);

    @Query("DELETE FROM mealcategories WHERE date < :date")
    void deleteOldMealCategories(Date date);
}
