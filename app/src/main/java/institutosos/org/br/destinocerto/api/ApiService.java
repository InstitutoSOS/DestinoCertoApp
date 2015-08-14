package institutosos.org.br.destinocerto.api;

import java.util.List;

import institutosos.org.br.destinocerto.model.Site;
import institutosos.org.br.destinocerto.model.WastePackage;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ApiService {
    @GET("/package/{id}")
    void getPackage(@Path("id") String barcode, Callback<WastePackage> cb);

    @GET("/site")
    void getSites(Callback<List<Site>> cb);

    @GET("/site/{id}")
    void getSite(@Path("id") int siteId, Callback<Site> cb);

    @POST("/package/{id}")
    void signUp(@Body double lat, @Body double lng, Callback<WastePackage> cb);

    @POST("/user/login")
    void login(@Body String username, @Body String password, Callback<WastePackage> cb);

    @POST("/user")
    void signUp(@Body String username, @Body String password, Callback<WastePackage> cb);

}
