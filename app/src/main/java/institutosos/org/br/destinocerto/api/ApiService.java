package institutosos.org.br.destinocerto.api;

import java.util.List;

import institutosos.org.br.destinocerto.model.WastePackage;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ApiService {
    @GET("package/{id}")
    List<WastePackage> getPackage(@Path("id") String barcode);
}
