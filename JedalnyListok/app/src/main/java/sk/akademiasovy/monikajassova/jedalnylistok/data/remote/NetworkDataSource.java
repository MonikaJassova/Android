package sk.akademiasovy.monikajassova.jedalnylistok.data.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import sk.akademiasovy.monikajassova.jedalnylistok.AppExecutors;
import sk.akademiasovy.monikajassova.jedalnylistok.AppJobService;
import sk.akademiasovy.monikajassova.jedalnylistok.AppSyncIntentService;

/**
 * Created by monika.jassova on 12/19/2017.
 */

public class NetworkDataSource {
    private static final String LOG_TAG = NetworkDataSource.class.getSimpleName();

    // Interval at which to sync with the weather. Use TimeUnit for convenience, rather than
    // writing out a bunch of multiplication ourselves and risk making a silly mistake.
    private static final int SYNC_INTERVAL_HOURS = 12;
    private static final int SYNC_INTERVAL_SECONDS = (int) TimeUnit.HOURS.toSeconds(SYNC_INTERVAL_HOURS);
    private static final int SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS / 3;
    private static final String SUNSHINE_SYNC_TAG = "jl-sync";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static NetworkDataSource sInstance;
    private final Context mContext;

    private final AppExecutors mExecutors;

    private NetworkDataSource(Context context, AppExecutors executors) {
        mContext = context;
        mExecutors = executors;

        mDownloadedWeatherForecasts = new MutableLiveData<WeatherEntry[]>();
    }

    // LiveData storing the latest downloaded weather forecasts
    private final MutableLiveData<WeatherEntry[]> mDownloadedWeatherForecasts;

    public LiveData<WeatherEntry[]> getCurrentWeatherForecasts() {
        return mDownloadedWeatherForecasts;
    }

    /**
     * Get the singleton for this class
     */
    public static NetworkDataSource getInstance(Context context, AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new NetworkDataSource(context.getApplicationContext(), executors);
                Log.d(LOG_TAG, "Made new network data source");
            }
        }
        return sInstance;
    }

    /**
     * Starts an intent service to fetch the weather.
     */
    public void startFetchJLService() {
        Intent intentToFetch = new Intent(mContext, AppSyncIntentService.class);
        mContext.startService(intentToFetch);
        Log.d(LOG_TAG, "Service created");
    }

    /**
     * Schedules a repeating job service which fetches the weather.
     */
    public void scheduleRecurringFetchJLSync() {
        Driver driver = new GooglePlayDriver(mContext);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        // Create the Job to periodically sync Sunshine
        Job syncSunshineJob = dispatcher.newJobBuilder()
                /* The Service that will be used to sync Sunshine's data */
                .setService(SunshineFirebaseJobService.class)
                /* Set the UNIQUE tag used to identify this Job */
                .setTag(SUNSHINE_SYNC_TAG)
                /*
                 * Network constraints on which this Job should run. We choose to run on any
                 * network, but you can also choose to run only on un-metered networks or when the
                 * device is charging. It might be a good idea to include a preference for this,
                 * as some users may not want to download any data on their mobile plan. ($$$)
                 */
                .setConstraints(Constraint.ON_ANY_NETWORK)
                /*
                 * setLifetime sets how long this job should persist. The options are to keep the
                 * Job "forever" or to have it die the next time the device boots up.
                 */
                .setLifetime(Lifetime.FOREVER)
                /*
                 * We want Sunshine's weather data to stay up to date, so we tell this Job to recur.
                 */
                .setRecurring(true)
                /*
                 * We want the weather data to be synced every 3 to 4 hours. The first argument for
                 * Trigger's static executionWindow method is the start of the time frame when the
                 * sync should be performed. The second argument is the latest point in time at
                 * which the data should be synced. Please note that this end time is not
                 * guaranteed, but is more of a guideline for FirebaseJobDispatcher to go off of.
                 */
                .setTrigger(Trigger.executionWindow(
                        SYNC_INTERVAL_SECONDS,
                        SYNC_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
                /*
                 * If a Job with the tag with provided already exists, this new job will replace
                 * the old one.
                 */
                .setReplaceCurrent(true)
                /* Once the Job is ready, call the builder's build method to return the Job */
                .build();

        // Schedule the Job with the dispatcher
        dispatcher.schedule(syncSunshineJob);
        Log.d(LOG_TAG, "Job scheduled");
    }

    /**
     * Gets the newest weather
     */
    public void fetchMealCategories() {
        Log.d(LOG_TAG, "Fetch meal categories started");
        mExecutors.networkIO().execute(() -> {
            try {

                // The getUrl method will return the URL that we need to get the forecast JSON for the
                // weather. It will decide whether to create a URL based off of the latitude and
                // longitude or off of a simple location as a String.

                URL weatherRequestUrl = NetworkUtils.getUrl();

                // Use the URL to retrieve the JSON
                String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(weatherRequestUrl);

                // Parse the JSON into a list of weather forecasts
                WeatherResponse response = new OpenWeatherJsonParser().parse(jsonWeatherResponse);
                Log.d(LOG_TAG, "JSON Parsing finished");


                // As long as there are weather forecasts, update the LiveData storing the most recent
                // weather forecasts. This will trigger observers of that LiveData, such as the
                // SunshineRepository.
                if (response != null && response.getWeatherForecast().length != 0) {
                    Log.d(LOG_TAG, "JSON not null and has " + response.getWeatherForecast().length
                            + " values");
                    Log.d(LOG_TAG, String.format("First value is %1.0f and %1.0f",
                            response.getWeatherForecast()[0].getMin(),
                            response.getWeatherForecast()[0].getMax()));

                    // TODO Finish this method when instructed.
                    // Will eventually do something with the downloaded data
                    mDownloadedWeatherForecasts.postValue(response.getWeatherForecast());
                }
            } catch (Exception e) {
                // Server probably invalid
                e.printStackTrace();
            }
        });
    }

}
