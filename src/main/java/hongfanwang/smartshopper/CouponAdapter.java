package hongfanwang.smartshopper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Hong Fan on 4/27/2017.
 */

public class CouponAdapter extends BaseAdapter {
    private Context context;
    private List<Coupon> dataSource;
    private LayoutInflater inflater;

    public CouponAdapter(Context context, List<Coupon> dataSource) {
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
        Coupon coupon = (Coupon) getItem(position);
        return coupon.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.coupon_list_item, parent, false);
        TextView storeNameTextView = (TextView) rowView.findViewById(R.id.store_name);
        TextView expirationDateTextView = (TextView) rowView.findViewById(R.id.expiration_date);

        Coupon coupon = (Coupon) getItem(position);
        storeNameTextView.setText(coupon.getStoreName());

        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy", Locale.US);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(coupon.getExpirationDate());
        expirationDateTextView.setText("Expires: " + sdf.format(calendar.getTime()));

        return rowView;
    }
}
