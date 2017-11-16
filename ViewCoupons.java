package hongfanwang.smartshopper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;

public class ViewCoupons extends AppCompatActivity {

    private SmartShopperDataSource dataSource;
    private CouponAdapter adapter;
    private ListView couponListView;

    private List<Coupon> allCoupons;
    private CouponNameCompare couponNameCompare;
    private ExpirationDateCompare expirationDateCompare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_coupons);

        dataSource = new SmartShopperDataSource(this);
        couponListView = (ListView) findViewById(R.id.coupon_list_view);

        allCoupons = dataSource.getAllCoupons();
        couponNameCompare = new CouponNameCompare();
        expirationDateCompare = new ExpirationDateCompare();

        Button byExpirationDateButton = (Button) findViewById(R.id.by_date_button);
        byExpirationDateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                sortListByExpirationDate();
            }
        });

        Button byStoreNameButton = (Button) findViewById(R.id.by_name_button);
        byStoreNameButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                sortListByStoreName();
            }
        });

        populateList();
    }

    protected void onResume() {
        super.onResume();
        allCoupons = dataSource.getAllCoupons();
        populateList();
    }

    protected void populateList() {
        final Context context = this;
        adapter = new CouponAdapter(this, allCoupons);

        couponListView.setAdapter(adapter);
        couponListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Coupon selectedCoupon = dataSource.getAllCoupons().get(position);
                Intent detailIntent = new Intent(context, CouponDetails.class);
                detailIntent.putExtra("id", "" + selectedCoupon.getId());
                startActivity(detailIntent);
            }
        });
    }

    protected void sortListByExpirationDate(){
        Collections.sort(allCoupons, expirationDateCompare);
        populateList();
    }

    protected void sortListByStoreName(){
        Collections.sort(allCoupons, couponNameCompare);
        populateList();
    }
}
