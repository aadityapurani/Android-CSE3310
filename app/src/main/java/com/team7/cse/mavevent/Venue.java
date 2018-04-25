package com.team7.cse.mavevent;

import java.io.Serializable;

/**
 * Created by jonki on 4/24/2018.
 */

enum VENUE{
    PIZZA,
    FRENCH,
    CHINESE,
    MEXICAN,
    ITALIAN,
    AMERICAN,
    GREEK,
    INDIAN,
    JAPANESE
}

public class Venue extends Food implements Serializable {
    public VENUE venue;
    Venue(int venue){
        this.venue = VENUE.values()[venue];
    }
	Venue(Venue venue){
		this.price = venue.price;
		this.name = venue.name;
		this.venue = venue.venue;
	}
    Venue(String name,
          int price,
          int venue){
        super(name,price);
        this.venue = VENUE.values()[venue];
    }
	Venue(String name,
		  String price,
		  String venue){
		super(name,price);
		this.venue = VENUE.values()[Integer.parseInt(venue)];
	}
}
