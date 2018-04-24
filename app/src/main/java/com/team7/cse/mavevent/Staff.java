// Edit this
package com.team7.cse.mavevent;


import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by mayur on 4/11/2018.
 * Edited by Jonathon Kirkpatrick on 4/22/2018.
 */

public class Staff extends UserBaseModel implements Serializable {
	private ArrayList <Event> assignedEvents;
	
	Staff(){
		assignedEvents = new ArrayList<Event>();
	}
	
	public ArrayList getAssignedEvents(){
		ArrayList e = new ArrayList<Event>(assignedEvents);
		return e;
	}

	public void setAssignedEvents(ArrayList assignedEvents){

		this.assignedEvents = new ArrayList(assignedEvents);
	}
	
	public boolean addAssignedEvent(Event e){
		
		if(this.isAvailable(e)){
			assignedEvents.add(e);
			return true;
		}
		return false;
	}
	
	public boolean isAvailable(Event e){
		for(Event a : assignedEvents){
			if(a.timeOverlap(e))
				return false;
		}
		return true;
	}
}