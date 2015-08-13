package institutosos.org.br.destinocerto.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Accesses the API.
 */
public class ApiClient {
    private static final String API_URL = "http://destinocerto.sebastianhaeni.ch/api/";
    private ApiService _service;

    Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Date.class, new DateTypeAdapter())
            .create();

    public ApiClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        _service = restAdapter.create(ApiService.class);
    }

    public ApiService getService() {
        return _service;
    }


}
