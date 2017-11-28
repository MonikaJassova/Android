package sk.akademiasovy.monikajassova.jedalnylistok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import sk.akademiasovy.monikajassova.jedalnylistok.Model.Meals;

public class MainActivity extends AppCompatActivity {

    TextView result;
    private static final String TAG = "hlavna";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result);

        String url = "http://papagaj-breweria.herokuapp.com/api/v1/menu/54ca39f401731406200082df/meal";
        new RetrieveDataTask(new AsyncTaskCallback() {
            @Override
            public void onTaskCompleted(Meals meals) {
                for (int i=0; i<meals.getMeals().size(); i++){
                    Log.i(TAG, meals.getMeals().get(i).getName());
                }
            }
        }).execute(url);

    }
}
