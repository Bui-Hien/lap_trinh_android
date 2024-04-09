package com.example.bt_lon.user;

import java.util.Date;

public class User {
    private int user_id;
    private String firstname;
    private String lastname;
    private String password;
    private boolean sex;
    private Date year_of_birth;
    private String address;
    private String phone_number;

    public User() {
    }

    public User(int user_id, String firstname, String lastname, String password, boolean sex, Date year_of_birth, String address, String phone_number) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.sex = sex;
        this.year_of_birth = year_of_birth;
        this.address = address;
        this.phone_number = phone_number;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
}
