package sk.akademiasovy.monikajassova.jedalnylistok;

import android.content.Context;
import android.os.AsyncTask;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

import sk.akademiasovy.monikajassova.jedalnylistok.Model.Meals;

/**
 * Created by monika.jassova on 11/28/2017.
 */

public class RetrieveDataTask extends AsyncTask<String, Void, Meals> {

    private AsyncTaskCallback listener;

    public RetrieveDataTask(AsyncTaskCallback listener){
        this.listener = listener;
    }

    @Override
    protected Meals doInBackground(String... strings) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Meals meals = null;
        try {
            meals = mapper.readValue(new URL(strings[0]), Meals.class);
            listener.onTaskCompleted(meals);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Meals meals) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }

}
