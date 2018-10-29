package androidproject.example.com.food.Database.Tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidproject.example.com.food.POJO.Dinner;



public class DinnerHelper extends SQLiteOpenHelper {

    private BreakHelper DBHelper;
    private SQLiteDatabase db;

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "Dinner.db";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + DinnerContract.DinnerEntry.TABLE_NAME + " (" +
                DinnerContract.DinnerEntry._ID + " INTEGER NOT NULL," +
                DinnerContract.DinnerEntry.COLUMN_LOCATION + " TEXT NOT NULL, " +
                DinnerContract.DinnerEntry.COLUMN_ITEM_1 + " TEXT NOT NULL, " +
                DinnerContract.DinnerEntry.COLUMN_ITEM_2 + " TEXT NOT NULL, " +
                DinnerContract.DinnerEntry.COLUMN_ITEM_3 + " TEXT NOT NULL, " +
                DinnerContract.DinnerEntry.COLUMN_DATE + " TEXT NOT NULL " +

                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }
    //drop beneficiary table
    private String DROP_MY_TABLE = "DROP TABLE IF EXISTS " + DinnerContract.DinnerEntry.TABLE_NAME;

    public DinnerHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //---opens the database---
    public DinnerHelper open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db1, int oldVersion, int newVersion) {

        //Drop User Table if exist

        db1.execSQL(DROP_MY_TABLE);

        // Create tables again
        onCreate(db1);

    }


    //Method to create beneficiary records

    public void addDinner(Dinner dinner) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DinnerContract.DinnerEntry._ID, dinner.getId());
        values.put(DinnerContract.DinnerEntry.COLUMN_DATE, dinner.getDate());
        values.put(DinnerContract.DinnerEntry.COLUMN_LOCATION, dinner.getLocation());
        values.put(DinnerContract.DinnerEntry.COLUMN_ITEM_1, dinner.getItem_1());
        values.put(DinnerContract.DinnerEntry.COLUMN_ITEM_2, dinner.getItem_2());
        values.put(DinnerContract.DinnerEntry.COLUMN_ITEM_3, dinner.getItem_3());

        db.insert(DinnerContract.DinnerEntry.TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                LunchContract.LunchEntry._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = DinnerContract.DinnerEntry.COLUMN_LOCATION + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(DinnerContract.DinnerEntry.TABLE_NAME, //Table to query
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





    public List<Dinner> getAllDinner() {
        // array of columns to fetch
        String[] columns = {
                DinnerContract.DinnerEntry._ID,
                DinnerContract.DinnerEntry.COLUMN_DATE,
                DinnerContract.DinnerEntry.COLUMN_LOCATION,
                DinnerContract.DinnerEntry.COLUMN_ITEM_1,
                DinnerContract.DinnerEntry.COLUMN_ITEM_2,
                DinnerContract.DinnerEntry.COLUMN_ITEM_3
        };
        // sorting orders
        String sortOrder =
                DinnerContract.DinnerEntry.COLUMN_LOCATION + " ASC";
        List<Dinner> lunchList = new ArrayList<Dinner>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(DinnerContract.DinnerEntry.TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Dinner dinner = new Dinner();
                dinner.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DinnerContract.DinnerEntry._ID))));
                dinner.setDate(cursor.getString(cursor.getColumnIndex(DinnerContract.DinnerEntry.COLUMN_DATE)));
                dinner.setLocation(cursor.getString(cursor.getColumnIndex(DinnerContract.DinnerEntry.COLUMN_LOCATION)));
                dinner.setItem_1(cursor.getString(cursor.getColumnIndex(DinnerContract.DinnerEntry.COLUMN_ITEM_1)));
                dinner.setItem_2(cursor.getString(cursor.getColumnIndex(DinnerContract.DinnerEntry.COLUMN_ITEM_2)));
                dinner.setItem_3(cursor.getString(cursor.getColumnIndex(DinnerContract.DinnerEntry.COLUMN_ITEM_3)));
                // Adding user record to list
                lunchList.add(dinner);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return lunchList;
    }

}
