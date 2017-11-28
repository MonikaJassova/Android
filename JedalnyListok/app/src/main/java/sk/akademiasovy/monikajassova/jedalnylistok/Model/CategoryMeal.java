package sk.akademiasovy.monikajassova.jedalnylistok.Model;

/**
 * Created by monika.jassova on 11/28/2017.
 */

public class CategoryMeal extends Category {
    private Packaging packaging;

    public Packaging getPackaging() {
        return packaging;
    }

    public void setPackaging(final Packaging packaging) {
        this.packaging = packaging;
    }
}
