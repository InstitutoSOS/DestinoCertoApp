package institutosos.org.br.destinocerto.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Site {

    private int id;
    private String name;
    private String address;
    private double lat;
    private double lng;

    @SerializedName("site_type")
    private String siteType;

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public double getLongitude() {
        return lng;
    }

    public double getLatitude() {
        return lat;
    }

    public String getType() {
        return siteType;
    }

    public int getId() {
        return id;
    }
}
