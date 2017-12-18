package sk.akademiasovy.monikajassova.jedalnylistok.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.AddOnsResponse;

/**
 * Created by monika.jassova on 11/29/2017.
 */

public interface AddonService {
    @GET("addon")
    Call<AddOnsResponse> getAddons();
}
