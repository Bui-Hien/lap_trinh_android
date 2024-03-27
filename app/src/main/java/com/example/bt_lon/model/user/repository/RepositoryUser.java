package com.example.bt_lon.model.user.repository;

import android.annotation.SuppressLint;

import com.example.bt_lon.model.user.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RepositoryUser {
    public final static User user;

    static {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = null;
        try {
            birthDate = dateFormat.parse("15/03/2003");
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        user = new User(1, "Bui", "Xuan Hien", "12345", true, birthDate, "Ha Noi", "07634337791", null);
    }
}
