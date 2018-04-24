package com.team7.cse.mavevent;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Created by Jonathon Kirkpatrick on 4/24/2018.
 */

public class Calender implements Serializable {
   private ArrayList<Event> events;

   Calender(){events = new ArrayList<Event>();}

   Calender(ArrayList<Event>events){this.events = new ArrayList<Event>(events);}

   public ArrayList<Event> getEvents(){return new ArrayList<Event>(events);}

   public boolean removeEvent(Event event){
      for(Event e : events){
         if(event.sameAs(e)){
            events.remove(e);
            return true;
         }
      }
      return false;
   }

   public void addEvent(Event e){
      events.add(e);
   }

   public void addEvents(ArrayList<Event> events){
      for(Event e: events){
         this.events.add(e);
      }
   }
}
