package sk.akademiasovy.monikajassova.jedalnylistok.Model;

/**
 * Created by monika.jassova on 11/28/2017.
 */

public class Addon {
    private String id;
    private Category category;
    private String name;
    private ServingSize servingSize;
    private String description;
    private Integer displaySeq;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
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
