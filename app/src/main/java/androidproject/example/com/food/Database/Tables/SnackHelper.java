package androidproject.example.com.food.Database.Tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


import androidproject.example.com.food.POJO.Snack;

public class SnackHelper extends SQLiteOpenHelper {

    private SnackHelper DBHelper;
    private SQLiteDatabase db;

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "Snack.db";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + SnackContract.SnackEntry.TABLE_NAME + " (" +
                SnackContract.SnackEntry._ID + " INTEGER NOT NULL," +
                SnackContract.SnackEntry.COLUMN_BENEFICIARY_NAME + " TEXT NOT NULL, " +
                SnackContract.SnackEntry.COLUMN_BENEFICIARY_EMAIL + " TEXT NOT NULL, " +
                SnackContract.SnackEntry.COLUMN_BENEFICIARY_ADDRESS + " TEXT NOT NULL, " +
                SnackContract.SnackEntry.COLUMN_BENEFICIARY_COUNTRY + " TEXT NOT NULL, " +
                SnackContract.SnackEntry.COLUMN_DATE + " TEXT NOT NULL " +

                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }
    //drop beneficiary table
    private String DROP_BENEFICIARY_TABLE = "DROP TABLE IF EXISTS " + BreakContract.BeneficiaryEntry.TABLE_NAME;

    public SnackHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //---opens the database---
    public SnackHelper open() throws SQLException
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

        db1.execSQL(DROP_BENEFICIARY_TABLE);

        // Create tables again
        onCreate(db1);

    }


    //Method to create beneficiary records

    public void addSnack(Snack snack) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SnackContract.SnackEntry._ID, snack.getId());
        values.put(SnackContract.SnackEntry.COLUMN_DATE, snack.getDate());
        values.put(SnackContract.SnackEntry.COLUMN_BENEFICIARY_NAME, snack.getLocation());
        values.put(SnackContract.SnackEntry.COLUMN_BENEFICIARY_EMAIL, snack.getItem_1());
        values.put(SnackContract.SnackEntry.COLUMN_BENEFICIARY_ADDRESS, snack.getItem_2());
        values.put(SnackContract.SnackEntry.COLUMN_BENEFICIARY_COUNTRY, snack.getItem_3());

        db.insert(SnackContract.SnackEntry.TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                SnackContract.SnackEntry._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = SnackContract.SnackEntry.COLUMN_BENEFICIARY_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(SnackContract.SnackEntry.TABLE_NAME, //Table to query
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





    public List<Snack> getAllSnack() {
        // array of columns to fetch
        String[] columns = {
                SnackContract.SnackEntry._ID,
                SnackContract.SnackEntry.COLUMN_DATE,
                SnackContract.SnackEntry.COLUMN_BENEFICIARY_NAME,
                SnackContract.SnackEntry.COLUMN_BENEFICIARY_EMAIL,
                SnackContract.SnackEntry.COLUMN_BENEFICIARY_ADDRESS,
                SnackContract.SnackEntry.COLUMN_BENEFICIARY_COUNTRY
        };
        // sorting orders
        String sortOrder =
                SnackContract.SnackEntry.COLUMN_BENEFICIARY_NAME + " ASC";
        List<Snack> snackList = new ArrayList<Snack>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(SnackContract.SnackEntry.TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Snack snack = new Snack();
                snack.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SnackContract.SnackEntry._ID))));
                snack.setDate(cursor.getString(cursor.getColumnIndex(SnackContract.SnackEntry.COLUMN_DATE)));
                snack.setLocation(cursor.getString(cursor.getColumnIndex(SnackContract.SnackEntry.COLUMN_BENEFICIARY_NAME)));
                snack.setItem_1(cursor.getString(cursor.getColumnIndex(SnackContract.SnackEntry.COLUMN_BENEFICIARY_EMAIL)));
                snack.setItem_2(cursor.getString(cursor.getColumnIndex(SnackContract.SnackEntry.COLUMN_BENEFICIARY_ADDRESS)));
                snack.setItem_3(cursor.getString(cursor.getColumnIndex(SnackContract.SnackEntry.COLUMN_BENEFICIARY_COUNTRY)));
                // Adding user record to list
                snackList.add(snack);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return snackList;
    }

}
