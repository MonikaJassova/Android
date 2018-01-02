package sk.akademiasovy.monikajassova.jedalnylistok.data.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by monika.jassova on 1/1/2018.
 */

@Dao
public interface MealCategoryMealDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(List<MealCategoryMeal> meals);

    @Update
    void update(MealCategoryMeal... meals);

    @Query("DELETE FROM MCmeals")
    void deleteOldMeals();

    @Query("SELECT * FROM MCmeals")
    List<MealCategoryMeal> getAllMeals();

    @Query("SELECT * FROM MCmeals WHERE categoryId=:categoryId")
    List<MealCategoryMeal> getMealsForCategory(final String categoryId);
}
