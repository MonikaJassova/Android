package sk.akademiasovy.monikajassova.jedalnylistok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.Addon;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.AddOnCategoriesResponse;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.AddOnCategory;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.AddOnsResponse;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.MealCategoriesResponse;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.MealCategory;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.Mealm;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.MealsResponse;
import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.AddonCategoryService;
import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.AddonService;
import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.ApiUtils;
import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.MealCategoryService;
import sk.akademiasovy.monikajassova.jedalnylistok.data.remote.MealService;

public class MainActivity extends AppCompatActivity {

    TextView result;
    private static final String TAG = "hlavna";
    private AddonCategoryService addonCategoryService;
    private MealCategoryService mealCategoryService;
    private AddonService addonService;
    private MealService mealService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result);

        fetchAddOnCategories();
        fetchMealCategories();
        fetchAddons();
        fetchMeals();

    }

    public void fetchMeals() {
        mealService = ApiUtils.getMealService();
        Call<MealsResponse> call = mealService.getMeals();
        call.enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                List<Mealm> meals = response.body().getMeals();
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
                List<MealCategory> mealCategories = response.body().getMealCategories();
                Log.i(TAG, "Nummber of meal categories received: " + mealCategories.size());
                int length = mealCategories.size();
                Log.i(TAG, "version "+response.body().getVersion());
                Log.i(TAG, "addonId "+mealCategories.get(1).getMeals().get(0).getAddOnIds().get(0));
                for (int i = 0; i < length; i++) {
                    Log.i(TAG, mealCategories.get(i).getName());
                }
            }

            @Override
            public void onFailure(Call<MealCategoriesResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
