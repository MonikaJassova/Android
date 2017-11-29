package sk.akademiasovy.monikajassova.jedalnylistok.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.MealsResponse;

/**
 * Created by monika.jassova on 11/29/2017.
 */

public interface MealService {
    @GET("meal")
    Call<MealsResponse> getMeals();
}
