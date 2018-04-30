package com.team7.cse.mavevent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import com.team7.cse.mavevent.App;

import java.util.ArrayList;

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
    private static final String TABLE_RECOURSE = "event_resources";
    private static final String TABLE_MEAL = "meal_tbl";
    private static final String TABLE_VENUE = "venue_tbl";
    private static final String TABLE_DRINK = "drinks_tbl";
    private static final String TABLE_STAFFASSIGNMENT = "staffass_tbl";

    // Common column names
    private static final String KEY_USERID = "user_id";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_HALLID = "hall_id";
    private static final String KEY_EVENTSID = "event_id";
    private static final String KEY_EVENTRECOURSEID = "event_resource_id";      // NEED TO UPDATE
    private static final String KEY_MEALID = "meal_type_id";
    private static final String KEY_VENUEID = "venue";
    private static final String KEY_DRINKID = "drink_id";

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
    private static final String KEY_EVENTCID = "caterer_id";
    private static final String KEY_EVENTFORMALITY = "formality";
    private static final String KEY_EVENTSTATUS = "status";

    // Specific Columns for Event Resource Table
    private static final String KEY_RECOURSEID = "event_recourse_id";
    private static final String KEY_RECOURSENAME = "name";
    private static final String KEY_RECOURSEPRICE = "price";
    private static final String KEY_RECOURSEDRINK = "drink_name";
    private static final String KEY_RECOURSEVENUE = "venue_id";
    private static final String KEY_RECOURSEMEALTYPE = "meal_name";
    private static final String KEY_RECOURSEEVENT = "event_id";
    private static final String KEY_RECOURSEQUANTITY = "quantity";

    // Specific Columns for Meals Table
    private static final String KEY_MEALNAME = "name_meal";

    // Specific Columns for Venue Table
    private static final String KEY_VENUENAME = "name_venue";

    // Specific Columns for Drinks Table
    private static final String KEY_DRINKNAME = "name_drink";

    // Specific Columns for Staff Ass table
    private static final String KEY_STAFFASSEID = "event_id";
    private static final String KEY_STAFFASSID  = "staff_id";


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

    // Staff Assignment

    private static final String CREATE_TABLE_STAFFASS = "CREATE TABLE "
            + TABLE_STAFFASSIGNMENT + "(" + KEY_STAFFASSEID+ " INTEGER, "
            + KEY_STAFFASSID + " INTEGER, "
            + "CONSTRAINT fk_staffass FOREIGN KEY ("+KEY_STAFFASSEID+") REFERENCES "+TABLE_EVENTS+"("+KEY_EVENTSID+"))";

    // Create Table Event
    /**
     * Event Status
     * 0 - Pending
     * 1 - Accepted
     * 2 - Rejected
     * 3 - Deleted
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
            + "CONSTRAINT fk_hall FOREIGN KEY ("+KEY_EVENTHID+") REFERENCES "+TABLE_HALL+"("+KEY_HALLID+"),"//+ KEY_EVENTHID + "INTEGER,"//+ "CONSTRAINT fk_hall FOREIGN KEY ("+KEY_EVENTHID+") REFERENCES "+TABLE_HALL+"("+KEY_HALLID+"),"
            +"CONSTRAINT fk_user FOREIGN KEY ("+KEY_EVENTUID+") REFERENCES "+TABLE_HALL+"("+KEY_USERID+"))";//+ KEY_EVENTUID + "INTEGER"//"CONSTRAINT fk_user FOREIGN KEY ("+KEY_EVENTUID+") REFERENCES "+TABLE_HALL+"("+KEY_USERID+"))";
            //+ ")";

   /* Creating Resource Table */
    private static final String CREATE_TABLE_RECOURSE = "CREATE TABLE "
            + TABLE_RECOURSE + "(" + KEY_RECOURSEID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + KEY_RECOURSEPRICE + " REAL, "
            + KEY_RECOURSEQUANTITY + " INTEGER, "
            + KEY_RECOURSEDRINK + " TEXT, "
            + KEY_RECOURSEVENUE + " TEXT, "
            + KEY_RECOURSEMEALTYPE + " TEXT, "
            + KEY_RECOURSEEVENT + " INTEGER, "
            + "CONSTRAINT fk_hall FOREIGN KEY ("+KEY_RECOURSEEVENT+") REFERENCES "+TABLE_HALL+"("+KEY_HALLID+"))";


    // Will execute the query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_HALL);
        db.execSQL(CREATE_TABLE_EVENT);
        db.execSQL(CREATE_TABLE_RECOURSE);
        db.execSQL(CREATE_TABLE_STAFFASS);


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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RECOURSE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_STAFFASSIGNMENT);
        onCreate(sqLiteDatabase);
    }


    public void addHall(int id, String hallName,int price,int capacity,String address){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(KEY_HALLID, id);
        values.put(KEY_HALLNAME, hallName);
        values.put(KEY_HALLPRICE, price);
        values.put(KEY_HALLCAPACITY, capacity);
        values.put(KEY_ADDRESS, address);
        db.insert(TABLE_HALL,null,values);
        DatabaseManager.getInstance().closeDatabase();
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

    public void addNewEvent(Event event,int userID){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EVENTNAME,event.getName());
        values.put(KEY_EVENTTYPE,"Party");
        String startDates = event.getDate() + " " + Integer.toString(event.getTime())+":00";
        values. put(KEY_EVENTSTARTDATE,startDates);
        String endDates = event.getDate() + " " + Integer.toString(event.getTime()+event.getDuration())+":00";
        values. put(KEY_EVENTENDDATE,endDates);
        values.put(KEY_EVENTATTENDEES,event.getCapacity());
        if(event.getAlcohol())
            values.put(KEY_EVENTALCOHOL,1);
        else
            values.put(KEY_EVENTALCOHOL,0);
        if(event.getHall()!=null)
            values.put(KEY_EVENTHID,event.getHall().id);

        values.put(KEY_EVENTUID,userID);
        if(event.isFormal)
            values.put(KEY_EVENTFORMALITY,1);
        else
            values.put(KEY_EVENTFORMALITY,0);
        values.put(KEY_EVENTSTATUS,0);
        DatabaseManager.getInstance().closeDatabase();

    }

    public boolean addNewEntertainmentItem(String name, int quant, double price, int eventId){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_RECOURSEMEALTYPE, name);
        values.put(KEY_RECOURSEQUANTITY, quant);
        values.put(KEY_RECOURSEPRICE, price);
        values.put(KEY_RECOURSEEVENT, eventId);
        db.insert(TABLE_RECOURSE, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return true;
    }

    public void addNewVenue(String food, int eventId){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String query = "SELECT * from "+TABLE_RECOURSE+" WHERE "+KEY_RECOURSEVENUE +" IS NOT NULL AND "+KEY_RECOURSEEVENT+"="+eventId+";";
        Cursor cursor = db.rawQuery(query, null);
        if(!cursor.moveToFirst()) {
            ContentValues values = new ContentValues();
            values.put(KEY_RECOURSEVENUE, food);
            values.put(KEY_RECOURSEEVENT, eventId);
            db.insert(TABLE_RECOURSE, null, values);
        }else{
            String query1 = "UPDATE "+TABLE_RECOURSE+" SET "+KEY_RECOURSEVENUE+"=\""+food+"\" WHERE "+KEY_RECOURSEEVENT+"="+eventId+";";
            Cursor cursor1 = db.rawQuery(query1, null);
            cursor1.moveToFirst();
        }
        DatabaseManager.getInstance().closeDatabase();
    }

    public boolean addNewDrink(String name,int quant, int eventId){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_RECOURSEDRINK, name);
        values.put(KEY_RECOURSEQUANTITY, quant);
        values.put(KEY_RECOURSEEVENT, eventId);
        db.insert(TABLE_RECOURSE, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return true;

    }


    public boolean checkExistence(String username,long id,boolean isAUser){
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

    public UserBaseModel isValidUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_USERS + " WHERE " + KEY_USERNAME + " = \""
                + username + "\" AND " + KEY_PASSWORD + " = \"" + password + "\";";
        Cursor cursor = db.rawQuery(query, null);
        if(!cursor.moveToFirst()){
            return null;
        }
        UserBaseModel u =new UserBaseModel();
        u.setId(cursor.getInt(cursor.getColumnIndex(KEY_USERID)));
        u.setType(cursor.getInt(cursor.getColumnIndex(KEY_UTYPE)));


        return u;
    }

    public boolean eventExists(Event event){

        return false;
    }           //NEED TO CREATE

    // FOR LOGIN

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
    /* New Added*/

    public ArrayList<PendingEventBean> getPendingEvents(){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();


        String query = "SELECT * FROM "+TABLE_EVENTS+" WHERE "+KEY_EVENTSTATUS+"=0;";
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<PendingEventBean> eventObj=new ArrayList<PendingEventBean>();
        cursor.moveToFirst();
        int i=0;
        while (!cursor.isAfterLast()) {
            PendingEventBean peb=new PendingEventBean();
            peb.setId(cursor.getInt(cursor.getColumnIndex(KEY_EVENTSID)));
            peb.setEventName(cursor.getString(cursor.getColumnIndex(KEY_EVENTNAME)));
            eventObj.add(peb);
            i++;
            cursor.moveToNext();
        }
        DatabaseManager.getInstance().closeDatabase();
        return eventObj;
    }

    public ArrayList<PendingEventBean> getApprovedEvents(){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String query = "SELECT * FROM "+TABLE_EVENTS+" WHERE "+KEY_EVENTSTATUS+"=1;";
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<PendingEventBean> eventObj=new ArrayList<PendingEventBean>();
        cursor.moveToFirst();
        int i=0;
        while (!cursor.isAfterLast()) {
            PendingEventBean peb=new PendingEventBean();
            peb.setId(cursor.getInt(cursor.getColumnIndex(KEY_EVENTSID)));
            peb.setEventName(cursor.getString(cursor.getColumnIndex(KEY_EVENTNAME)));
            eventObj.add(peb);
            i++;
            cursor.moveToNext();
        }
        DatabaseManager.getInstance().closeDatabase();
        return eventObj;

    }


    /* User Accept Events */

    /* User can see his Events too */
    public ArrayList<PendingEventBean> getuEvents(int uid){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String query = "SELECT * FROM "+TABLE_EVENTS+" WHERE "+KEY_EVENTUID+"="+uid+";";
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<PendingEventBean> eventObj=new ArrayList<PendingEventBean>();
        cursor.moveToFirst();
        int i=0;
        while (!cursor.isAfterLast()) {
            PendingEventBean peb=new PendingEventBean();
            peb.setId(cursor.getInt(cursor.getColumnIndex(KEY_EVENTSID)));
            peb.setEventName(cursor.getString(cursor.getColumnIndex(KEY_EVENTNAME)));
            eventObj.add(peb);
            i++;
            cursor.moveToNext();
        }
        DatabaseManager.getInstance().closeDatabase();
        return eventObj;

    }



    /* Accept */
    public ArrayList<PendingEventBean> acceptPendingEvents(int id){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String query = "UPDATE "+TABLE_EVENTS+" SET "+KEY_EVENTSTATUS+"=1 WHERE "+KEY_EVENTSID+"="+id;
        Cursor cursor=db.rawQuery(query,null);
        cursor.moveToFirst();
        DatabaseManager.getInstance().closeDatabase();
        return getPendingEvents();

    }

    /* Reject */
    public ArrayList<PendingEventBean> rejectPendingEvents(int id){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String query = "UPDATE "+TABLE_EVENTS+" SET "+KEY_EVENTSTATUS+"=2 WHERE "+KEY_EVENTSID+"="+id;
        Cursor cursor=db.rawQuery(query,null);
        cursor.moveToFirst();
        DatabaseManager.getInstance().closeDatabase();
        return getPendingEvents();

    }

    /* Delete */

    public void deleteEventCaterer(int eid){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String query = "UPDATE "+TABLE_EVENTS+" SET "+KEY_EVENTSTATUS+"=3 WHERE "+KEY_EVENTSID+"="+eid;
        Cursor cursor=db.rawQuery(query,null);
        cursor.moveToFirst();
        DatabaseManager.getInstance().closeDatabase();

    }

    /* Drop */

    public void dropEventCaterer(int eid){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String query = "UPDATE "+TABLE_EVENTS+" SET "+KEY_EVENTSTATUS+"=0 WHERE "+KEY_EVENTSID+"="+eid;
        Cursor cursor=db.rawQuery(query,null);
        cursor.moveToFirst();
        DatabaseManager.getInstance().closeDatabase();

    }



    /* View Reserved Events at the end of User */
    /* Will retrieve the first event */

    public String[] viewReservedEvents(int id, String evName){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String query = "SELECT * FROM "+TABLE_EVENTS+" WHERE "+KEY_EVENTUID+"="+id+" AND "+KEY_EVENTNAME+"=\""+evName+"\";";
        String[] resEvents = new String[4];
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            resEvents[0] = cursor.getString(cursor.getColumnIndex(KEY_EVENTNAME));
            resEvents[1] = cursor.getString(cursor.getColumnIndex(KEY_EVENTSTARTDATE));
            resEvents[2] = cursor.getString(cursor.getColumnIndex(KEY_EVENTENDDATE));
            resEvents[3] = Integer.toString(cursor.getInt(cursor.getColumnIndex(KEY_EVENTSTATUS))); // Cuz it's string array

        }else{
            resEvents[0] = "N/A";
            resEvents[1] = "N/A";
            resEvents[2] = "N/A";
            resEvents[3] = "N/A";
        }

        return resEvents;

    }


    // Allocate Hall to Event
    public void allocateHall(int hallid, int EventID){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String query = "UPDATE "+TABLE_EVENTS+" SET "+KEY_EVENTHID+"="+hallid+" WHERE "+KEY_EVENTSID+"="+EventID+";";
        Cursor cursor=db.rawQuery(query,null);
        cursor.moveToFirst();
        DatabaseManager.getInstance().closeDatabase();
        //return true;
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

    // FOR LOOK UP
    public User getUser(int userId ) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_USERS + " WHERE " + KEY_USERID + " = \""
                + userId + "\";";
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

    public Staff getStaff(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_USERS + " WHERE " + KEY_USERID + " = \""
                + userId + "\";";
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

    public Caterer getCaterer(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_USERS + " WHERE " + KEY_USERID + " = \""
                + userId + "\";";
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

    public Event getEvent(int eventId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_EVENTS + " WHERE " + KEY_EVENTSID+ " = \""
                + eventId+ "\";";
        Cursor cursor = db.rawQuery(query, null);
        Event event = new Event();

        if(cursor.moveToFirst()){
            event.setId(cursor.getInt(cursor.getColumnIndex(KEY_EVENTSID)));
            Log.i("DatabaseHelper","*********************************************************************************************************************");
            event.setName(cursor.getString(cursor.getColumnIndex(KEY_EVENTNAME)));
            String date = cursor.getString(cursor.getColumnIndex(KEY_EVENTSTARTDATE));
            int mid=date.indexOf(" ");
            event.setDate(date.substring(0,mid-1));
            String test = date.substring(mid+1,mid+3);
            Log.i("DatabaseHelper","BEFORE****************************************" + test);
            if(test.substring(1).equals(":")){
                Log.i("DatabaseHelper","INSIDE");
                test = test.substring(0,1);
            }
            Log.i("DatabaseHelper","RESULT: " + test);
            int start = Integer.parseInt(test);
            event.setTime(start);
            date = cursor.getString(cursor.getColumnIndex(KEY_EVENTENDDATE));

            mid=date.indexOf(" ");
            test = date.substring(mid+1,mid+3);
            Log.i("DatabaseHelper","BEFORE" + test);
            if(test.substring(1).equals(":")){
                Log.i("DatabaseHelper","INSIDE");
                test = test.substring(0,1);
            }
            Log.i("DatabaseHelper","RESULT: " + test);
            int end = Integer.parseInt(test);
            event.setDuration(end-start);
            event.setCapacity(cursor.getInt(cursor.getColumnIndex(KEY_EVENTATTENDEES)));

            event.setHall(getHall(cursor.getInt(cursor.getColumnIndex(KEY_EVENTHID))));
            if(cursor.getInt(cursor.getColumnIndex(KEY_EVENTFORMALITY))==1)
                event.isFormal = true;
            else
                event.isFormal = false;
            if(cursor.getInt(cursor.getColumnIndex(KEY_EVENTSTATUS))==1)
                event.setAccepted(true);
            else
                event.setAccepted(false);
            return event;
        }
        return null;
    }

    public Hall getHall(int hallId){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_HALL + " WHERE " + KEY_HALLID + " = \""
                + hallId + "\";";
        Cursor cursor = db.rawQuery(query, null);

        //get the value
        Hall hall = new Hall();

        if(cursor.moveToFirst()) {
            hall.setCapacity(cursor.getInt(cursor.getColumnIndex(KEY_HALLCAPACITY)));
            hall.setName(cursor.getString(cursor.getColumnIndex(KEY_HALLNAME)));
            hall.setPrice(cursor.getInt(cursor.getColumnIndex(KEY_HALLPRICE)));
            return hall;
        }
        return null;
    }

    public void getUserEvents(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_EVENTS + " WHERE " + KEY_EVENTUID+ " = \""
                + user.getId()+ "\";";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
            user.addEvent(getEvent(cursor.getInt(cursor.getColumnIndex(KEY_EVENTSID))));
        while(cursor.moveToNext()){
            user.addEvent(getEvent(cursor.getInt(cursor.getColumnIndex(KEY_EVENTSID))));
        }
    }           //NEED TO CREATE    ***

    public void getCatererEvents(Caterer caterer){

    }   // NEED TO CREATE   ****

    public EntertainmentItem getEntertainmentItems(int entertainmentItemId,int eventRecourseId){
        return null;
    }   //NEED TO CREATE **** NEED TO PUT IN DATABASE

    public Drink getDrinks(int drinkId,int eventRecourseId){
        SQLiteDatabase db = this.getWritableDatabase();

        String queryDrink = "SELECT * from " + TABLE_DRINK + " WHERE " + KEY_DRINKID + " = \""
                + drinkId + "\";";
        Cursor cursorDrink = db.rawQuery(queryDrink, null);
        String queryRecourse = "SELECT * from " + TABLE_RECOURSE+ " WHERE " + KEY_RECOURSEID + " = \""
                + eventRecourseId+ "\";";
        Cursor cursorRecourse = db.rawQuery(queryRecourse, null);
        Drink drink = new Drink();
        if(cursorDrink.moveToFirst() && cursorRecourse.moveToFirst()){
            drink.name = cursorRecourse.getString(cursorRecourse.getColumnIndex(KEY_RECOURSENAME));
            drink.price = cursorRecourse.getInt(cursorRecourse.getColumnIndex(KEY_RECOURSEPRICE));
            drink.quantity = cursorRecourse.getInt(cursorRecourse.getColumnIndex(KEY_RECOURSEQUANTITY));
            String test = cursorDrink.getString(cursorDrink.getColumnIndex(KEY_DRINKNAME));
            if(test.equals("Alcoholic"))
                drink.alcoholic = true;
            else
                drink.alcoholic = false;

            return drink;
        }
        return null;
    }

    public Food getMeal(int mealId,int eventRecourseId){
        SQLiteDatabase db = this.getWritableDatabase();

        String queryDrink = "SELECT * from " + TABLE_MEAL + " WHERE " + KEY_MEALID + " = \""
                + mealId+ "\";";
        Cursor cursorDrink = db.rawQuery(queryDrink, null);
        String queryRecourse = "SELECT * from " + TABLE_RECOURSE+ " WHERE " + KEY_RECOURSEID + " = \""
                + eventRecourseId+ "\";";
        Cursor cursorRecourse = db.rawQuery(queryRecourse, null);
        Food food = new Food();
        if(cursorDrink.moveToFirst() && cursorRecourse.moveToFirst()){
            food.name = cursorRecourse.getString(cursorRecourse.getColumnIndex(KEY_RECOURSENAME));
            food.price = cursorRecourse.getInt(cursorRecourse.getColumnIndex(KEY_RECOURSEPRICE));
            food.quantity = cursorRecourse.getInt(cursorRecourse.getColumnIndex(KEY_RECOURSEQUANTITY));
            String test = cursorDrink.getString(cursorDrink.getColumnIndex(KEY_MEALNAME));
            if(test.equals("Breakfast"))
                food.mealType = 1;
            else if(test.equals("Lunch"))
                food.mealType = 2;
            else
                food.mealType = 3;
            return food;
        }
        return null;

    }

    public Venue getVenue(int venueId,int eventRecourseId){
        SQLiteDatabase db = this.getWritableDatabase();

        String queryDrink = "SELECT * from " + TABLE_VENUE + " WHERE " + KEY_VENUEID+ " = \""
                + venueId+ "\";";
        Cursor cursorDrink = db.rawQuery(queryDrink, null);
        String queryRecourse = "SELECT * from " + TABLE_RECOURSE+ " WHERE " + KEY_RECOURSEID + " = \""
                + eventRecourseId+ "\";";
        Cursor cursorRecourse = db.rawQuery(queryRecourse, null);
        Venue venue = new Venue();
        if(cursorDrink.moveToFirst() && cursorRecourse.moveToFirst()) {
            venue.name = cursorRecourse.getString(cursorRecourse.getColumnIndex(KEY_RECOURSENAME));
            venue.price = cursorRecourse.getInt(cursorRecourse.getColumnIndex(KEY_RECOURSEPRICE));
            venue.quantity = cursorRecourse.getInt(cursorRecourse.getColumnIndex(KEY_RECOURSEQUANTITY));
            String test = cursorDrink.getString(cursorDrink.getColumnIndex(KEY_VENUENAME));
            if(test.equals("Pizza")){
                venue.venue = Venue.VENUE.PIZZA;
            }
            else if(test.equals("French")){
                venue.venue = Venue.VENUE.FRENCH;
            }
            else if(test.equals("Chinese")){
                venue.venue = Venue.VENUE.CHINESE;
            }
            else if(test.equals("MEXICAN")){
                venue.venue = Venue.VENUE.MEXICAN;
            }
            else if(test.equals("Italian")){
                venue.venue = Venue.VENUE.ITALIAN;
            }
            else if(test.equals("American")){
                venue.venue = Venue.VENUE.AMERICAN;
            }
            else if(test.equals("Greek")){
                venue.venue = Venue.VENUE.GREEK;
            }
            else if(test.equals("Indian")){
                venue.venue = Venue.VENUE.INDIAN;
            }
            else{
                venue.venue = Venue.VENUE.JAPANESE;
            }
            return venue;

        }
        return null;
    }

    public ArrayList<Event> getAllEvents(){
        ArrayList<Event> events = new ArrayList<Event>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_EVENTS + ";";
        Cursor cursor = db.rawQuery(query,null);
        Event event;
        int event_id;
        if(cursor.moveToFirst()) {
            event_id = cursor.getInt(cursor.getColumnIndex(KEY_EVENTSID));
            event = getEvent(event_id);
            events.add(event);
        }
        while(cursor.moveToNext()){
            event_id = cursor.getInt(cursor.getColumnIndex(KEY_EVENTSID));
            event = getEvent(event_id);
            events.add(event);
        }

        return events;
    }


    /* Final Summary for Caterer */
    public String[] viewFinalSumm(int eventId){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String query ="select * from Events_tbl INNER JOIN Hall_tbl ON Events_tbl.hall_id=Hall_tbl.hall_id INNER JOIN Users_tbl ON Events_tbl.user_id=Users_tbl.user_id INNER JOIN event_resources ON Events_tbl.event_id=event_resources.event_id AND Events_tbl.event_id="+eventId+";";
        String[] resEvents = new String[7];
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            resEvents[0] = cursor.getInt(cursor.getColumnIndex(KEY_EVENTATTENDEES))+""; //Desired Attendees casted
            resEvents[1] = cursor.getString(cursor.getColumnIndex(KEY_HALLNAME));    // Where
            resEvents[2] = cursor.getInt(cursor.getColumnIndex(KEY_EVENTFORMALITY))+""; // Formality
            resEvents[3] = cursor.getString(cursor.getColumnIndex(KEY_FNAME)); // Booked By
            resEvents[4] = cursor.getString(cursor.getColumnIndex(KEY_RECOURSEVENUE)); // Like Venue fetching
            resEvents[5] = cursor.getString(cursor.getColumnIndex(KEY_EVENTSTARTDATE)); // Start Date
            resEvents[6] = cursor.getString(cursor.getColumnIndex(KEY_EVENTENDDATE)); // End Date

        }else{
            resEvents[0] = "N/A";
            resEvents[1] = "N/A";
            resEvents[2] = "N/A";
            resEvents[3] = "N/A";
            resEvents[4] = "N/A";
            resEvents[5] = "N/A";
            resEvents[6] = "N/A";
        }
        DatabaseManager.getInstance().closeDatabase();
        return resEvents;

    }

    public String[] viewFinalSumm2(int eventId){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String query ="SELECT * from Events_tbl INNER JOIN staffass_tbl ON Events_tbl.event_id=staffass_tbl.event_id AND Events_tbl.event_id="+eventId+" INNER JOIN users_tbl ON staffass_tbl.staff_id=users_tbl.user_id;";
        String[] resEvents = new String[1];
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            resEvents[0] = cursor.getString(cursor.getColumnIndex(KEY_FNAME))+""; //Staff First Name
        }else{
            resEvents[0] = "N/A";
        }
        DatabaseManager.getInstance().closeDatabase();
        return resEvents;

    }



    /* GetStaff as well as it's uid */

    public ArrayList<GetStaffBean> getAllStaff()
    {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        String query = "SELECT * FROM "+TABLE_USERS+" WHERE "+KEY_UTYPE+"=2;";
      //  Cursor cursor = db.rawQuery(query, null);
      //  cursor.moveToFirst();
    //    String query1 = "SELECT * FROM staff_view;";
        Cursor cursor1 = db.rawQuery(query,null);

        ArrayList<GetStaffBean> eventObj=new ArrayList<GetStaffBean>();
        cursor1.moveToFirst();
        int i=0;
        while (!cursor1.isAfterLast()) {
            GetStaffBean peb=new GetStaffBean();
            peb.setId(cursor1.getInt(cursor1.getColumnIndex(KEY_USERID)));
            peb.setFirstName(cursor1.getString(cursor1.getColumnIndex(KEY_FNAME)));
            eventObj.add(peb);
            i++;
            cursor1.moveToNext();
        }

        DatabaseManager.getInstance().closeDatabase();
        return eventObj;
    }

    public boolean assignStaffdB(ArrayList<Integer> assignedStaff,int eventID){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        for(int i=0; i< assignedStaff.size(); i++)
        {
            values.put(KEY_STAFFASSID, assignedStaff.get(i));
            values.put(KEY_STAFFASSEID, eventID);
            db.insert(TABLE_STAFFASSIGNMENT, null, values);
        }

        DatabaseManager.getInstance().closeDatabase();
        return true;

    }

    public ArrayList<Event> getAcceptedCatererEvents(int staff_id){
        ArrayList<Event> acceptedEvents = new ArrayList<Event>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_EVENTS + " WHERE " + KEY_EVENTCID + " = \""
                + staff_id + "\"" + KEY_EVENTSTATUS + "\"=1\";";
        Cursor cursor = db.rawQuery(query,null);
        Event event;
        int event_id;
        if(cursor.moveToFirst()) {
            event_id = cursor.getInt(cursor.getColumnIndex(KEY_EVENTSID));
            event = getEvent(event_id);
            acceptedEvents.add(event);
        }
        while(cursor.moveToNext()){
            event_id = cursor.getInt(cursor.getColumnIndex(KEY_EVENTSID));
            event = getEvent(event_id);
            acceptedEvents.add(event);
        }
        return acceptedEvents;
    }
/*
    0-caterer
    1-user
    2-staff

    ERROR
    -1 for unfound

 */
    public int getUserType(int userId){
        SQLiteDatabase db = this.getWritableDatabase();
        int userType;
        String query = "SELECT * from " + TABLE_USERS + " WHERE " + KEY_USERID + " = \""
                + userId + "\";";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndex(KEY_UTYPE));
        }
        return -1;
    }

}
    