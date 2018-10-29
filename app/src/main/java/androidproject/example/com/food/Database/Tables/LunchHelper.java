package androidproject.example.com.food.Database.Tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidproject.example.com.food.POJO.Lunch;


public class LunchHelper extends SQLiteOpenHelper {

    private LunchHelper DBHelper;
    private SQLiteDatabase db;

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "Lunch.db";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + LunchContract.LunchEntry.TABLE_NAME + " (" +
                LunchContract.LunchEntry._ID + " INTEGER NOT NULL," +
                LunchContract.LunchEntry.COLUMN_LOCATION + " TEXT NOT NULL, " +
                LunchContract.LunchEntry.COLUMN_ITEM_1 + " TEXT NOT NULL, " +
                LunchContract.LunchEntry.COLUMN_ITEM_2 + " TEXT NOT NULL, " +
                LunchContract.LunchEntry.COLUMN_ITEM_3 + " TEXT NOT NULL, " +
                LunchContract.LunchEntry.COLUMN_DATE + " TEXT NOT NULL " +

                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }
    //drop beneficiary table
    private String DROP_BENEFICIARY_TABLE = "DROP TABLE IF EXISTS " + LunchContract.LunchEntry.TABLE_NAME;

    public LunchHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public LunchHelper open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        DBHelper.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db1, int oldVersion, int newVersion) {

        db1.execSQL(DROP_BENEFICIARY_TABLE);
        onCreate(db1);

    }


    public void addLunch(Lunch lunch) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LunchContract.LunchEntry._ID, lunch.getId());
        values.put(LunchContract.LunchEntry.COLUMN_DATE, lunch.getDate());
        values.put(LunchContract.LunchEntry.COLUMN_LOCATION, lunch.getLocation());
        values.put(LunchContract.LunchEntry.COLUMN_ITEM_1, lunch.getItem_1());
        values.put(LunchContract.LunchEntry.COLUMN_ITEM_2, lunch.getItem_2());
        values.put(LunchContract.LunchEntry.COLUMN_ITEM_3, lunch.getItem_3());

        db.insert(LunchContract.LunchEntry.TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                LunchContract.LunchEntry._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = LunchContract.LunchEntry.COLUMN_LOCATION + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(LunchContract.LunchEntry.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public List<Lunch> getAllLunch() {
        // array of columns to fetch
        String[] columns = {
                LunchContract.LunchEntry._ID,
                LunchContract.LunchEntry.COLUMN_DATE,
                LunchContract.LunchEntry.COLUMN_LOCATION,
                LunchContract.LunchEntry.COLUMN_ITEM_1,
                LunchContract.LunchEntry.COLUMN_ITEM_2,
                LunchContract.LunchEntry.COLUMN_ITEM_3
        };
        // sorting orders
        String sortOrder =
                LunchContract.LunchEntry.COLUMN_LOCATION + " ASC";
        List<Lunch> lunchList = new ArrayList<Lunch>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(LunchContract.LunchEntry.TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        if (c.moveToFirst()) {
            do {
                Lunch lunch = new Lunch();
                lunch.setId(Integer.parseInt(c.getString(c.getColumnIndex(LunchContract.LunchEntry._ID))));
                lunch.setDate(c.getString(c.getColumnIndex(LunchContract.LunchEntry.COLUMN_DATE)));
                lunch.setLocation(c.getString(c.getColumnIndex(LunchContract.LunchEntry.COLUMN_LOCATION)));
                lunch.setItem_1(c.getString(c.getColumnIndex(LunchContract.LunchEntry.COLUMN_ITEM_1)));
                lunch.setItem_2(c.getString(c.getColumnIndex(LunchContract.LunchEntry.COLUMN_ITEM_2)));
                lunch.setItem_3(c.getString(c.getColumnIndex(LunchContract.LunchEntry.COLUMN_ITEM_3)));
                // Adding user record to list
                lunchList.add(lunch);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return lunchList;
    }

}