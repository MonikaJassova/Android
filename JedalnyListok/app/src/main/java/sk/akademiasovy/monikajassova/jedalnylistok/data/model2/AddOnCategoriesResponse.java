package sk.akademiasovy.monikajassova.jedalnylistok.data.model2;

import java.util.List;

/**
 * Created by monika.jassova on 11/29/2017.
 */

public class AddOnCategoriesResponse {
    private int version;
    private List<AddOnCategory> addOnCategories;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<AddOnCategory> getAddOnCategories() {
        return addOnCategories;
    }

    public void setAddOnCategories(List<AddOnCategory> addOnCategories) {
        this.addOnCategories = addOnCategories;
    }
}
