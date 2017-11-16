package hongfanwang.smartshopper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hong Fan on 4/20/2017.
 */

public class SmartShopperDataSource {

    private SmartShopperSQLiteHelper dbHelper;

    public SmartShopperDataSource(Context context) {
        dbHelper = new SmartShopperSQLiteHelper(context);
    }

    public void addCoupon(Coupon coupon) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SmartShopperSQLiteHelper.COLUMN_STORENAME, coupon.getStoreName());
        values.put(SmartShopperSQLiteHelper.COLUMN_ADDRESS, coupon.getAddress());
        values.put(SmartShopperSQLiteHelper.COLUMN_EXPIRATIONDATE, coupon.getExpirationDate());
        values.put(SmartShopperSQLiteHelper.COLUMN_DESCRIPTION, coupon.getDescription());

        database.insert(SmartShopperSQLiteHelper.TABLE_COUPONS, null, values);
        database.close();
    }

    public void addCoupon(int id, String storeName, String address, long expirationDate, String description) {
        Coupon coupon = new Coupon(id, storeName, address, expirationDate, description);
        addCoupon(coupon);
    }

    public Coupon getCoupon(int id) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(SmartShopperSQLiteHelper.TABLE_COUPONS, null, SmartShopperSQLiteHelper.COLUMN_ID + " = ?", new String[]{Integer.toString(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Coupon coupon = new Coupon(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Long.parseLong(cursor.getString(3)), cursor.getString(4));
        cursor.close();

        return coupon;
    }

    public List<Coupon> getAllCoupons() {
        List<Coupon> couponList = new ArrayList<Coupon>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String countQuery = "SELECT * FROM " + SmartShopperSQLiteHelper.TABLE_COUPONS;
        Cursor cursor = database.rawQuery(countQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Coupon coupon = new Coupon();
                coupon.setId(Integer.parseInt(cursor.getString(0)));
                coupon.setStoreName(cursor.getString(1));
                coupon.setAddress(cursor.getString(2));
                coupon.setExpirationDate(Long.parseLong(cursor.getString(3)));
                coupon.setDescription(cursor.getString(4));
                couponList.add(coupon);
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        return couponList;
    }

    public int getCouponCount() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(SmartShopperSQLiteHelper.TABLE_COUPONS, new String[]{SmartShopperSQLiteHelper.COLUMN_ID}, null, null, null, null, null, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public List<Store> getDistinctLocations() {
        List<Store> storesList = new ArrayList<Store>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(true, SmartShopperSQLiteHelper.TABLE_COUPONS, new String[]{SmartShopperSQLiteHelper.COLUMN_STORENAME, SmartShopperSQLiteHelper.COLUMN_ADDRESS}, null, null, SmartShopperSQLiteHelper.COLUMN_STORENAME, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Store store = new Store();
                store.setStoreName(cursor.getString(0));
                store.setAddress(cursor.getString(1));
                storesList.add(store);
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        return storesList;
    }

    public void updateCoupon(Coupon coupon) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SmartShopperSQLiteHelper.COLUMN_STORENAME, coupon.getStoreName());
        values.put(SmartShopperSQLiteHelper.COLUMN_ADDRESS, coupon.getAddress());
        values.put(SmartShopperSQLiteHelper.COLUMN_EXPIRATIONDATE, coupon.getExpirationDate());
        values.put(SmartShopperSQLiteHelper.COLUMN_DESCRIPTION, coupon.getDescription());

        database.update(SmartShopperSQLiteHelper.TABLE_COUPONS, values, SmartShopperSQLiteHelper.COLUMN_ID + " = ?", new String[] { String.valueOf(coupon.getId()) });
        database.close();
    }

    public void deleteCoupon(Coupon coupon) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(SmartShopperSQLiteHelper.TABLE_COUPONS, SmartShopperSQLiteHelper.COLUMN_ID + " = ?", new String[] { String.valueOf(coupon.getId()) });
        database.close();
    }

    public void deleteExpiredCoupons(long currentDate) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(SmartShopperSQLiteHelper.TABLE_COUPONS, SmartShopperSQLiteHelper.COLUMN_EXPIRATIONDATE + " <= ?", new String[] { String.valueOf(currentDate) });
        database.close();
    }

    public void deleteAllCoupons() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(SmartShopperSQLiteHelper.TABLE_COUPONS, null, null);
        database.close();
    }
}
