package sk.akademiasovy.monikajassova.jedalnylistok.data.model;

/**
 * Created by monika.jassova on 12/18/2017.
 */

public class CategoryMeal {
    private String id;
    private String name;
    private Packaging packaging;
    private String description;
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
