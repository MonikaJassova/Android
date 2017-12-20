package sk.akademiasovy.monikajassova.jedalnylistok.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sk.akademiasovy.monikajassova.jedalnylistok.InjectorUtils;
import sk.akademiasovy.monikajassova.jedalnylistok.R;
import sk.akademiasovy.monikajassova.jedalnylistok.data.AppDatabase;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.*;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategory;
import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.AddonCategoryService;
import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.AddonService;
import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.ApiUtils;
import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.MealCategoryService;
import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.MealService;

public class MainActivity extends AppCompatActivity implements MealCategoryPersAdapter.MealCategoryPersAdapterOnItemClickHandler{

    private static final String TAG = "hlavna";

    private AddonCategoryService addonCategoryService;
    private MealCategoryService mealCategoryService;
    private AddonService addonService;
    private MealService mealService;

    private RecyclerView recyclerView;
    private MealCategoryAdapter mAdapter;

    private ProgressBar mLoadingIndicator;
    private MealCategoryPersAdapter mForecastAdapter;
    private RecyclerView mRecyclerView;
    private int mPosition = RecyclerView.NO_POSITION;
    private MainActivityViewModel mViewModel;

    private MealCategoryExAdapter adapter;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        recyclerView = (RecyclerView) findViewById(R.id.mealcategory_recyclerview);
//        mAdapter = new MealCategoryAdapter(new ArrayList<MealCategory>(0), R.layout.row_layout, this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(mAdapter);
//        recyclerView.setHasFixedSize(true);

        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);
        mRecyclerView = findViewById(R.id.mealcategory_recyclerview);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mForecastAdapter = new MealCategoryPersAdapter(this, this);

        /* Setting the adapter attaches it to the RecyclerView in our layout. */
        mRecyclerView.setAdapter(mForecastAdapter);
        MainViewModelFactory factory = InjectorUtils.provideMainActivityViewModelFactory(this.getApplicationContext());
        mViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

        mViewModel.getMealCategories().observe(this, newMealCategories -> {
            mForecastAdapter.swapMealCategories(newMealCategories);
            if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
            mRecyclerView.smoothScrollToPosition(mPosition);

            // Show the weather list or the loading screen based on whether the forecast data exists
            // and is loaded
            if (newMealCategories != null && newMealCategories.size() != 0) showMealCategoryDataView();
            else showLoading();
        });


        /*RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mealcategory_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        db = AppDatabase.getInstance(getApplicationContext());

        fetchMealCategories();
//        fetchAddOnCategories();
//        fetchAddons();
//        fetchMeals();


        List<sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategory> mealCategories = db.mealCategoryDAO().getAll();
        List<sk.akademiasovy.monikajassova.jedalnylistok.ui.MealCategory> mcUI = new ArrayList<>();
        int i = 0;
        for (MealCategory mc : mealCategories) {
//            Log.i(TAG, "MC z DB: "+mc.getName());
//            Log.i(TAG, "Meal z DB: "+mc.getMeals().get(0).getName());
            mcUI.add(i, new sk.akademiasovy.monikajassova.jedalnylistok.ui.MealCategory(mc));
            i++;
        }

        //instantiate your adapter with the list of genres
        adapter = new MealCategoryExAdapter(mcUI);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);*/

    }

    public void fetchMeals() {
        mealService = ApiUtils.getMealService();
        Call<MealsResponse> call = mealService.getMeals();
        call.enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                List<Meal> meals = response.body().getMeals();
                Log.i(TAG, "Nummber of meals received: " + meals.size());
                int length = meals.size();
                Log.i(TAG, "version "+response.body().getVersion());
                for (int i = 0; i < length; i++) {
                    Log.i(TAG, meals.get(i).getName());
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    public void fetchAddons() {
        addonService = ApiUtils.getAddonService();
        Call<AddOnsResponse> call = addonService.getAddons();
        call.enqueue(new Callback<AddOnsResponse>() {
            @Override
            public void onResponse(Call<AddOnsResponse> call, Response<AddOnsResponse> response) {
                List<Addon> addOns = response.body().getAddOns();
                Log.i(TAG, "Nummber of addons received: " + addOns.size());
                int length = addOns.size();
                Log.i(TAG, "version "+response.body().getVersion());
                for (int i = 0; i < length; i++) {
                    Log.i(TAG, addOns.get(i).getName());
                }
            }

            @Override
            public void onFailure(Call<AddOnsResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    public void fetchAddOnCategories(){
        addonCategoryService = ApiUtils.getAddonCategoryService();
        Call<AddOnCategoriesResponse> call = addonCategoryService.getAddonCategories();
        call.enqueue(new Callback<AddOnCategoriesResponse>() {
            @Override
            public void onResponse(Call<AddOnCategoriesResponse> call, Response<AddOnCategoriesResponse> response) {
                List<AddOnCategory> addOnCategories = response.body().getAddOnCategories();
                Log.i(TAG, "Nummber of addon categories received: " + addOnCategories.size());
                int length = addOnCategories.size();
                Log.i(TAG, "version "+response.body().getVersion());
                for (int i = 0; i < length; i++) {
                    Log.i(TAG, addOnCategories.get(i).getSelectionOption().getMax().toString());
                }
            }

            @Override
            public void onFailure(Call<AddOnCategoriesResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    public void fetchMealCategories(){
        mealCategoryService = ApiUtils.getMealCategoryService();
        Call<MealCategoriesResponse> call2 = mealCategoryService.getMealCategories();
        call2.enqueue(new Callback<MealCategoriesResponse>() {
            @Override
            public void onResponse(Call<MealCategoriesResponse> call, Response<MealCategoriesResponse> response) {
                List<MealCategory> mc = response.body().getMealCategories();
//                mAdapter.updateAnswers(mc);
                db.mealCategoryDAO().bulkInsert(mc);
            }

            @Override
            public void onFailure(Call<MealCategoriesResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //adapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //adapter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onItemClick(String id) {
        Intent mealDetailIntent = new Intent(MainActivity.this, MealActivity.class);
        mealDetailIntent.putExtra(MealActivity.MEAL_ID_EXTRA, id);
        startActivity(mealDetailIntent);
    }

    /**
     * This method will make the View for the weather data visible and hide the error message and
     * loading indicator.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't need to check whether
     * each view is currently visible or invisible.
     */
    private void showMealCategoryDataView() {
        // First, hide the loading indicator
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        // Finally, make sure the weather data is visible
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * This method will make the loading indicator visible and hide the weather View and error
     * message.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't need to check whether
     * each view is currently visible or invisible.
     */
    private void showLoading() {
        // Then, hide the weather data
        mRecyclerView.setVisibility(View.INVISIBLE);
        // Finally, show the loading indicator
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }
}
