package sk.akademiasovy.monikajassova.jedalnylistok.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

/**
 * Created by monika.jassova on 11/29/2017.
 */

@Entity(tableName = "mealcategories")
public class MealCategory {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    @Ignore
    private List<MealCategoryMeal> meals;
    @Ignore
    private Packaging packaging;
    @Ignore
    private String description;
    @Ignore
    private Integer displaySeq;

    @Ignore
    public MealCategory(){

    }

    public MealCategory(String id, String name){
        this.id = id;
        this.name = name;
    }

    @Ignore
    public MealCategory(String id, String name, List<MealCategoryMeal> meals, Packaging packaging, String description, Integer displaySeq) {
        this.id = id;
        this.name = name;
        this.meals = meals;
        this.packaging = packaging;
        this.description = description;
        this.displaySeq = displaySeq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MealCategoryMeal> getMeals() {
        return meals;
    }

    public void setMeals(List<MealCategoryMeal> meals) {
        this.meals = meals;
    }

    public Packaging getPackaging() {
        return packaging;
    }

    public void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDisplaySeq() {
        return displaySeq;
    }

    public void setDisplaySeq(Integer displaySeq) {
        this.displaySeq = displaySeq;
    }

}
