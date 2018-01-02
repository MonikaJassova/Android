package sk.akademiasovy.monikajassova.jedalnylistok.data.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by monika.jassova on 11/29/2017.
 */

@Dao
public abstract class MealCategoryDAO {
    // Returns a list of all mealcategories in the database
    @Query("SELECT * FROM mealcategories")
    public abstract LiveData<List<MealCategory>> getAll();

    @Query("SELECT id FROM mealcategories")
    public abstract List<String> getMCIds();

    @Query("SELECT * FROM mealcategories WHERE id LIKE :id")
    abstract MealCategory findById(String id);

    // Inserts multiple mealcategories
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void bulkInsert(List<MealCategory> mealCategories);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(MealCategory mealCategory);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertMealList(List<MealCategoryMeal> meals);

    @Query("SELECT * FROM mealcategories WHERE id LIKE :id")
    abstract MealCategory getMealCategory(String id);

    @Query("SELECT * FROM mcmeals WHERE categoryId LIKE :categoryId")
    abstract List<MealCategoryMeal> getMealList(String categoryId);

    @Query("DELETE FROM mealcategories")
    public abstract void deleteOldMealCategories();

    @Query("SELECT count(id) FROM mealcategories")
    public abstract int rowCount();

    public void insertMealCategoryWithMeals(MealCategory mealCategory){
        List<MealCategoryMeal> meals = mealCategory.getMeals();
        for (int i = 0; i < meals.size(); i++){
            meals.get(i).setCategoryId(mealCategory.getId());
        }
        insert(mealCategory);
        insertMealList(meals);
    }

    public MealCategory getMealCategoryWithMeals(String id){
        MealCategory mealCategory = getMealCategory(id);
        List<MealCategoryMeal> meals = getMealList(id);
        mealCategory.setMeals(meals);
        return mealCategory;
    }

    public LiveData<List<MealCategory>> getAllWithMeals(){
        List<MealCategory> list = new ArrayList<>();
        for (String id : getMCIds()){
            list.add(getMealCategoryWithMeals(id));
        }
        return null;
    }

}
