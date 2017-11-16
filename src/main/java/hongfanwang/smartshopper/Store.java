package hongfanwang.smartshopper;

/**
 * Created by Hong Fan on 5/2/2017.
 */

public class Store {

    private String storeName;
    private String address;
    private double distance;
    private String unit;

    public Store() {
        storeName = "";
        address = "";
        distance = 0;
        unit = "km";
    }

    public Store(String storeName, String address, double distance, String unit) {
        this.storeName = storeName;
        this.address = address;
        this.distance = distance;
        this.unit = unit;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getAddress() {
        return address;
    }

    public double getDistance() {
        return distance;
    }

    public String getUnit() {
        return unit;
    }
}
