package institutosos.org.br.destinocerto.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class WastePackage {

    private Material material;

    private double weight;

    @SerializedName("image")
    private String imageUrl;

    @SerializedName("currentLocation")
    private Location currentLocation;

    private String barcode;

    @SerializedName("siteHistory")
    private List<Location> siteHistory;

    public double getWeight() {
        return weight;
    }

    public Material getMaterial() {
        return material;
    }

    public String getBarcode() {
        return barcode;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<Location> getLocationHistory() {
        return siteHistory;
    }
}
