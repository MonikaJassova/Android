package sk.akademiasovy.monikajassova.jedalnylistok.data.model2;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import sk.akademiasovy.monikajassova.jedalnylistok.data.model.SelectionOption;

/**
 * Created by monika.jassova on 11/29/2017.
 */

@Entity(tableName = "addoncategories")
public class AddOnCategory {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    @Ignore
    private String description;
    @Ignore
    private Integer displaySeq;
    @Ignore
    private SelectionOption selectionOption;

    public AddOnCategory(String id, String name){
        this.id = id;
        this.name = name;
    }

    @Ignore
    public AddOnCategory(String id, String name, String description, Integer displaySeq, SelectionOption selectionOption) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.displaySeq = displaySeq;
        this.selectionOption = selectionOption;
    }

    @Ignore
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
