package com.example.bt_lon.model.user;

import android.graphics.Bitmap;

import java.util.Date;

public class User {
    private int user_id;
    private String full_name;
    private String username;//ten dang nhap
    private String password;
    private boolean sex;
    private Date year_of_birth;
    private String address;
    private String phone_number;
    private Bitmap profileImage;
    public User() {
    }
    public User(int user_id, String username,Bitmap profileImage) {
        this.user_id = user_id;
        this.username = username;
        this.profileImage = profileImage;
    }
    public User(int user_id, String full_name, String username, String password, boolean sex, Date year_of_birth, String address, String phone_number, Bitmap profileImage) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.year_of_birth = year_of_birth;
        this.address = address;
        this.phone_number = phone_number;
        this.profileImage = profileImage;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Date getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(Date year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Bitmap getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Bitmap profileImage) {
        this.profileImage = profileImage;
    }
}
