package sk.akademiasovy.monikajassova.jedalnylistok.data.model2;

import sk.akademiasovy.monikajassova.jedalnylistok.data.model.SelectionOption;

/**
 * Created by monika.jassova on 11/29/2017.
 */

public class AddOnCategory {
    private String id;
    private String name;
    private String description;
    private Integer displaySeq;
    private SelectionOption selectionOption;

    public AddOnCategory(String id, String name, String description, Integer displaySeq, SelectionOption selectionOption) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.displaySeq = displaySeq;
        this.selectionOption = selectionOption;
    }

    public AddOnCategory(){

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

    public SelectionOption getSelectionOption() {
        return selectionOption;
    }

    public void setSelectionOption(SelectionOption selectionOption) {
        this.selectionOption = selectionOption;
    }
}
