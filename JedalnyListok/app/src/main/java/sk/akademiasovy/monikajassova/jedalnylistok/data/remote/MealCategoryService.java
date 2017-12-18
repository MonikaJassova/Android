package sk.akademiasovy.monikajassova.jedalnylistok.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategoriesResponse;

/**
 * Created by monika.jassova on 11/29/2017.
 */

public interface MealCategoryService {
    @GET("meal/category")
    Call<MealCategoriesResponse> getMealCategories();
}
