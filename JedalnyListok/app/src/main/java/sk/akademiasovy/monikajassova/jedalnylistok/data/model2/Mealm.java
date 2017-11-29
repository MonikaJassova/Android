package sk.akademiasovy.monikajassova.jedalnylistok.data.model2;

import java.util.List;

import sk.akademiasovy.monikajassova.jedalnylistok.data.model.CategoryMeal;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.ServingSize;

/**
 * Created by monika.jassova on 11/29/2017.
 */

public class Mealm {
    private String id;
    private String name;
    private CategoryMeal category;
    private ServingSize servingSize;
    private String description;
    private List<String> addOnIds;
    private Integer displaySeq;

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
