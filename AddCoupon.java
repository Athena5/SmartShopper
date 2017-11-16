package hongfanwang.smartshopper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddCoupon extends AppCompatActivity {

    private SmartShopperDataSource dataSource;

    private EditText storeNameInput;
    private EditText addressInput;
    private DatePicker expirationDateInput;
    private EditText descriptionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coupon);

        dataSource = new SmartShopperDataSource(this);
        storeNameInput = (EditText) findViewById(R.id.store_name_input);
        addressInput = (EditText) findViewById(R.id.address_input);
        descriptionInput = (EditText) findViewById(R.id.description_input);

        Calendar calendar = Calendar.getInstance();
        final int currentYear = calendar.get(Calendar.YEAR);
        final int currentMonth = calendar.get(Calendar.MONTH);
        final int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        expirationDateInput = (DatePicker) findViewById(R.id.expiration_date_input);
        expirationDateInput.init(currentYear, currentMonth, currentDay, null);

        Button updateButton = (Button) findViewById(R.id.add_button);
        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                addCoupon();
            }
        });
    }

    private void addCoupon() {
        Coupon coupon = new Coupon();
        coupon.setStoreName(storeNameInput.getText().toString());
        coupon.setAddress(addressInput.getText().toString());
        coupon.setDescription(descriptionInput.getText().toString());

        Calendar c = new GregorianCalendar(expirationDateInput.getYear(), expirationDateInput.getMonth(), expirationDateInput.getDayOfMonth());
        coupon.setExpirationDate(c.getTimeInMillis());

        dataSource.addCoupon(coupon);
        finish();
    }
}
