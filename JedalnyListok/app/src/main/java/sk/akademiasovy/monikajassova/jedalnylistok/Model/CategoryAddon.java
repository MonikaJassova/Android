package sk.akademiasovy.monikajassova.jedalnylistok.Model;

/**
 * Created by monika.jassova on 11/28/2017.
 */

public class CategoryAddon extends Category{
    private SelectionOption selectionOption;

    public SelectionOption getSelectionOption() {
        return selectionOption;
    }

    public void setSelectionOption(final SelectionOption selectionOption) {
        this.selectionOption = selectionOption;
    }
}
