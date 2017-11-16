package hongfanwang.smartshopper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Hong Fan on 5/2/2017.
 */

public class StoreAdapter extends BaseAdapter {

    private Context context;
    private List<Store> dataSource;
    private LayoutInflater inflater;

    public StoreAdapter(Context context, List<Store> dataSource) {
        this.context = context;
        this.dataSource = dataSource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.store_list_item, parent, false);
        TextView storeNameTextView = (TextView) rowView.findViewById(R.id.store_name);
        TextView addressTextView = (TextView) rowView.findViewById(R.id.address);
        TextView distanceTextView = (TextView) rowView.findViewById(R.id.distance);

        Store store = (Store) getItem(position);
        storeNameTextView.setText(store.getStoreName());
        addressTextView.setText(store.getAddress());

        double distance = store.getDistance();
        String unit = store.getUnit();
        if (distance > 0) {
            if (distance >= 100 && unit.equals("m")) {
                distance /= 1000;
                unit = "km";
            }

            distanceTextView.setText(distance + " " + unit);
        }
        else {
            distanceTextView.setText("Unavailable");
        }

        return rowView;
    }
}
