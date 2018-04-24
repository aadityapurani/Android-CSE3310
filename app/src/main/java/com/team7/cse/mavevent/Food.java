package com.team7.cse.mavevent;

import java.io.Serializable;

/**
 * Created by Jonathon Kirkpatrick on 4/24/2018.
 */

public class Food extends EventRecourse implements Serializable {
    Food(){}
    Food(Food food){
        this.price=food.price;
        this.name=food.name;
    }
    Food(String name,int price){
        this.name = name;
        this.price = price;
    }
}
