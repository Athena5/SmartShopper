package hongfanwang.smartshopper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CouponDetails extends AppCompatActivity {

    private SmartShopperDataSource dataSource;
    private Coupon coupon;

    private TextView storeNameView;
    private TextView addressView ;
    private TextView expirationDateView;
    private TextView descriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_details);

        dataSource = new SmartShopperDataSource(this);

        Bundle b = getIntent().getExtras();
        String id = b.getString("id");
        coupon = dataSource.getCoupon(Integer.parseInt(id));

        storeNameView = (TextView) findViewById(R.id.store_name);
        addressView = (TextView) findViewById(R.id.address);
        expirationDateView = (TextView) findViewById(R.id.expiration_date);
        descriptionView = (TextView) findViewById(R.id.description);

        showDetails();

        Button updateButton = (Button) findViewById(R.id.update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                updateCoupon();
            }
        });

        Button deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                deleteCoupon();
            }
        });
    }

    private void showDetails() {
        coupon = dataSource.getCoupon(coupon.getId());
        storeNameView.setText(coupon.getStoreName());
        addressView.setText(coupon.getAddress());
        descriptionView.setText(coupon.getDescription());

        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy", Locale.US);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(coupon.getExpirationDate());
        expirationDateView.setText(sdf.format(calendar.getTime()));
    }

    protected void onResume() {
        super.onResume();
        showDetails();
    }

    private void updateCoupon() {
        Intent intent = new Intent(this, UpdateCoupon.class);
        intent.putExtra("id", "" + coupon.getId());
        startActivity(intent);
        showDetails();
    }

    private void deleteCoupon() {
        dataSource.deleteCoupon(coupon);
        finish();
    }
}
