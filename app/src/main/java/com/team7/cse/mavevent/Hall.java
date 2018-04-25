package com.team7.cse.mavevent;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
//import java.time;
//import java.Time;

/*
 * Created by Jonathon Kirkpatrick on 4/23/2018.
 */
enum day_of_week{
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY
}

public class Hall implements Serializable {
    private int capacity;
    private int price;
    private String name;
    private ArrayList<Event>schedule;

    Hall() {
        schedule = new ArrayList<Event>();
    }

    Hall(int capacity,int price,String name){
        this.capacity=capacity;
        this.price=price;
        this.name= name;
    }

    Hall(String capacity,String price, String name){
        this.capacity = Integer.parseInt(capacity);
        this.price=Integer.parseInt(price);
        this.name=name;
    }

    public void setCapacity(int capcity) {this.capacity = capacity;}

    public int getCapacity(){return capacity;}

    public void setPrice(int price){this.price=price;}

    public int getPrice(){return price;}

    public void setName(String name){this.name=name;}

    public String getName(){return name;}

    public void setSchedule(ArrayList<Event> schedule){this.schedule=schedule;}


    public ArrayList<Event>getSchedule(){return schedule;}


    // actual functions
    /*
        adds the varieble to the schedule iff the event doesn't overlap with any other events

     */
    public boolean addEvent(Event event) {
        for(Event e : schedule){
            if(event.timeOverlap(e)){
                return false;
            }
        }
        schedule.add(event);
        return true;
    }

    public boolean removeEvent(Event event){
        for(Event e : schedule) {
           if(event.sameAs(e)) {
               schedule.remove(e);
               return true;
           }
        }
        return false;
    }
    /*
        checks if event is available for a particular time, date, and duration
     */
    public boolean isAvailable(int time,String date,int duration) {
        for(Event e : schedule) {
            if (e.timeOverlap(time,date,duration)) {
                return false;
            }
        }
        return true;
    }


    public boolean isAcceptable(int time,String date,int duration){
        int dayOfWeek = findDayOfWeek(date);
        int endTime;
        int startTime;
        // if day is sunday, friday or starday
        if(dayOfWeek==1||dayOfWeek==6||dayOfWeek==7){
            endTime = 26;
        }
        else{
            endTime = 23;
        }
        if(dayOfWeek == 1){
            startTime =12;
        }
        else{
            startTime = 7;
        }
        int totalTime = time+duration;
        if(time<startTime||totalTime>endTime){
            return false;
        }
        return true;
    }

    /*
    produces an intiger with and equivelent value to the day_of_week class
    */

    private int findDayOfWeek(String date) {
        Calendar c = Calendar.getInstance();
        int month = Integer.parseInt(date.substring(0,1));
        int day = Integer.parseInt(date.substring(3,4));
        int year = 2000 + Integer.parseInt(date.substring(6,7));
        Date d = new Date(year,month,day);
        c.setTime(d);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }


}

