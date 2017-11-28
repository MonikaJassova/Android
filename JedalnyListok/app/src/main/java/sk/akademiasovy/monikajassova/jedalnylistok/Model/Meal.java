package sk.akademiasovy.monikajassova.jedalnylistok.Model;

import java.util.List;

/**
 * Created by monika.jassova on 11/28/2017.
 */

public class Meal {
    private String id;
    private String name;
    private Object photo;
    private CategoryMeal category;
    private ServingSize servingSize;
    private String description;
    private List<String> addOnIds;
    private Integer displaySeq;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(final Object photo) {
        this.photo = photo;
    }

    public CategoryMeal getCategory() {
        return category;
    }

    public void setCategory(final CategoryMeal category) {
        this.category = category;
    }

    public ServingSize getServingSize() {
        return servingSize;
    }

    public void setServingSize(final ServingSize servingSize) {
        this.servingSize = servingSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<String> getAddOnIds() {
        return addOnIds;
    }

    public void setAddOnIds(final List<String> addOnIds) {
        this.addOnIds = addOnIds;
    }

    public Integer getDisplaySeq() {
        return displaySeq;
    }

    public void setDisplaySeq(final Integer displaySeq) {
        this.displaySeq = displaySeq;
    }
}
