package hongfanwang.smartshopper;

import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainMenu extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleApiClient googleApiClient = null;

    private SmartShopperDataSource dataSource;
    private TextView couponCount;
    private Location currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        dataSource = new SmartShopperDataSource(this);
        couponCount = (TextView) findViewById(R.id.count);
        updateCount();

        Button viewCouponsButton = (Button) findViewById(R.id.view_coupons);
        viewCouponsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startViewCouponsActivity();
            }
        });

        Button addCouponButton = (Button) findViewById(R.id.add_coupon);
        addCouponButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startAddCouponActivity();
            }
        });

        Button viewStoreDistancesButton = (Button) findViewById(R.id.view_store_distances);
        viewStoreDistancesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startViewStoreDistancesActivity();
            }
        });

        Button clearExpiredButton = (Button) findViewById(R.id.clear_expired);
        clearExpiredButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                clearExpiredCoupons();
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            currentLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        } catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        googleApiClient.connect();
        updateCount();
    }

    @Override
    protected void onPause() {
        super.onPause();
        googleApiClient.disconnect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    public void startViewCouponsActivity() {
        Intent intent = new Intent(this, ViewCoupons.class);
        startActivity(intent);
    }

    public void startAddCouponActivity() {
        Intent intent = new Intent(this, AddCoupon.class);
        startActivity(intent);
    }

    public void startViewStoreDistancesActivity() {
        currentLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        Intent intent = new Intent(this, ViewStoreDistances.class);
        intent.putExtra("lat", "" + currentLocation.getLatitude());
        intent.putExtra("long", "" + currentLocation.getLongitude());
        startActivity(intent);
    }

    public void updateCount() {
        CharSequence c = "Coupons: " + String.valueOf(dataSource.getCouponCount());
        couponCount.setText(c);
    }

    public void clearExpiredCoupons() {
        Calendar calendar = Calendar.getInstance();
        Calendar c = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        dataSource.deleteExpiredCoupons(c.getTimeInMillis());
        updateCount();
    }
}
