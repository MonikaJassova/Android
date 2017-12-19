package sk.akademiasovy.monikajassova.jedalnylistok.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import sk.akademiasovy.monikajassova.jedalnylistok.data.AppRepository;

/**
 * Created by monika.jassova on 12/19/2017.
 */

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final AppRepository mRepository;

    public MainViewModelFactory(AppRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainActivityViewModel(mRepository);
    }
}
