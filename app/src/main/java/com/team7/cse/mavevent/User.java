// Edit this
package com.team7.cse.mavevent;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Created by mayur on 4/11/2018.
 * Edited by Jonathon Kirkpatrick on 4/22/2018.
 */

public class User extends UserBaseModel implements Serializable {
	private ArrayList <Event> reservedEvents;
	private String utaId;
	
	User(){
		reservedEvents = new ArrayList<Event>();
	}
	
	public ArrayList getReservedEvents(){
		ArrayList x = new ArrayList<Event>(reservedEvents);
		return x;
	}
	
	public String getUtaId(){
		return utaId;
	}
	
	public void setUtaId(String utaId){
		this.utaId = utaId;
	}
	
	// Actual functions
	
	/*
		removes 
	*/
	
	public boolean removeReservedEvent(Event e){
		for(int i=0; i < reservedEvents.size();i++){
			if(reservedEvents.get(i).sameAs(e)){
				reservedEvents.remove(reservedEvents.get(i));
				return true;
			}
		}
		return false;
	}
	
	public boolean updateEvent(Event e){
		if(removeReservedEvent(e)){
			this.addReservedEvent(e);
			return true;
		}
		return false;
	}
	
	public void addReservedEvent(Event e){
		reservedEvents.add(e);
	}
	
}