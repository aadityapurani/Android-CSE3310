package com.team7.cse.mavevent;

import java.io.Serializable;

/**
 * Created by Jonathon Kirkpatrick on 4/24/2018.
 */

public class EntertainmentItem extends EventRecourse implements Serializable {
    public boolean adultEntertainment;
    EntertainmentItem(){
        adultEntertainment=false;
    }
}
