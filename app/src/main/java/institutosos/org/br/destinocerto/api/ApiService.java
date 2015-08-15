package institutosos.org.br.destinocerto.api;

import java.util.List;

import institutosos.org.br.destinocerto.model.LoginResult;
import institutosos.org.br.destinocerto.model.Material;
import institutosos.org.br.destinocerto.model.Site;
import institutosos.org.br.destinocerto.model.User;
import institutosos.org.br.destinocerto.model.WastePackage;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;

public interface ApiService {
    @GET("/package/{id}")
    void getPackage(@Path("id") String barcode, Callback<WastePackage> cb);

    @GET("/site")
    void getSites(Callback<List<Site>> cb);

    @GET("/site/{id}")
    void getSite(@Path("id") int siteId, Callback<Site> cb);

    @Multipart
    @POST("/site")
    Site createSite(@Part("name") String name, @Part("address") String address);

    @Multipart
    @POST("/user/login")
    LoginResult login(@Part("username") String username, @Part("password") String password);

    @Multipart
    @POST("/user")
    User signUp(@Part("username") String username, @Part("password") String password);

    @FormUrlEncoded
    @POST("/locationhistory")
    void updatePackageLocation(@Field("package_id") int id, @Field("site_id") int idSite, Callback<WastePackage> cb);

    void getMaterials(Callback<List<Material>> cb);

    @FormUrlEncoded
    @PUT("/user/{id}")
    User updateUser(@Path("id") int id, @Field("site_id") int siteId);
}
