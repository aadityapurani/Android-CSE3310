package com.team7.cse.mavevent;

import java.io.Serializable;

/**
 * Created by Jonathon Kirkpatrick on 4/24/2018.
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

enum MEAL_TYPE{
    DINNER,
    BREAKFAST,
    LUNCH
}


public class Food extends EventRecourse implements Serializable {
    public MEAL_TYPE meal_type;
    public VENUE venue;
    Food(){}
    Food(Food food){

        this.meal_type=food.meal_type;
        this.venue = food.venue;
        this.price=food.price;
        this.name=food.name;
    }
    Food(String name,int price,int venue,int meal_type){
        this.name = name;
        this.price = price;
        this.venue = VENUE.values()[venue];
        this.meal_type = MEAL_TYPE.values()[meal_type];
    }
}
