//***********CHANGE LATER***************
package com.team7.cse.mavevent;
//***********CHANGE LATER***************
//***********CHANGE LATER***************
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
	private int capacity;
	private Hall hall;
	public boolean isFormal;
	private int duration;
	private ArrayList <Drink> drinks;
	private ArrayList <EntertainmentItem> entertainmentItems;
	private Food food;
	private ArrayList <Staff> staffMembers;
	//public boolean isAccepted;
	private boolean accepted;
	
	Event(){
		drinks = new ArrayList<Drink>();
		entertainmentItems = new ArrayList<EntertainmentItem>();
		food = new Food();
		staffMembers = new ArrayList<Staff>();
		isFormal = true;
		time = -1;
		date = "";
		duration = -1;
		capacity = 0;
		accepted = false;
	}

	Event(
			int id,
		  String name,
		  int time,
		  String date,
		  int capacity,
		  Hall hall,
		  boolean isFormal,
		  int duration,
		  ArrayList<Drink>drinks,
		  ArrayList<EntertainmentItem>entertainmentItems,
		  Food food,
		  //ArrayList<Staff> staffMembers,
		  boolean accepted
		){
		this.id = id;
		this.name = name;
		this.time = time;
		this.date = date;
		this.capacity = capacity;
		this.hall = hall;
		this.isFormal = isFormal;
		this.duration = duration;
		this.drinks = new ArrayList<Drink>(drinks);
		this.entertainmentItems = new ArrayList<EntertainmentItem>(entertainmentItems);
		this.food = new Food(food);
		this.staffMembers = new ArrayList<Staff>();
		this.accepted = accepted;
	}
	
	Event(String id,
		  String name,
		  String time,
		  String date,
		  String capacity,
		  Hall hall,
		  String isFormal,
		  String duration,
		  //ArrayList<Drink>drinks,
		  //ArrayList<EntertainmentItem>entertainmentItems,
		  Food food,
		  //ArrayList<Staff> staffMembers,
		  boolean accepted
		){
		this.id = Integer.parseInt(id);
		this.name = name;
		this.time = Integer.parseInt(time);
		this.date = date;
		this.capacity = Integer.parseInt(capacity);
		this.hall = hall;
		this.isFormal = Boolean.parseBoolean(isFormal);
		this.duration = Integer.parseInt(duration);
		this.drinks = new ArrayList<Drink>(drinks);
		this.entertainmentItems = new ArrayList<EntertainmentItem>(entertainmentItems);
		this.food = new Food(food);
		this.staffMembers = new ArrayList<Staff>();
		this.accepted = Boolean.parseBoolean(accepted);
	}

	Event(
			int id,
			String name,
			int time,
			String date,
			int capacity,
			Hall hall,
			boolean isFormal,
			int duration,
			ArrayList<Drink>drinks,
			ArrayList<EntertainmentItem>entertainmentItems,
			Food food,
			ArrayList<Staff> staffMembers,
			boolean accepted
	){
		this.id = id;
		this.name = name;
		this.time = time;
		this.date = date;
		this.capacity = capacity;
		this.hall = hall;
		this.isFormal = isFormal;
		this.duration = duration;
		this.drinks = new ArrayList<Drink>(drinks);
		this.entertainmentItems = new ArrayList<EntertainmentItem>(entertainmentItems);
		this.food = new Food(food);
		this.staffMembers = new ArrayList<Staff>(staffMembers);
		this.accepted = accepted;
	}

	//STAFF MEMBERS
	public ArrayList getStaffMembers(){
		ArrayList x = new ArrayList<Staff>(staffMembers);
		return x;
	}
	
	public void setStaffMembers(ArrayList staffMembers){this.staffMembers = new ArrayList<Staff>(staffMembers);}

	//ACCEPTED
	public boolean getAccepted(){return accepted;}

	public void setAccepted(boolean accepted){
		if(accepted == false){staffMembers.clear();}
		this.accepted = accepted;
	}

	//ID
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	//NAME
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	//TIME
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

	//DATE
	public String getDate(){
		return date;
	}
	
	public boolean setDate(String date){
		if(date.length()==8){
			this.date = date;
			return true;
		}
		return false;
	}

	//CAPACITY
	public int getCapacity(){
		return capacity;
	}
	
	public boolean setCapacity(int capacity){
		if(capacity>=0){
			this.capacity = capacity;
			return true;
		}
		return false;
	}

	//HALL
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
	//DURATION
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

	//DRINKS
	public ArrayList<Drink>getDrinks(){return new ArrayList<Drink>(drinks);}

	public void addDrink(Drink drink){this.drinks.add(drink);}

	public boolean removeDrink(Drink drink){return drinks.remove(drink);}

	//ENTERTAINMENT ITEMS
	public ArrayList<EntertainmentItem> getEntertainmentItems(){return new ArrayList<EntertainmentItem>(entertainmentItems);}

	public void addEntertainmentItem(EntertainmentItem entertainmentItem){this.entertainmentItems.add(entertainmentItem);}

	public boolean removeEntertainmentItem(EntertainmentItem entertainmentItem){return this.entertainmentItems.remove(entertainmentItem);}

	//FOOD
	public Food getFood(){return food;}

	public void setFoood(Food food){this.food=food;}

	//STAFF
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
		for( Staff s : staffMembers){
			if(staff.sameAs(s))
				return true;
		}
		return false;
	}

	//ID
	public boolean sameAs(Event e){
		return e.getId()==this.getId();
	}

	//TIME OVER LAP
	public boolean timeOverlap(int time, String date, int duration){
		//MM/DD/YY
		//01234567
		// checks MM then DD then YY
		if(date.length()!=8)
			return false;
		for(int i=0;i<8;i+=3){
			this.date.substring(0);
			if(!date.substring(i,i+1).equals(this.date.substring(i,i+1)))
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
