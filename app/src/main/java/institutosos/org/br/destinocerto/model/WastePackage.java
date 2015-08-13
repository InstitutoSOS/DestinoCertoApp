package institutosos.org.br.destinocerto.model;

/**
 * Created by rehans on 13-Aug-15.
 */
public class WastePackage {
    private EMaterial material;
    private double weight;
    private String imageUrl;
    private Cooperative cooperative;
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

    public void setCooperative(Cooperative cooperative) {
        this.cooperative = cooperative;
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

    public Cooperative getCooperative() {
        return cooperative;
    }

    public enum EMaterial {
        PLASTIC
    }
}
