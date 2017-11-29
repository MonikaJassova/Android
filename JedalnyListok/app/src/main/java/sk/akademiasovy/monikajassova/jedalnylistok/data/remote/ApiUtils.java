package sk.akademiasovy.monikajassova.jedalnylistok.data.remote;

/**
 * Created by monika.jassova on 11/29/2017.
 */

public class ApiUtils {

    public static AddonCategoryService getAddonCategoryService() {
        return RetrofitClient.getClient().create(AddonCategoryService.class);
    }

    public static MealCategoryService getMealCategoryService(){
        return RetrofitClient.getClient().create(MealCategoryService.class);
    }

    public static AddonService getAddonService(){
        return RetrofitClient.getClient().create(AddonService.class);
    }

    public static MealService getMealService(){
        return RetrofitClient.getClient().create(MealService.class);
    }
}
