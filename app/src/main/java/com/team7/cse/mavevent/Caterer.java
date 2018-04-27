// Edit this
package com.team7.cse.mavevent;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Created by mayur on 4/11/2018.
 * Edited by Jonathon Kirkpatrick on 4/22/2018.
 */

public class Caterer extends UserBaseModel implements Serializable {
    //private ArrayList<Event> events;

    Caterer(){
        super();
    }

    Caterer(String fName,
            String lName,
            String email,
            String userName,
            String password,
            String phone,
            String address,
            int id){
        super(fName, lName, email, userName, password, phone, address, id,0,-1);
    }

	Caterer(String fName,
            String lName,
            String email,
            String userName,
            String password,
            String phone,
            String address,
            String id){
        super(fName, lName, email, userName, password, phone, address, Integer.parseInt(id),0,-1);
    }
	
    Caterer(String fName,
            String lName,
            String email,
            String userName,
            String password,
            String phone,
            String address,
            int id,
            ArrayList<Event>events){
        super(fName, lName, email, userName, password, phone, address, id,events,0,-1);
    }

	/*
		rejects if already accepted by someone else && if it conflicts with other schedules
	*/

    public boolean acceptEvent(Event e){
        if(e.getAccepted()==true)
            return false;
        if(isAvailable(e)){
            e.setAccepted(true);
            events.add(e);
            return true;
        }
        return false;
    }

    /*
        removes the event from the event list
        it will remove ALL staff from event list
    */
    public void deleteEvent(Event e){
        events.remove(e);
        e.setAccepted(false);
    }
}