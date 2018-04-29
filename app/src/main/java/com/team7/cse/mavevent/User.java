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
	User(String fName,
		 String lName,
		 String email,
		 String userName,
		 String password,
		 String phone,
		 String address,
		 String id,
		 int utaId){
		super(fName, lName, email, userName, password, phone, address, Integer.parseInt(id),1,utaId);
	}
	
	User(int id,
		 String fName,
		 String lName,
		 String email,
		 String userName,
		 String password,
		 String phone,
		 String address,
		 int utaId){
		super(fName, lName, email, userName, password, phone, address, id,1,utaId);
	}

	User(String fName,
		 String lName,
		 String email,
		 String userName,
		 String password,
		 String phone,
		 String address,
		 int id,
		 int utaId,
		 ArrayList<Event>events){
		super(fName, lName, email, userName, password, phone, address, id, events,1,utaId);;
	}

	User(){
		super();
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

	public void addEvent(Event e){
		events.add(e);
	}

}