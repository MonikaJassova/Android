package sk.akademiasovy.monikajassova.jedalnylistok.data.model2;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

import sk.akademiasovy.monikajassova.jedalnylistok.data.model.CategoryMeal;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.ServingSize;

/**
 * Created by monika.jassova on 11/29/2017.
 */

@Entity(tableName = "meals", foreignKeys = @ForeignKey(entity = MealCategory.class, parentColumns = "id", childColumns = "categoryId"))
public class Mealm {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String categoryId;
    @Ignore
    private CategoryMeal category;
    @Embedded
    private ServingSize servingSize;
    @Ignore
    private String description;
    @Ignore
    private List<String> addOnIds;
    @Ignore
    private Integer displaySeq;

    public Mealm(){

    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Mealm (String id, String name, ServingSize servingSize){
        this.id = id;
        this.name = name;
        this.servingSize = servingSize;
        this.categoryId = category.getId();
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

    public CategoryMeal getCategory() {
        return category;
    }

    public void setCategory(CategoryMeal category) {
        this.category = category;
    }

    public ServingSize getServingSize() {
        return servingSize;
    }

    public void setServingSize(ServingSize servingSize) {
        this.servingSize = servingSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAddOnIds() {
        return addOnIds;
    }

    public void setAddOnIds(List<String> addOnIds) {
        this.addOnIds = addOnIds;
    }

    public Integer getDisplaySeq() {
        return displaySeq;
    }

    public void setDisplaySeq(Integer displaySeq) {
        this.displaySeq = displaySeq;
    }
}
