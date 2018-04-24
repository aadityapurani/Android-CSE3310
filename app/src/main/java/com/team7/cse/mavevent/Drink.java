package com.team7.cse.mavevent;

import java.io.Serializable;

/**
 * Created by Joanthon Kirkpatrick on 4/24/2018.
 */

public class Drink extends EventRecourse implements Serializable {
    public boolean alcholic;
    Drink(){
        alcholic=false;
    }
    Drink(String name,
          int price,
          boolean alcholic){
        this.name = name;
        this.price = price;
        this.alcholic=alcholic;
    }
}
