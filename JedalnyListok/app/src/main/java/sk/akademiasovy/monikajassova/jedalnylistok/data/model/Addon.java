package sk.akademiasovy.monikajassova.jedalnylistok.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.AddOnCategory;

/**
 * Created by monika.jassova on 11/28/2017.
 */

@Entity(tableName = "addons", foreignKeys = @ForeignKey(entity = AddOnCategory.class, parentColumns = "id", childColumns = "categoryId"))
public class Addon {
    @PrimaryKey
    @NonNull
    private String id;
    @Ignore
    private CategoryAddon category;
    private String name;
    private String categoryId;
    @Ignore
    private ServingSize servingSize;
    @Ignore
    private String description;
    @Ignore
    private Integer displaySeq;

    public Addon(String id, String name) {
        this.id = id;
        this.name = name;
        this.categoryId = category.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public CategoryAddon getCategory() {
        return category;
    }

    public void setCategory(final CategoryAddon category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
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

    public Integer getDisplaySeq() {
        return displaySeq;
    }

    public void setDisplaySeq(final Integer displaySeq) {
        this.displaySeq = displaySeq;
    }
}
