package sk.akademiasovy.monikajassova.jedalnylistok.Model;

/**
 * Created by monika.jassova on 11/28/2017.
 */

public class Category {
    private String id;
    private String name;
    private String description;
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
