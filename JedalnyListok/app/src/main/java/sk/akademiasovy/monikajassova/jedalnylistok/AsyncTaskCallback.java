package sk.akademiasovy.monikajassova.jedalnylistok;

import sk.akademiasovy.monikajassova.jedalnylistok.Model.Meals;

/**
 * Created by monika.jassova on 11/28/2017.
 */

interface AsyncTaskCallback {
    void onTaskCompleted(Meals result);
}
