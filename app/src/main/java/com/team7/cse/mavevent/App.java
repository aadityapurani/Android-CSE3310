package com.team7.cse.mavevent;

import android.app.Application;
import android.content.Context;
import android.provider.ContactsContract;
import com.team7.cse.mavevent.DatabaseManager;

import com.team7.cse.mavevent.DatabaseHelper;
import com.team7.cse.mavevent.User;

public class  App extends Application {
    private static Context context;
    private static DatabaseHelper dbHelper;
    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DatabaseHelper();
        DatabaseManager.initializeInstance(dbHelper);
        dbHelper.updateData();
    }

    public static Context getContext(){
        return context;
    }

}