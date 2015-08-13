package institutosos.org.br.destinocerto.api;

import institutosos.org.br.destinocerto.model.WastePackage;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ApiService {
    @GET("/package/{id}")
    void getPackage(@Path("id") String barcode, Callback<WastePackage> cb);
}
