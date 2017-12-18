package sk.akademiasovy.monikajassova.jedalnylistok.data.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * Created by monika.jassova on 11/30/2017.
 */

public class MealWithAddons {
    @Embedded
    public Meal meal;
    @Relation(parentColumn = "id", entityColumn = "id", entity = Addon.class)
    public List<Addon> addons;

//    @Relation(parentColumn = "id",
//                entityColumn = "mealId") public List<Addon> addonList;
}
