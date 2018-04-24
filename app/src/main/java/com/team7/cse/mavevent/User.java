// Edit this
package com.team7.cse.mavevent;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Created by mayur on 4/11/2018.
 * Edited by Jonathon Kirkpatrick on 4/22/2018.
 */

public class User extends UserBaseModel implements Serializable {
	//private ArrayList <Event> reservedEvents;
	private String utaId;
	
	User(){
		events = new ArrayList<Event>();
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
	
	public boolean removeEvent(Event e){
		for(int i=0; i < events.size();i++){
			if(events.get(i).sameAs(e)){
				events.remove(events.get(i));
				return true;
			}
		}
		return false;
	}
	/*
	public boolean updateEvent(Event e){
		if(removeEvent(e)){
			this.addEvent(e);
			return true;
		}
		return false;
	}
	*/
	public void addEvent(Event e){
		events.add(e);
	}
}