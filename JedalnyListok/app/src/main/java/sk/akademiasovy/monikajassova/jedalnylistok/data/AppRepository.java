package sk.akademiasovy.monikajassova.jedalnylistok.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

import sk.akademiasovy.monikajassova.jedalnylistok.AppExecutors;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.AddOnCategoryDAO;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.AddonDAO;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategory;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategoryDAO;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategoryMealDAO;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealDAO;
import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.NetworkDataSource;

/**
 * Created by monika.jassova on 12/19/2017.
 */

public class AppRepository {
    private static final String LOG_TAG = AppRepository.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static AppRepository sInstance;
    private final MealCategoryDAO mealCategoryDAO;
    private final MealCategoryMealDAO mealCategoryMealDAO;
    private final MealDAO mealDAO;
    private final AddonDAO addonDAO;
    private final AddOnCategoryDAO addOnCategoryDAO;
    private final NetworkDataSource networkDataSource;
    private final AppExecutors mExecutors;
    private boolean mInitialized = false;

    private AppRepository(MealCategoryDAO mealCategoryDAO,
                               MealCategoryMealDAO mealCategoryMealDAO,
                               MealDAO mealDAO,
                               AddonDAO addonDAO,
                               AddOnCategoryDAO addOnCategoryDAO,
                               NetworkDataSource networkDataSource,
                               AppExecutors executors) {
        this.mealCategoryDAO = mealCategoryDAO;
        this.mealCategoryMealDAO = mealCategoryMealDAO;
        this.addOnCategoryDAO = addOnCategoryDAO;
        this.mealDAO = mealDAO;
        this.addonDAO = addonDAO;
        this.networkDataSource = networkDataSource;
        mExecutors = executors;

        // As long as the repository exists, observe the network LiveData.
        // If that LiveData changes, update the database.
        LiveData<List<MealCategory>> networkData = networkDataSource.getCurrentMCs();
        networkData.observeForever(newMScFromNetwork -> {
            mExecutors.diskIO().execute(() -> {
                // Deletes old historical data
                deleteOldData();
                Log.d(LOG_TAG, "Old JL deleted");

                // Insert our new data into database
                //mealCategoryDAO.bulkInsert(newMScFromNetwork);
                for (MealCategory mc : newMScFromNetwork){
                    mealCategoryDAO.insertMealCategoryWithMeals(mc);
                }
                Log.d(LOG_TAG, "New values inserted");
            });
        });

    }

    private void startFetchMealCategoryService() {
        networkDataSource.startFetchMCService();
    }

    /**
     * Database related operations
     **/

    public LiveData<List<MealCategory>> getMealCategories() {
        initializeData();
        //Date today = SunshineDateUtils.getNormalizedUtcDateForToday();
        return mealCategoryDAO.getAllWithMealsLive();
    }

//    public LiveData<WeatherEntry> getWeatherByDate(Date date){
//        initializeData();
//        return mealCategoryDAO.getWeatherByDate(date);
//    }

    public synchronized static AppRepository getInstance(
            MealCategoryDAO mealCategoryDAO, MealCategoryMealDAO mealCategoryMealDAO, MealDAO mealDAO, AddonDAO addonDAO,
            AddOnCategoryDAO addOnCategoryDAO, NetworkDataSource networkDataSource,
            AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new AppRepository(mealCategoryDAO, mealCategoryMealDAO, mealDAO, addonDAO, addOnCategoryDAO, networkDataSource, executors);
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    /**
     * Creates periodic sync tasks and checks to see if an immediate sync is required. If an
     * immediate sync is required, this method will take care of making sure that sync occurs.
     */
    private synchronized void initializeData() {

        // Only perform initialization once per app lifetime. If initialization has already been
        // performed, we have nothing to do in this method.
        if (mInitialized) return;
        mInitialized = true;

//        // This method call triggers Sunshine to create its task to synchronize weather data
//        // periodically.
//        networkDataSource.scheduleRecurringFetchJLSync();
//
        mExecutors.diskIO().execute(() -> {
            if (isFetchNeeded()) {
                startFetchMealCategoryService();
            }
            else Log.d(LOG_TAG, "Fetch not needed.");
        });
    }

    /**
     * Database related operations
     **/

    /**
     * Deletes old weather data because we don't need to keep multiple days' data
     */
    private void deleteOldData() {
        mealCategoryDAO.deleteOldMealCategories();
    }

    /**
     * Checks if there are enough days of future weather for the app to display all the needed data.
     *
     * @return Whether a fetch is needed
     */
    private boolean isFetchNeeded() {
        Log.d(LOG_TAG, "Number of rows in DB: "+String.valueOf(mealCategoryDAO.rowCount()));
        return true;
//        return mealCategoryDAO.rowCount() == 0;
    }

    /**
     * Network related operation
     */

    private void startFetchMCService() {
        networkDataSource.startFetchMCService();
    }

}
