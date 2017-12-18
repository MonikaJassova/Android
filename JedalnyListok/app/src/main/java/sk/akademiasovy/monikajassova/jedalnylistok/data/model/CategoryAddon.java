package sk.akademiasovy.monikajassova.jedalnylistok.data.model;

import android.arch.persistence.room.Ignore;

/**
 * Created by monika.jassova on 12/18/2017.
 */

public class CategoryAddon {
    private String id;
    @Ignore
    private String name;
    @Ignore
    private String description;
    @Ignore
    private SelectionOption selectionOption;
    @Ignore
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
