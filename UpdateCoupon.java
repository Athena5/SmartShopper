package hongfanwang.smartshopper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class UpdateCoupon extends AppCompatActivity {

    private SmartShopperDataSource dataSource;
    private Coupon coupon;

    private EditText storeNameInput;
    private EditText addressInput;
    private DatePicker expirationDateInput;
    private EditText descriptionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_coupon);

        dataSource = new SmartShopperDataSource(this);

        Bundle b = getIntent().getExtras();
        String id = b.getString("id");
        coupon = dataSource.getCoupon(Integer.parseInt(id));

        storeNameInput = (EditText) findViewById(R.id.store_name_input);
        storeNameInput.setText(coupon.getStoreName());

        addressInput = (EditText) findViewById(R.id.address_input);
        addressInput.setText(coupon.getAddress());

        descriptionInput = (EditText) findViewById(R.id.description_input);
        descriptionInput.setText(coupon.getDescription());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(coupon.getExpirationDate());
        final int currentYear = calendar.get(Calendar.YEAR);
        final int currentMonth = calendar.get(Calendar.MONTH);
        final int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        expirationDateInput = (DatePicker) findViewById(R.id.expiration_date_input);
        expirationDateInput.init(currentYear, currentMonth, currentDay, null);

        Button updateButton = (Button) findViewById(R.id.update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                updateCoupon();
            }
        });
    }

    private void updateCoupon() {
        coupon.setStoreName(storeNameInput.getText().toString());
        coupon.setAddress(addressInput.getText().toString());
        coupon.setDescription(descriptionInput.getText().toString());

        Calendar c = new GregorianCalendar(expirationDateInput.getYear(), expirationDateInput.getMonth(), expirationDateInput.getDayOfMonth());
        coupon.setExpirationDate(c.getTimeInMillis());

        dataSource.updateCoupon(coupon);
        finish();
    }
}
