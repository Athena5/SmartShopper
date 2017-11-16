package hongfanwang.smartshopper;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ViewStoreDistances extends AppCompatActivity {

    private Location currentLocation;
    private Geocoder geocoder;
    private SmartShopperDataSource dataSource;
    private StoreAdapter adapter;
    private ListView storeListView;


    private List<Store> allStores;
    private StoreNameCompare storeNameCompare;
    private DistanceCompare distanceCompare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_store_distances);

        Bundle b = getIntent().getExtras();
        double latitude = b.getDouble("lat");
        double longitude = b.getDouble("long");

        currentLocation = new Location("");
        currentLocation.setLatitude(latitude);
        currentLocation.setLongitude(longitude);
        geocoder = new Geocoder(this, Locale.getDefault());

        dataSource = new SmartShopperDataSource(this);
        storeListView = (ListView) findViewById(R.id.store_list_view);

        allStores = dataSource.getDistinctLocations();
        storeNameCompare = new StoreNameCompare();
        distanceCompare = new DistanceCompare();

        Button byExpirationDateButton = (Button) findViewById(R.id.by_distance_button);
        byExpirationDateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                sortListByDistance();
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

    @Override
    protected void onResume() {
        super.onResume();

        allStores = dataSource.getDistinctLocations();
        populateList();
    }

    protected void populateList() {
        try {
            for (Store store : allStores) {
                List<Address> address = geocoder.getFromLocationName(store.getAddress(), 5);
                if (address == null) {
                    store.setDistance(-1);
                }
                else {
                    Address location = address.get(0);
                    Location storeLocation = new Location("");
                    storeLocation.setLatitude(location.getLatitude());
                    storeLocation.setLongitude(location.getLongitude());
                    float[] dist = new float[1];
                    Location.distanceBetween(currentLocation.getLatitude(), currentLocation.getLongitude(), storeLocation.getLatitude(), storeLocation.getLongitude(), dist);
                    store.setDistance((double)dist[0]);
                    //store.setDistance(currentLocation.distanceTo(storeLocation));
                    store.setUnit("m");
                }
            }
        }
        catch (IOException e) {

        }
        adapter = new StoreAdapter(this, allStores);
        storeListView.setAdapter(adapter);
    }

    protected void sortListByDistance(){
        Collections.sort(allStores, distanceCompare);
        populateList();
    }

    protected void sortListByStoreName(){
        Collections.sort(allStores, storeNameCompare);
        populateList();
    }
}
