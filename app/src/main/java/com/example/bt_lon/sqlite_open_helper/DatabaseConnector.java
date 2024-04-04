package com.example.bt_lon.sqlite_open_helper;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.example.bt_lon.R;
import com.example.bt_lon.fragment.HomeFragment;
import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.category.Category;
import com.example.bt_lon.model.forgotPassword.ForgotPassword;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.question.Question;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DAO.QuestionDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.UserDAO;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    private static final String createTableProducts = "CREATE TABLE Products(product_id INTEGER PRIMARY KEY AUTOINCREMENT," + " category_id INTEGER, " + "product_name TEXT, " + "description TEXT," + "price REAL," + "image_product TEXT, " + "quantity INTEGER, " + "FOREIGN KEY(category_id) REFERENCES Categories(category_id));";

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
}
