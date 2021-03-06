package sk.akademiasovy.monikajassova.jedalnylistok.ui;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sk.akademiasovy.monikajassova.jedalnylistok.R;
import sk.akademiasovy.monikajassova.jedalnylistok.data.AppDatabase;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.*;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategory;
import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.AddonCategoryService;
import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.AddonService;
import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.ApiUtils;
import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.MealCategoryService;
import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.MealService;

import static sk.akademiasovy.monikajassova.jedalnylistok.ui.MealCategoryDataFactory.makeMealCategories;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "hlavna";

    private AddonCategoryService addonCategoryService;
    private MealCategoryService mealCategoryService;
    private AddonService addonService;
    private MealService mealService;

    private RecyclerView recyclerView;
    private MealCategoryAdapter mAdapter;

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

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mealcategory_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        db = AppDatabase.getInstance(getApplicationContext());

        //instantiate your adapter with the list of genres
        adapter = new MealCategoryExAdapter(makeMealCategories());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fetchMealCategories();
//        fetchAddOnCategories();
//        fetchAddons();
//        fetchMeals();


        List<sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategory> mealCategories = db.mealCategoryDAO().getAll();
        for (MealCategory mc : mealCategories) {
            Log.i(TAG, "MC z DB: "+mc.getName());
        }

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
                db.mealCategoryDAO().insertAll(mc);
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
        adapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter.onRestoreInstanceState(savedInstanceState);
    }
}
