package com.team7.cse.mavevent;
import java.util.ArrayList;
import java.io.Serializable;
/**
 * Created by jonki on 4/23/2018.
 */

public class EventRecourse implements Serializable{
    public String name;
    public int price;
    EventRecourse(){}
    EventRecourse(String name,
                  int price){
        this.name = name;
        this.price = price;
    }
}
