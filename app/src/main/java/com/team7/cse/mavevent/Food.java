package com.team7.cse.mavevent;

import java.io.Serializable;

/**
 * Created by Jonathon Kirkpatrick on 4/24/2018.
 */
enum MEAL_TYPES{
    BREAKFAST, LUNCH, DINNER

}
public class Food extends EventRecourse implements Serializable {
    public int mealType;
    Food(){}
    Food(Food food){
        this.price=food.price;
        this.name=food.name;
    }

    Food(String name,int price){
        this.name = name;
        this.price = price;
    }

    Food(String name,String price){
        this.name = name;
        this.price = Integer.parseInt(price);
    }
}
