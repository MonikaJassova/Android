package sk.akademiasovy.monikajassova.jedalnylistok.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by monika.jassova on 11/28/2017.
 */

public class RetrofitClient {
    private static Retrofit retrofit = null;
    public static final String BASE_URL = "http://papagaj-breweria.herokuapp.com/api/v1/menu/54ca39f401731406200082df/";

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
