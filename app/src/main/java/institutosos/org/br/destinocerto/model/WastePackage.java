package institutosos.org.br.destinocerto.model;

import com.google.gson.annotations.SerializedName;

public class WastePackage {
    private EMaterial material;
    private double weight;
    @SerializedName("picture")
    private String imageUrl;
    private Site site;
    private String barcode;

    public void setMaterial(EMaterial material) {
        this.material = material;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public double getWeight() {
        return weight;
    }

    public EMaterial getMaterial() {
        return material;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }

    public Site getSite() {
        return site;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public enum EMaterial {
        PLASTIC
    }
}
