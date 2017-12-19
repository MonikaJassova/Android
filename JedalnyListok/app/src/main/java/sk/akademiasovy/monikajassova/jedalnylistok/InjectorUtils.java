package sk.akademiasovy.monikajassova.jedalnylistok;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;

import sk.akademiasovy.monikajassova.jedalnylistok.data.AppDatabase;
import sk.akademiasovy.monikajassova.jedalnylistok.data.AppRepository;
import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.NetworkDataSource;
import sk.akademiasovy.monikajassova.jedalnylistok.ui.DetailViewModelFactory;
import sk.akademiasovy.monikajassova.jedalnylistok.ui.MainViewModelFactory;

/**
 * Created by monika.jassova on 12/19/2017.
 */

public class InjectorUtils {
    public static AppRepository provideRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        NetworkDataSource networkDataSource = NetworkDataSource.getInstance(context.getApplicationContext(), executors);
        return AppRepository.getInstance(database.mealCategoryDAO(), database.mealDAO(),
                database.addonDAO(), database.addOnCategoryDAO(), networkDataSource, executors);
    }

    public static NetworkDataSource provideNetworkDataSource(Context context) {
        // This call to provide repository is necessary if the app starts from a service - in this
        // case the repository will not exist unless it is specifically created.
        provideRepository(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        return NetworkDataSource.getInstance(context.getApplicationContext(), executors);
    }

    public static DetailViewModelFactory provideDetailViewModelFactory(Context context, Date date) {
        AppRepository repository = provideRepository(context.getApplicationContext());
        return new DetailViewModelFactory(repository);
    }

    public static MainViewModelFactory provideMainActivityViewModelFactory(Context context) {
        AppRepository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(repository);
    }
}
