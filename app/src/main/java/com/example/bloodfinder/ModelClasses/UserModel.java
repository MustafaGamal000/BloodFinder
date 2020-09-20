package com.example.bloodfinder.ModelClasses;

public class UserModel {
    private String username;
    private String email;
    private String phone;
    private String bloodGroup;

    public UserModel() {
    }

    public UserModel(String username, String email, String phone, String bloodGroup) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.bloodGroup = bloodGroup;
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
}
