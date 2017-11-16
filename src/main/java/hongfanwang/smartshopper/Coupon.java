package hongfanwang.smartshopper;

import java.sql.Date;

/**
 * Created by Hong Fan on 4/13/2017.
 */

public class Coupon {

    private int id;
    private String storeName;
    private String address;
    private long expirationDate;
    private String description;

    public Coupon() {
        id = -1;
        storeName = "";
        address = "";
        expirationDate = -1;
        description = "";
    }

    public Coupon(int id, String storeName, String address, long expirationDate, String description) {
        this.id = id;
        this.storeName = storeName;
        this.address = address;
        this.expirationDate = expirationDate;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getAddress() {
        return address;
    }

    public long getExpirationDate() {
        return expirationDate;
    }

    public String getDescription() {
        return description;
    }
}
