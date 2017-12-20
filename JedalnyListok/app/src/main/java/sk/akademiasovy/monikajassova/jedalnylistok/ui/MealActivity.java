package sk.akademiasovy.monikajassova.jedalnylistok.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import sk.akademiasovy.monikajassova.jedalnylistok.R;

/**
 * Created by monika.jassova on 12/19/2017.
 */

public class MealActivity extends AppCompatActivity {
    private static final String TAG = MealActivity.class.getSimpleName();
    public static final String MEAL_ID_EXTRA = "MEAL_ID_EXTRA";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        String id = null;

        if (extras != null) {
            id = extras.getString(MEAL_ID_EXTRA);
        }

        final ActionBar actionBar = getSupportActionBar();
        try {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
