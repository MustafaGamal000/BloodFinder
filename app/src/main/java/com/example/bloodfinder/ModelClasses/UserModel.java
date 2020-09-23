package com.example.bloodfinder.ModelClasses;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String uid;
    private String username;
    private String email;
    private String phone;
    private String bloodGroup;
    private String address;

    public UserModel() {
    }

    public UserModel(String uid, String username, String email, String phone, String bloodGroup, String address) {
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.bloodGroup = bloodGroup;
        this.address = address;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
