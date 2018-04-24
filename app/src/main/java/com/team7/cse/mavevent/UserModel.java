package com.team7.cse.mavevent;

import java.io.Serializable;
/**
 * Created by aadit on 4/22/2018.
 */

public class UserModel implements Serializable{

    private int id;
    private String userFName;
    private String userLName;
    private String userUName;
    private String userEmail;
    private String userAddress;
    private String userPhone;
    private String userPassword;
    private int userUta;
    private int userType;

    public int getUserUta() {
        return userUta;
    }

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

    public String getUserLName() {
        return userLName;
    }

    public void setUserLName(String userLName) {
        this.userLName = userLName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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

    public void setUserUta(int userUta) {
            this.userUta = userUta;
        }
    }
