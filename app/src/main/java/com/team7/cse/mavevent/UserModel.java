package com.team7.cse.mavevent;

import java.io.Serializable;
/**
 * Created by aadit on 4/22/2018.
 */

public class UserModel implements Serializable{

    private int id;
    private String userFName;
    private String userUName;
    private String userEmail;
    private String userAddress;
    private String userPassword;
    private int userType;

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userUName;
    }

    public void setUserName(String userUName) {
        this.userUName = userUName;
    }

    public String getUserFName() {
        return userFName;
    }

    public void setUserFName(String userFName) {
        this.userFName = userFName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

}
