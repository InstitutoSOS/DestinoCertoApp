package institutosos.org.br.destinocerto;

import institutosos.org.br.destinocerto.api.ApiClient;
import institutosos.org.br.destinocerto.model.User;

public class Application extends android.app.Application {
    private static ApiClient _apiClient;
    private static User _user;

    public static User getUser() {
        return _user;
    }

    public static void setUser(User user) {
        Application._user = user;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        _apiClient = new ApiClient();
    }

    public static ApiClient getApiClient() {
        return _apiClient;
    }
}
