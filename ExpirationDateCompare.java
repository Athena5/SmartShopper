package hongfanwang.smartshopper;

import java.util.Comparator;

/**
 * Created by Hong Fan on 5/1/2017.
 */

public class ExpirationDateCompare implements Comparator<Coupon> {

    @Override
    public int compare(Coupon coupon1, Coupon coupon2) {
        return Long.valueOf(coupon1.getExpirationDate()).compareTo(Long.valueOf(coupon2.getExpirationDate()));
    }
}
