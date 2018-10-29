package androidproject.example.com.food.Database.Tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


import androidproject.example.com.food.POJO.Dessert;

public class DessertHelper extends SQLiteOpenHelper {

    private DessertHelper DBHelper;
    private SQLiteDatabase db;

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "Desssert.db";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + DessertContract.DessertEntry.TABLE_NAME + " (" +
                DessertContract.DessertEntry._ID + " INTEGER NOT NULL," +
                DessertContract.DessertEntry.COLUMN_BENEFICIARY_NAME + " TEXT NOT NULL, " +
                DessertContract.DessertEntry.COLUMN_BENEFICIARY_EMAIL + " TEXT NOT NULL, " +
                DessertContract.DessertEntry.COLUMN_BENEFICIARY_ADDRESS + " TEXT NOT NULL, " +
                DessertContract.DessertEntry.COLUMN_BENEFICIARY_COUNTRY + " TEXT NOT NULL, " +
                DessertContract.DessertEntry.COLUMN_DATE + " TEXT NOT NULL " +

                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }

    //drop beneficiary table
    private String DROP_BENEFICIARY_TABLE = "DROP TABLE IF EXISTS " + DessertContract.DessertEntry.TABLE_NAME;

    public DessertHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //---opens the database---
    public DessertHelper open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    //---closes the database---
    public void close() {
        DBHelper.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db1, int oldVersion, int newVersion) {

        //Drop User Table if exist

        db1.execSQL(DROP_BENEFICIARY_TABLE);

        // Create tables again
        onCreate(db1);

    }


    //Method to create beneficiary records

    public void addDessert(Dessert dessert) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DessertContract.DessertEntry._ID, dessert.getId());
        values.put(DessertContract.DessertEntry.COLUMN_DATE, dessert.getDate());
        values.put(DessertContract.DessertEntry.COLUMN_BENEFICIARY_NAME, dessert.getLocation());
        values.put(DessertContract.DessertEntry.COLUMN_BENEFICIARY_EMAIL, dessert.getItem_1());
        values.put(DessertContract.DessertEntry.COLUMN_BENEFICIARY_ADDRESS, dessert.getItem_2());
        values.put(DessertContract.DessertEntry.COLUMN_BENEFICIARY_COUNTRY, dessert.getItem_3());

        db.insert(DessertContract.DessertEntry.TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                DessertContract.DessertEntry._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = DessertContract.DessertEntry.COLUMN_BENEFICIARY_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(DessertContract.DessertEntry.TABLE_NAME, //Table to query
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


    public List<Dessert> getAllDessert() {
        // array of columns to fetch
        String[] columns = {
                DessertContract.DessertEntry._ID,
                DessertContract.DessertEntry.COLUMN_DATE,
                DessertContract.DessertEntry.COLUMN_BENEFICIARY_NAME,
                DessertContract.DessertEntry.COLUMN_BENEFICIARY_EMAIL,
                DessertContract.DessertEntry.COLUMN_BENEFICIARY_ADDRESS,
                DessertContract.DessertEntry.COLUMN_BENEFICIARY_COUNTRY
        };
        // sorting orders
        String sortOrder =
                DessertContract.DessertEntry.COLUMN_BENEFICIARY_NAME + " ASC";
        List<Dessert> dessertList = new ArrayList<Dessert>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(DessertContract.DessertEntry.TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Dessert dessert = new Dessert();
                dessert.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DessertContract.DessertEntry._ID))));
                dessert.setDate(cursor.getString(cursor.getColumnIndex(DessertContract.DessertEntry.COLUMN_DATE)));
                dessert.setLocation(cursor.getString(cursor.getColumnIndex(DessertContract.DessertEntry.COLUMN_BENEFICIARY_NAME)));
                dessert.setItem_1(cursor.getString(cursor.getColumnIndex(DessertContract.DessertEntry.COLUMN_BENEFICIARY_EMAIL)));
                dessert.setItem_2(cursor.getString(cursor.getColumnIndex(DessertContract.DessertEntry.COLUMN_BENEFICIARY_ADDRESS)));
                dessert.setItem_3(cursor.getString(cursor.getColumnIndex(DessertContract.DessertEntry.COLUMN_BENEFICIARY_COUNTRY)));
                // Adding user record to list
                dessertList.add(dessert);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return dessertList;
    }

}