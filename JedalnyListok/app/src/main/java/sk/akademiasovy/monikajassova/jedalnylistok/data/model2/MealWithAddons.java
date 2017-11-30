package sk.akademiasovy.monikajassova.jedalnylistok.data.model2;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

import sk.akademiasovy.monikajassova.jedalnylistok.data.model.Addon;

/**
 * Created by monika.jassova on 11/30/2017.
 */

public class MealWithAddons {
    @Embedded
    public Mealm meal;

//    @Relation(parentColumn = "id",
//                entityColumn = "mealId") public List<Addon> addonList;
}
