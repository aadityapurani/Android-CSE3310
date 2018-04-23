//***********CHANGE LATER***************
package com.example.mayur.myfirstapplication;
//***********CHANGE LATER***************
//***********CHANGE LATER***************
package arraylist;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Created by mayur on 4/11/2018.
 * Edited by Jonathon Kirkpatrick on 4/22/2018.
 */

/*
	time is an intiger that represents the hours (minutes not included)
*/
 
public class Event implements Serializable{
	private int id;
	private String name;
	private int time;
	private String date;
	private int capacity
	private Hall hall;
	public boolean isFormal;
	private int duration;
	private ArrayList eventRecourses;
	private ArrayList staffMembers;
	public boolean isAccepted;
	
	Event(){
		isFormal = true;
		time = -1;
		date = "";
		duration = -1;
		capacity = 0;
		isAccepted = 0;
	}
	
	public ArrayList getStaffMembers(){
		ArrayList x = new ArrayList(staffMembers);
		return x;
	}
	
	public void setStaffMembers(ArrayList staffMembers){
		this.staffMembers = new ArrayList(staffMembers);
	}
	
	public boolean addStaffMember(Staff staff){
		if(this.alreadyAssigned(staff)){
			return false;
		}
		this.staffMembers.add(staff);
		return true;
	}
	
	public void removeStaffMember(Staff staff){
		staffMembers.remove(staff);
	}
	
	public boolean alreadyAssigned(Staff staff){
		for( Staff a : staffMembers){
			if(a.sameAs(staff)
				return true;
		}
		return false;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getTime(){
		return time;
	}
	
	public boolean setTime(int time){
		if(time>=0&&time<24){
			this.time = time;
			return true;
		}
		return false;
	}
	
	public String getDate(){
		return date;
	}
	
	public boolean setDate(String date){
		if(date.size()==8){
			this.date = date;
			return true;
		}
		return false;
	}
	
	public int getCapacity(){
		return capacity;
	}
	
	public boolean setCapacity(int capacity){
		if(capacity>0){
			this.capacity = capacity;
			return true;
		}
		return false;
	}
	
	public Hall getHall(){
		return hall;
	}
	
	public boolean setHall(Hall hall){
		if(time==-1 || date.length()==0 || duration == -1){
			return false;
		}
		else if(hall.isAvailable(time,date,duration)){
			this.hall = hall;
			return true;
		}
		return false;
	}
	
	public int getDuration(){
		return duration;
	}
	
	public boolean setDuration(int duration){
		if(duration>0){
			this.duration = duration;
			return true;
		}
		return false;
	}
	
	public ArrayList getEventRecourses(){
		ArrayList X = new ArrayList(eventRecourses);
		return X;
	}
	
	public void setEventRecourses(ArrayList eventRecourses){
		this.eventRecourses = new ArrayList(eventRecourses);
	}
	
	public void addEventRecourse(EventRecourse e){
		eventRecourses.add(e);
	}
	
	public boolean sameAs(Event e){
		return e.getId()==this.getId();
	}
	
	public boolean timeOverlap(int time, String date, int duration){
		//MM/DD/YY
		//01234567
		// checks MM then DD then YY
		for(int i=0;i<8;i+=3){
			if(date[i]!=this.date.[i] || date[i+1]!=this.date[i+1])
				return false;
		}
		
		// checks if times overlap
		if(this.time>time){
			if(this.time-time>duration)
				return true;
		}
		else{
			if(time-this.time>this.duration)
				return true;
		}
		// it 
		return false;
	}
	
	public boolean timeOverlap(Event e){
		return this.timeOverlap(e.time,e.date,e.duration);
	}
}