package institutosos.org.br.destinocerto;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.GsonBuilder;

import institutosos.org.br.destinocerto.api.ApiClient;
import institutosos.org.br.destinocerto.model.User;

public class Application extends android.app.Application {
    private static ApiClient _apiClient;
    private static User _user;


    public Application() {
        //SharedPreferences sharedPref = getSharedPreferences(
        //        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        //String json = sharedPref.getString(getString(R.string.serialized_user), null);
        //if (json != null) {
        //    _user = new GsonBuilder().create().fromJson(json, User.class);
        //}

    }

    public static User getUser() {
        return _user;
    }

    public static void setUser(User user) {
        //SharedPreferences sharedPref = getSharedPreferences(
        //        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPref.edit();
        //editor.putString(getString(R.string.serialized_user), new GsonBuilder().create().toJson(_user));
        //editor.apply();

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
