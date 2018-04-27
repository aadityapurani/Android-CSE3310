package com.team7.cse.mavevent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.team7.cse.mavevent.App;


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
    private static final String TABLE_HALL = "Hall_tbl";

    // Common column names
    private static final String KEY_USERID = "user_id";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_HALLID = "hall_id";

    // Specific Columns for Users table
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_FNAME = "first_name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone_number";
    private static final String KEY_LNAME = "last_name";
    private static final String KEY_UTAID = "uta_id";
    private static final String KEY_UTYPE = "user_type";

    // Specific Columns for Hall table
    private static final String KEY_HALLNAME = "name";
    private static final String KEY_HALLPRICE = "price";
    private static final String KEY_HALLCAPACITY = "capacity";
    private static final String KEY_HALLADDRESS = "address";

    // Just a database creator
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(){
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
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

    // Create Table Hall
    private static final String CREATE_TABLE_HALL = "CREATE TABLE "
            + TABLE_HALL + "(" + KEY_HALLID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + KEY_HALLNAME + " TEXT,"
            + KEY_HALLPRICE + " INTEGER,"
            + KEY_HALLCAPACITY + " INTEGER," + KEY_HALLADDRESS
            + " TEXT" + ")";

    // Will execute the query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_HALL);
    }

    public void updateData(){
        addHall(1,"Arlington hall",0,50,"Planet UTA");
        addHall(2,"KC hall",0,25,"Planet Arlington");
        addHall(3,"Shard hall",0,25,"Planet Tarrant");
        addHall(4,"Liberty hall",0,75,"Planet Texas");
        addHall(5,"Maverick Hall",0,100,"Planet USA");
    }

    // If newer version exists, start with fresh database
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HALL);
        onCreate(sqLiteDatabase);
    }


    public void addHall(int id, String hallName,int price,int capacity,String address){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
       // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_HALLID, id);
        values.put(KEY_HALLNAME, hallName);
        values.put(KEY_HALLPRICE, price);
        values.put(KEY_HALLCAPACITY, capacity);
        values.put(KEY_ADDRESS, address);
        db.insert(TABLE_HALL,null,values);
        DatabaseManager.getInstance().closeDatabase();
    }

    public Hall retrieveHall(int id) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        //SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_HALL+ " WHERE " + KEY_HALLID+ " = \""
                + id+"\";";
        Cursor cursor = db.rawQuery(query, null);
        if(!cursor.moveToFirst()){
            return null;
        }
        Hall hall = new Hall(cursor.getInt(cursor.getColumnIndex(KEY_HALLID)),
                cursor.getInt(cursor.getColumnIndex(KEY_HALLCAPACITY)),
                cursor.getInt(cursor.getColumnIndex(KEY_HALLPRICE)),
                cursor.getString(cursor.getColumnIndex(KEY_HALLNAME))
                );

        return hall;
    }

    // Register User Database Logic
    public void addNewUser(UserModel user){
       // SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
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
        //db.close();
        DatabaseManager.getInstance().closeDatabase();
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