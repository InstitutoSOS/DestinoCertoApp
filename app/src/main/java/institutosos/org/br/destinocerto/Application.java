package institutosos.org.br.destinocerto;

import institutosos.org.br.destinocerto.api.ApiClient;

public class Application extends android.app.Application {
    private static ApiClient _apiClient;

    @Override
    public void onCreate() {
        super.onCreate();

        _apiClient = new ApiClient();
    }


    public static ApiClient getApiClient() {
        return _apiClient;
    }
}
