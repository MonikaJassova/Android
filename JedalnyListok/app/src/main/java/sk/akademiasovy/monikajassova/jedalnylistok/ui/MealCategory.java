package sk.akademiasovy.monikajassova.jedalnylistok.ui;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import sk.akademiasovy.monikajassova.jedalnylistok.data.model.Meal;

/**
 * Created by monika.jassova on 12/1/2017.
 */

public class MealCategory extends ExpandableGroup<Meal> {
    public MealCategory(String title, List<Meal> items) {
        super(title, items);
    }
}
