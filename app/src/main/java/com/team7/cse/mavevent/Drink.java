package com.team7.cse.mavevent;

import java.io.Serializable;

/**
 * Created by Joanthon Kirkpatrick on 4/24/2018.
 */

public class Drink extends EventRecourse implements Serializable {
    public boolean alcoholic;
    Drink(){
        alcoholic=false;
    }
    Drink(String name,
          int price,
          boolean alcoholic){
        this.name = name;
        this.price = price;
        this.alcoholic=alcoholic;
    }
	
	Drink(String name,
          String price,
          String alcoholic){
        this.name = name;
        this.price = Integer.parseInt(price);
        this.alcoholic = Boolean.parseBoolean(alcoholic);
        //this.alcholic=true;
    }
}
