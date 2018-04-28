package com.team7.cse.mavevent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.team7.cse.mavevent.App;

import static java.sql.Types.NULL;


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
    private static final String TABLE_EVENTS = "Events_tbl";

    // Common column names
    private static final String KEY_USERID = "user_id";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_HALLID = "hall_id";
    private static final String KEY_EVENTSID = "event_id";

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

    // Specific Columns for Events Table
    private static final String KEY_EVENTNAME = "name";
    private static final String KEY_EVENTTYPE = "type";
    private static final String KEY_EVENTSTARTDATE = "bookedDateStart";
    private static final String KEY_EVENTENDDATE = "bookedDateEnd";
    private static final String KEY_EVENTATTENDEES = "desiredAttendees";
    private static final String KEY_EVENTMEAL = "meal";
    private static final String KEY_EVENTALCOHOL = "alco_or_not";
    private static final String KEY_EVENTHID = "hall_id";
    private static final String KEY_EVENTUID = "user_id";
    private static final String KEY_EVENTFORMALITY = "formality";
    private static final String KEY_EVENTSTATUS = "status";


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

    // Create Table Event
    /**
     * Event Status
     * 0 - Pending
     * 1 - Accepted
     * 2 - Rejected
     *
     */

    /**
     * Meal Type
     * 1 - Breakfast
     * 2 - Lunch
     * 3 - Dinner
     */

    private static final String CREATE_TABLE_EVENT = "CREATE TABLE "
            + TABLE_EVENTS + "(" + KEY_EVENTSID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + KEY_EVENTNAME + " TEXT,"
            + KEY_EVENTTYPE + " TEXT,"
            + KEY_EVENTSTARTDATE + " TEXT,"
            + KEY_EVENTENDDATE + " TEXT,"
            + KEY_EVENTATTENDEES + " INTEGER,"
            + KEY_EVENTMEAL + " INTEGER,"
            + KEY_EVENTALCOHOL + " INTEGER,"
            + KEY_EVENTHID + " INTEGER,"
            + KEY_EVENTUID + " INTEGER,"
            + KEY_EVENTFORMALITY + " INTEGER,"
            + KEY_EVENTSTATUS + " INTEGER,"
            + "CONSTRAINT fk_hall FOREIGN KEY ("+KEY_EVENTHID+") REFERENCES "+TABLE_HALL+"("+KEY_HALLID+"),"
            + "CONSTRAINT fk_user FOREIGN KEY ("+KEY_EVENTUID+") REFERENCES "+TABLE_HALL+"("+KEY_USERID+"))";



    // Will execute the query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_HALL);
        db.execSQL(CREATE_TABLE_EVENT);


    }

    public void updateData(){
        addHall(1,"Arlington hall",0,50,"Planet UTA");
        addHall(2,"KC hall",0,25,"Planet Arlington");
        addHall(3,"Shard hall",0,25,"Planet Tarrant");
        addHall(4,"Liberty hall",0,75,"Planet USA");
        addHall(5,"Maverick Hall",0,100,"Planet Texas");
    }

    // If newer version exists, start with fresh database
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HALL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
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
    public void addNewUser(UserBaseModel user){
        // SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUserName());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_ADDRESS, user.getAddress());
        values.put(KEY_FNAME, user.getFName());
        values.put(KEY_LNAME, user.getLName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PHONE, user.getPhone());
        values.put(KEY_UTAID, user.getUtaId());
        values.put(KEY_UTYPE, user.type);
        db.insert(TABLE_USERS, null, values);
        //db.close();
        DatabaseManager.getInstance().closeDatabase();
    }

    public boolean checkExistence(String username,int id,boolean isAUser){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * from " + TABLE_USERS + " WHERE " + KEY_USERNAME + " = \""
                + username + "\";";
        Cursor cursor = db.rawQuery(query, null);
        if(!cursor.moveToFirst()){
            String query2 = "SELECT * from " + TABLE_USERS + " WHERE " + KEY_UTAID+ " = \""
                    + id + "\";";
            Cursor cursor2 = db.rawQuery(query, null);
            return !cursor2.moveToFirst()||!isAUser;
        }
        return false;
    }

    // Login
    /*
        0 is invalid
        1 is user
        2 is cater
        3 is staff

     */
    public int isValidUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_USERS + " WHERE " + KEY_USERNAME + " = \""
                + username + "\" AND " + KEY_PASSWORD + " = \"" + password + "\";";
        Cursor cursor = db.rawQuery(query, null);
        if(!cursor.moveToFirst()){
            return -1;
        }
        return cursor.getInt(cursor.getColumnIndex(KEY_UTYPE));
    }


    /* Hacking it here*/
    public boolean requestEvent(String attendees, String mealType, String comboDate, String comboDate2, String isAlcohol, String formal, String userID, String eventName, String eventCategory){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EVENTNAME, eventName);
        values.put(KEY_EVENTTYPE, eventCategory);
        values.put(KEY_EVENTATTENDEES, attendees);
        values.put(KEY_EVENTMEAL, Integer.parseInt(mealType));    // int
        values.put(KEY_EVENTALCOHOL, Integer.parseInt(isAlcohol)); // int
        values.put(KEY_EVENTFORMALITY, Integer.parseInt(formal));  // int
        values.putNull(KEY_EVENTHID);
        values.put(KEY_EVENTUID, Integer.parseInt(userID)); //int
        values.put(KEY_EVENTSTATUS, 0); // Pending
        values.put(KEY_EVENTSTARTDATE, comboDate);
        values.put(KEY_EVENTENDDATE, comboDate2);
        db.insert(TABLE_EVENTS, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return true;
    }





    // User getter values
    public User getUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_USERS + " WHERE " + KEY_USERNAME + " = \""
                + username + "\" AND " + KEY_PASSWORD + " = \"" + password + "\";";
        Cursor cursor = db.rawQuery(query, null);

        //get the value
        User model = new User();

        if(cursor.moveToFirst()) {
            model.setId(cursor.getInt(cursor.getColumnIndex(KEY_USERID)));
            model.setFName(cursor.getString(cursor.getColumnIndex(KEY_FNAME)));
            model.setLName(cursor.getString(cursor.getColumnIndex(KEY_LNAME)));
            model.setUserName(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            model.setUtaId(cursor.getInt(cursor.getColumnIndex(KEY_UTAID)));
            model.setPhone(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
            //model.set(cursor.getInt(cursor.getColumnIndex(KEY_UTYPE)));
            model.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
        } else {
            model = null;
        }
        return model;
    }

    public Staff getStaff(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_USERS + " WHERE " + KEY_USERNAME + " = \""
                + username + "\" AND " + KEY_PASSWORD + " = \"" + password + "\";";
        Cursor cursor = db.rawQuery(query, null);

        //get the value
        Staff model = new Staff();

        if(cursor.moveToFirst()) {
            model.setId(cursor.getInt(cursor.getColumnIndex(KEY_USERID)));
            model.setFName(cursor.getString(cursor.getColumnIndex(KEY_FNAME)));
            model.setLName(cursor.getString(cursor.getColumnIndex(KEY_LNAME)));
            model.setUserName(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            //model.setUtaId(cursor.getInt(cursor.getColumnIndex(KEY_UTAID)));
            model.setPhone(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
            //model.set(cursor.getInt(cursor.getColumnIndex(KEY_UTYPE)));
            model.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
        } else {
            model = null;
        }
        return model;
    }

    public Caterer getCaterer(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_USERS + " WHERE " + KEY_USERNAME + " = \""
                + username + "\" AND " + KEY_PASSWORD + " = \"" + password + "\";";
        Cursor cursor = db.rawQuery(query, null);
        //get the value
        Caterer model = new Caterer();

        if(cursor.moveToFirst()) {
            model.setId(cursor.getInt(cursor.getColumnIndex(KEY_USERID)));
            model.setFName(cursor.getString(cursor.getColumnIndex(KEY_FNAME)));
            model.setLName(cursor.getString(cursor.getColumnIndex(KEY_LNAME)));
            model.setUserName(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            //model.setUtaId(cursor.getInt(cursor.getColumnIndex(KEY_UTAID)));
            model.setPhone(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
            //model.set(cursor.getInt(cursor.getColumnIndex(KEY_UTYPE)));
            model.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
        } else {
            model = null;
        }
        return model;
    }
}