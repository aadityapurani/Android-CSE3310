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
}
