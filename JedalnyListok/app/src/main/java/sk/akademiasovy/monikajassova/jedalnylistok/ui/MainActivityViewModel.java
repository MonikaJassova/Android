package sk.akademiasovy.monikajassova.jedalnylistok.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import sk.akademiasovy.monikajassova.jedalnylistok.data.AppRepository;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.*;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategory;

/**
 * Created by monika.jassova on 12/19/2017.
 */

public class MainActivityViewModel extends ViewModel {
    private final AppRepository mRepository;
    private final LiveData<List<sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategory>> mMealCategories;

    public MainActivityViewModel(AppRepository repository) {
        mRepository = repository;
        mMealCategories = mRepository.getMealCategories();
    }

    public LiveData<List<sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategory>> getMealCategories() {
        return mMealCategories;
    }

}
