package com.example.bt_lon.sqlite_open_helper;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.example.bt_lon.model.user.User;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseConnector extends SQLiteOpenHelper {
    Context context;

    //database name
    private static final String DB_NAME = "baitaplon_android2.db";
    private static final int DB_VERSION = 1;

    //các câu lệnh sql
    private static final String createTableUsers = "CREATE TABLE users(" + "user_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "full_name TEXT, " + "username TEXT UNIQUE," + "password TEXT," + "sex INTEGER, " + //1 is true, 0 is false
            "year_of_birth TEXT," + "address TEXT, " + "phone_number TEXT, " + "profileImage TEXT);";//TEXT nên thay

    private static final String createTableQuestions = "CREATE TABLE Questions(" + "question_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "question TEXT);";

    private static final String createTableForgotPassword = "CREATE TABLE ForgotPassword(" + "forgot_password_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "user_id INTEGER, " + "question_id INTEGER, " + "answer TEXT, " + "FOREIGN KEY(user_id) REFERENCES users(user_id)," + " FOREIGN KEY(question_id) REFERENCES Questions(question_id));";

    private static final String createTableCategories = "CREATE TABLE Categories(" + "category_id INTEGER PRIMARY KEY AUTOINCREMENT," + " category_name TEXT," + " description TEXT);";

    private static final String createTableProducts = "CREATE TABLE Products(product_id INTEGER PRIMARY KEY AUTOINCREMENT," + " category_id INTEGER, " + "product_name TEXT, " + "description TEXT," + "price REAL," + "image_product TEXT, " + "FOREIGN KEY(category_id) REFERENCES Categories(category_id));";

    private static final String createTableCarts = "CREATE TABLE Cart(" + "cart_id INTEGER PRIMARY KEY AUTOINCREMENT," + "user_id INTEGER, " + "product_id INTEGER, " + "quantity INTEGER, " + "FOREIGN KEY(user_id) REFERENCES users(user_id), " + "FOREIGN KEY(product_id) REFERENCES Products(product_id));";

    private static final String createTablePurchaseOrders = "CREATE TABLE PurchaseOrders(" + "purchase_order_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "product_id INTEGER, " + "quantity INTEGER, " + "purchase_time TEXT," + // "YYYY-MM-DD HH:MM:SS"
            " cost REAL," + " FOREIGN KEY(product_id) REFERENCES Products(product_id));";


    public DatabaseConnector(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    //chỉ chạy 1 lần đầu tiên
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableUsers);
        db.execSQL(createTableQuestions);
        db.execSQL(createTableForgotPassword);
        db.execSQL(createTableCategories);
        db.execSQL(createTableProducts);
        db.execSQL(createTableCarts);
        db.execSQL(createTablePurchaseOrders);
        Toast.makeText(context.getApplicationContext(), "Table created successfully", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean storeUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        byte[] imageInBytes = bitmapToByteArray(user.getProfileImage());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String birthDateString = formatter.format(user.getYear_of_birth());


        ContentValues values = new ContentValues();
        values.put("full_name", user.getFull_name());
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("sex", user.isMale() ? 1 : 0);
        values.put("year_of_birth", birthDateString);
        values.put("address", user.getAddress());
        values.put("phone_number", user.getPhone_number());
        values.put("profileImage", imageInBytes);
        // Add other columns here

        long qk = db.insert("users", null, values);
        db.close();
        if (qk > 0) {
            return true;
        } else {
            return false;
        }

    }


    public boolean checkUser(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT username FROM users WHERE username LIKE ?", new String[]{userName});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public User getUser(String userName, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{userName, password});

        User user = null;
        if (cursor.moveToFirst()) {
            int userId = cursor.getInt(0);
            String fullName = cursor.getString(1);
            boolean isMale = cursor.getInt(4) == 1;
            String birthDateString = cursor.getString(5);
            Date birthDate = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                birthDate = sdf.parse(birthDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String address = cursor.getString(6);
            String phoneNumber = cursor.getString(7);
            byte[] blobData = cursor.getBlob(8);
            Bitmap profileImage = getBitmapFromBlob(blobData);

            user = new User(userId,//0
                    fullName,//1
                    userName,//2
//                    password,//3
                    isMale,//4
                    birthDate,//5
                    address,//6
                    phoneNumber,//7
                    profileImage//8
            );
        }

        cursor.close();
        db.close();
        return user;
    }

    public int updateInForUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();


        byte[] imageInBytes = bitmapToByteArray(user.getProfileImage());


        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String birthDateString = formatter.format(user.getYear_of_birth());


        ContentValues values = new ContentValues();
        values.put("full_name", user.getFull_name());
        values.put("sex", user.isMale() ? 1 : 0);
        values.put("year_of_birth", birthDateString);
        values.put("address", user.getAddress());
        values.put("phone_number", user.getPhone_number());
        values.put("profileImage", imageInBytes);
        // Add other columns here

        return db.update("users", values, "username" + " = ?", new String[]{String.valueOf(user.getUsername())});


    }

    public int updatePasswordUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("password", user.getPassword());
        // You can add other columns to update here if needed

        // Update the row in the users table where the username matches the provided username
        int rowsAffected = db.update("users", values, "username = ?", new String[]{user.getUsername()});

        // Return the number of rows affected
        return rowsAffected;
    }


    public Bitmap getBitmapFromBlob(byte[] blob) {
        if (blob == null) return null;

        return BitmapFactory.decodeByteArray(blob, 0, blob.length);
    }

    public byte[] bitmapToByteArray(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            Toast.makeText(this.context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return new byte[0];
    }

}
