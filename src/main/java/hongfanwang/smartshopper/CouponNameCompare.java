package hongfanwang.smartshopper;

import java.util.Comparator;

/**
 * Created by Hong Fan on 5/1/2017.
 */

public class CouponNameCompare implements Comparator<Coupon> {

    @Override
    public int compare(Coupon coupon1, Coupon coupon2) {
        return coupon1.getStoreName().compareToIgnoreCase(coupon2.getStoreName());
    }
}
