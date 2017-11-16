package hongfanwang.smartshopper;

import java.util.Comparator;

/**
 * Created by Hong Fan on 5/2/2017.
 */

public class StoreNameCompare implements Comparator<Store> {

    @Override
    public int compare(Store store1, Store store2) {
        return store1.getStoreName().compareToIgnoreCase(store2.getStoreName());
    }
}
