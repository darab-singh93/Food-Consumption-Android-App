package androidproject.example.com.food.Database.Tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidproject.example.com.food.POJO.Break;


public class BreakHelper extends SQLiteOpenHelper {

    private BreakHelper DBHelper;
    private SQLiteDatabase db;

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "BreakItem.db";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + BreakContract.BeneficiaryEntry.TABLE_NAME + " (" +
                BreakContract.BeneficiaryEntry._ID + " INTEGER NOT NULL," +
                BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_NAME + " TEXT NOT NULL, " +
                BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_EMAIL + " TEXT NOT NULL, " +
                BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_ADDRESS + " TEXT NOT NULL, " +
                BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_COUNTRY + " TEXT NOT NULL, " +
                BreakContract.BeneficiaryEntry.COLUMN_DATE + " TEXT NOT NULL " +

                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }
    //drop beneficiary table
    private String DROP_BENEFICIARY_TABLE = "DROP TABLE IF EXISTS " + BreakContract.BeneficiaryEntry.TABLE_NAME;

    public BreakHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //---opens the database---
    public BreakHelper open() throws SQLException
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

    public void addBeneficiary(Break beneficiary) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BreakContract.BeneficiaryEntry._ID, beneficiary.getId());
        values.put(BreakContract.BeneficiaryEntry.COLUMN_DATE, beneficiary.getDate());
        values.put(BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_NAME, beneficiary.getLocation());
        values.put(BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_EMAIL, beneficiary.getItem_1());
        values.put(BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_ADDRESS, beneficiary.getItem_2());
        values.put(BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_COUNTRY, beneficiary.getItem_3());

        db.insert(BreakContract.BeneficiaryEntry.TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                BreakContract.BeneficiaryEntry._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(BreakContract.BeneficiaryEntry.TABLE_NAME, //Table to query
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

    public List<Break> getAllBeneficiary() {
        // array of columns to fetch
        String[] columns = {
                BreakContract.BeneficiaryEntry._ID,
                BreakContract.BeneficiaryEntry.COLUMN_DATE,
                BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_NAME,
                BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_EMAIL,
                BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_ADDRESS,
                BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_COUNTRY
        };
        // sorting orders
        String sortOrder =
                BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_NAME + " ASC";
        List<Break> beneficiaryList = new ArrayList<Break>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(BreakContract.BeneficiaryEntry.TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Break beneficiary = new Break();
                beneficiary.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BreakContract.BeneficiaryEntry._ID))));
                beneficiary.setDate(cursor.getString(cursor.getColumnIndex(BreakContract.BeneficiaryEntry.COLUMN_DATE)));
                beneficiary.setLocation(cursor.getString(cursor.getColumnIndex(BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_NAME)));
                beneficiary.setItem_1(cursor.getString(cursor.getColumnIndex(BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_EMAIL)));
                beneficiary.setItem_2(cursor.getString(cursor.getColumnIndex(BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_ADDRESS)));
                beneficiary.setItem_3(cursor.getString(cursor.getColumnIndex(BreakContract.BeneficiaryEntry.COLUMN_BENEFICIARY_COUNTRY)));
                // Adding user record to list
                beneficiaryList.add(beneficiary);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return beneficiaryList;
    }

}