package com.example.bt_lon.model.user;

import android.graphics.Bitmap;

import java.util.Date;

public class RepositoryUser {
    private static User account = null;

    private RepositoryUser() {
    }

    // Method to create a new account
    public static void createNewAccount(int user_id, String full_name, String username, boolean sex, Date year_of_birth, String address, String phone_number, Bitmap profileImage) {
        if (account == null) {
            account = new User(user_id, full_name, username, sex, year_of_birth, address, phone_number, profileImage);
        }
    }

    // Method to create a new account
    public static void createNewAccount(User user) {
        if (account == null) {
            account = user;
        }
    }

    public static void createNewAccount(int user_id, String username) {
        if (account == null) {
            account = new User(user_id, username);
        }
    }

    public static void deleteAccount() {
        account = null;
    }

    public static User getAccount() {
        return account;
    }
}