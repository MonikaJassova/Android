package sk.akademiasovy.monikajassova.jedalnylistok.data.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by monika.jassova on 11/30/2017.
 */

@Dao
public interface MealWithAddonsDAO {
    @Query("SELECT * FROM addons")
    public List<MealWithAddons> getMealsWithAddons();
}
