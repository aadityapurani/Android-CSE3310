// Edit this
package com.team7.cse.mavevent;


import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by mayur on 4/11/2018.
 * Edited by Jonathon Kirkpatrick on 4/22/2018.
 */

public class Staff extends UserBaseModel implements Serializable {
	private ArrayList assignedEvents;
	
	Staff(){
		assignedEvents = new ArrayList();
	}
	
	public ArrayList getAssignedEvents(){
		ArrayList e = new ArrayList(assignedEvents);
		return e;
	}
	
	/*public boolean setAssignedEvents(ArrayList assignedEvents){
		this.assignedEvents = new ArrayList(assignedEvents);
	}*/
	
	/*public boolean addAssignedEvent(Event e){
		
		if(this.isAvailable(e)){
			assignedEvents.add(e);
			return true;
		}
		return false;
	}*/
	
	/*public boolean isAvailable(Event e){
		for(Event a : assignedEvents){
			if(a.timeOverlaps(e))
				return false;
		}
		return true;
	}*/
	
	/*
		deletes all events that may be 
	*/
	
	/*public void overRide(Event e){
		for(Event a : assignedEvents){
			if(a.timeOverlaps(e)){
				assignedEvents.remove(a);
				e.removeStaff(this);
			}
		}
		assignedEvents.add(e);
	}*/
	
}