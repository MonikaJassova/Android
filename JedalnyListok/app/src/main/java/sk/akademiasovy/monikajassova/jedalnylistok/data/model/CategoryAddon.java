package sk.akademiasovy.monikajassova.jedalnylistok.data.model;

/**
 * Created by monika.jassova on 12/18/2017.
 */

public class CategoryAddon {
    private String id;
    private String name;
    private String description;
    private SelectionOption selectionOption;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SelectionOption getSelectionOption() {
        return selectionOption;
    }

    public void setSelectionOption(SelectionOption selectionOption) {
        this.selectionOption = selectionOption;
    }

    public Integer getDisplaySeq() {
        return displaySeq;
    }

    public void setDisplaySeq(Integer displaySeq) {
        this.displaySeq = displaySeq;
    }
}
