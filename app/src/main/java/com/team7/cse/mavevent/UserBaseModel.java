package com.team7.cse.mavevent;

//import java.util.ArrayList;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * Created by mayur on 4/11/2018.
 * Edited by Jonathon Kirkpatrick on 4/22/2018.
 */

public class UserBaseModel implements Serializable {

    //private int id;
    private String fName;
    private String lName;
    private String email;
	private String userName;
    private String password;
	private String phone;
	private String address;
	private int id;
	public int type;
	protected ArrayList<Event> events;
	private long utaId;
	UserBaseModel(){
		events = new ArrayList<Event>();
	}

	UserBaseModel(String fName,
				  String lName,
				  String email,
				  String userName,
				  String password,
				  String phone,
				  String address,
				  String id,
	              String type){
		this.fName =fName;
		this.lName=lName;
		this.email=email;
		this.userName=userName;
		this.password=password;
		this.phone=phone;
		this.address=address;
		this.id = Integer.parseInt(id);
		events = new ArrayList<Event>();
		this.type = Integer.parseInt(type);

	}
	
	UserBaseModel(String fName,
				  String lName,
				  String email,
				  String userName,
				  String password,
				  String phone,
				  String address,
				  int id,
				  int type,
				  int utaId){
		this.fName =fName;
		this.lName=lName;
		this.email=email;
		this.userName=userName;
		this.password=password;
		this.phone=phone;
		this.address=address;
		this.id = id;
		this.type = type;
		events = new ArrayList<Event>();
	}
	
	UserBaseModel(String fName,
				  String lName,
				  String email,
				  String userName,
				  String password,
				  String phone,
				  String address,
				  int id,
				  ArrayList<Event>events,
				  int type,
				  int utaId
	){
		this.fName =fName;
		this.lName=lName;
		this.email=email;
		this.userName=userName;
		this.password=password;
		this.phone=phone;
		this.address=address;
		this.id = id;
		this.events = new ArrayList<Event>(events);
		this.type = type;
		this.utaId = utaId;
	}

	//private ArrayList reservedEvents = new ArrayList();
	public ArrayList<Event> getEvents(){return new ArrayList<Event>(events);}

/* public void setEvents(ArrayList events){this.events= new ArrayList<Event>(events);} */

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

	public String getPhone(){
		return phone;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public long getUtaId(){
		return utaId;
	}

	public void setUtaId(long utaId){
		this.utaId = utaId;
	}
	
	// actual functions

	/*
	checks too see if the event has any time interference with other classes
	 */

	public boolean isAvailable(Event event){
		for(Event e : events){
			if(e.timeOverlap(event))
				return false;
		}
		return true;
	}

	/*
		checks too see if they are the same people
	*/
	
	public boolean sameAs(UserBaseModel u){
		return u.getId() == this.getId();
	}
	
	/*
		checks to see if the userName and password are the same as the instances username and password
	*/
	
	public boolean isUser(String userName, String password){
		return userName == this.userName && password == this.password;
	}
	
	
	
	//public boolean isAUser(){}
	
	
	
	/*
	public ArrayList getUserReservedEvents(){
		return reservedEvents;
	}
	
	public 
	*/
}