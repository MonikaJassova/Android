package sk.akademiasovy.monikajassova.jedalnylistok.data.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by monika.jassova on 11/29/2017.
 */

@Dao
public interface MealDAO {
    @Query("SELECT * FROM meals")
    List<Meal> getAll();

    @Query("SELECT * FROM meals WHERE id LIKE :id")
    Meal findById(String id);

    @Query("SELECT * FROM meals WHERE categoryId LIKE :id")
    List<Meal> findByCategoryId(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Meal... meals);
}
