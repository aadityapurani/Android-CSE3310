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
        events = new ArrayList<Event>();
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

    private boolean addEvent(Event e){
        if(isAvailable(e)){
            events.add(e);
            return true;
        }
        return false;
    }

    /*public void addEventRecourse(EventRecourse er,Event e){
        if(Event.event)
    }*/

}