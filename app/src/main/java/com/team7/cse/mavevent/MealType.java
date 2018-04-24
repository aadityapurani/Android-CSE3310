package com.team7.cse.mavevent;

import java.io.Serializable;

/**
 * Created by jonki on 4/24/2018.
 */

enum MEAL_TYPE{
    DINNER,
    BREAKFAST,
    LUNCH
}


public class MealType extends Food implements Serializable {
    public MEAL_TYPE mealType;
    MealType(String name,
             int price,
             int mealType){
        super(name, price);
        this.mealType = MEAL_TYPE.values()[mealType];
    }

    MealType(int mealType){
        this.mealType = MEAL_TYPE.values()[mealType];
    }

    MealType(){}

}
