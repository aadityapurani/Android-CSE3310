// Edit this
package com.team7.cse.mavevent;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by mayur on 4/11/2018.
 * Edited by Jonathon Kirkpatrick on 4/22/2018.
 */

public class Staff extends UserBaseModel implements Serializable {
	//private ArrayList <Event> assignedEvents;

	Staff(){
		super();
	}

	Staff(String fName,
		  String lName,
		  String email,
		  String userName,
		  String password,
		  String phone,
		  String address,
		  int id){
		super(fName, lName, email, userName, password, phone, address, id,2,-1);
	}

	Staff(String fName,
		  String lName,
		  String email,
		  String userName,
		  String password,
		  String phone,
		  String address,
		  int id,
		  ArrayList<Event> events){
		super(fName, lName, email, userName, password, phone, address, id,events,2,-1);
	}

	Staff(String fName,
		  String lName,
		  String email,
		  String userName,
		  String password,
		  String phone,
		  String address,
		  String id){
		super(fName, lName, email, userName, password, phone, address, Integer.parseInt(id),2,-1);
	}
	public boolean removeEvent(Event event){
		for(Event e : events){
			if(event.sameAs(e)){
				events.remove(e);
				return true;
			}
		}
		return false;
	}

	public boolean addEvent(Event e){
		if(this.isAvailable(e)){
			events.add(e);
			return true;
		}
		return false;
	}
}