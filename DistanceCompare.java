package hongfanwang.smartshopper;

import java.util.Comparator;

/**
 * Created by Hong Fan on 5/2/2017.
 */

public class DistanceCompare implements Comparator<Store> {

    @Override
    public int compare(Store store1, Store store2) {
        double distance1 = store1.getDistance();
        String unit1 = store1.getUnit();
        if(unit1.equals("m")) {
            distance1 /= 1000;
        }

        double distance2 = store2.getDistance();
        String unit2 = store2.getUnit();
        if(unit2.equals("m")) {
            distance2 /= 1000;
        }

        return Double.valueOf(distance1).compareTo(Double.valueOf(distance2));
    }
}
