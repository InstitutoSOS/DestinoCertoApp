package institutosos.org.br.destinocerto.api;

import retrofit.RestAdapter;

/**
 * Accesses the API.
 */
public class ApiClient {
    // TODO
    public static final String API_URL = "https://api.github.com";
    private ApiService _service;

    public ApiClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        _service = restAdapter.create(ApiService.class);
    }

    public ApiService getService() {
        return _service;
    }


}
