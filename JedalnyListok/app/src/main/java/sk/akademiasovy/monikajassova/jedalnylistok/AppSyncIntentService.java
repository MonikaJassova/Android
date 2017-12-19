package sk.akademiasovy.monikajassova.jedalnylistok;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.NetworkDataSource;

/**
 * Created by monika.jassova on 12/19/2017.
 */

public class AppSyncIntentService extends IntentService {

    private static final String LOG_TAG = AppSyncIntentService.class.getSimpleName();

    public AppSyncIntentService() {
        super("JLSyncIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(LOG_TAG, "Intent service started");
        NetworkDataSource networkDataSource = InjectorUtils.provideNetworkDataSource(this.getApplicationContext());
        networkDataSource.fetchMealCategories();
    }
}
