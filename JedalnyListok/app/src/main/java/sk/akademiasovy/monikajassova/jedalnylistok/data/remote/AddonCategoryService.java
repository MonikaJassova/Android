package sk.akademiasovy.monikajassova.jedalnylistok.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.AddOnCategoriesResponse;

/**
 * Created by monika.jassova on 11/28/2017.
 */

public interface AddonCategoryService {
    @GET("addon/category")
    Call<AddOnCategoriesResponse> getAddonCategories();
}
