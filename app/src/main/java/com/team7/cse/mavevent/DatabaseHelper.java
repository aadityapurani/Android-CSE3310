package com.team7.cse.mavevent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by aadit on 4/23/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    // Tag for adb logcat
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "mavseventdb";

    // Tables Name
    private static final String TABLE_USERS = "Users_tbl";

    // Common column names
    private static final String KEY_USERID = "user_id";
    private static final String KEY_CREATED_AT = "created_at";

    // Specific Columns for Users table
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_FNAME = "first_name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone_number";
    private static final String KEY_LNAME = "last_name";
    private static final String KEY_UTAID = "uta_id";
    private static final String KEY_UTYPE = "user-type";

    // Just a database creator
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create Table Users
    private static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USERS + "(" + KEY_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_USERNAME
            + " TEXT," + KEY_PASSWORD
            + " TEXT," +KEY_ADDRESS
            + " TEXT," +KEY_FNAME
            + " TEXT," + KEY_LNAME
            + " TEXT," +KEY_EMAIL
            + " TEXT," +KEY_PHONE
            + " TEXT," + KEY_UTAID
            + " INTEGER," + KEY_UTYPE
            +" INTEGER" + ")";

    // Will execute the query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_USERS);
    }

    // If newer version exists, start with fresh database
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(sqLiteDatabase);
    }

    // Register User Database Logic
    public void addNewUser(UserModel user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUserName());
        values.put(KEY_PASSWORD, user.getUserPassword());
        values.put(KEY_ADDRESS, user.getUserAddress());
        values.put(KEY_FNAME, user.getUserFName());
        values.put(KEY_LNAME, user.getUserLName());
        values.put(KEY_EMAIL, user.getUserEmail());
        values.put(KEY_PHONE, user.getUserPhone());
        values.put(KEY_UTAID, user.getUserUta());
        values.put(KEY_UTYPE, user.getUserType());
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    // Login
    public UserModel retrieveUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_USERS + " WHERE " + KEY_USERNAME + " = \""
                + username + "\" AND " + KEY_PASSWORD + " = \"" + password + "\";";
        Cursor cursor = db.rawQuery(query, null);

        UserModel model = new UserModel();

        if (cursor.moveToFirst()) {
            model.setId(cursor.getInt(cursor.getColumnIndex(KEY_USERID)));
            model.setUserFName(cursor.getString(cursor.getColumnIndex(KEY_FNAME)));
            model.setUserLName(cursor.getString(cursor.getColumnIndex(KEY_LNAME)));
            model.setUserName(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            model.setUserUta(cursor.getInt(cursor.getColumnIndex(KEY_UTAID)));
            model.setUserPhone(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
            model.setUserType(cursor.getInt(cursor.getColumnIndex(KEY_UTYPE)));
            model.setUserAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
        } else {
            model = null;
        }
        return model;
    }
}
