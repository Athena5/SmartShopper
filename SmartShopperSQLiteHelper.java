package hongfanwang.smartshopper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hong Fan on 4/20/2017.
 */

public class SmartShopperSQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COUPONS = "COUPONS";
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_STORENAME = "STORENAME";
    public static final String COLUMN_ADDRESS = "ADDRESS";
    public static final String COLUMN_EXPIRATIONDATE = "EXPIRATIONDATE";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";

    private static final String DATABASE_NAME = "SmartShopper.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_COUPONS + "( "
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_STORENAME + " TEXT, "
            + COLUMN_ADDRESS + " TEXT, "
            + COLUMN_EXPIRATIONDATE + " INTEGER, "
            + COLUMN_DESCRIPTION + " TEXT);";

    public SmartShopperSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUPONS);
        onCreate(db);
    }
}
