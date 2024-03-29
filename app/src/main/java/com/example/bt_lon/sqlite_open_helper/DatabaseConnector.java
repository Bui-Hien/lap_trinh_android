package com.example.bt_lon.sqlite_open_helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class DatabaseConnector  extends SQLiteOpenHelper{
    Context context;

    //database name
    private static String DB_NAME = "baitaplon_android.db";
    private static int DB_VERSION = 1;

    //các câu lệnh sql
    private static final String createTableUsers =
            "CREATE TABLE users(" +
                    "user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "full_name TEXT, " +
                    "username TEXT UNIQUE," +
                    " password TEXT," +
                    " sex INTEGER, " + //1 is true, 0 is false
                    "year_of_birth TEXT," +
                    "address TEXT, " +
                    "phone_number TEXT, " +
                    "profileImage BLOB);";

    private static final String createTableQuestions =
            "CREATE TABLE Questions(" +
                    "question_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "question TEXT);";

    private static final String createTableForgotPassword =
            "CREATE TABLE ForgotPassword(" +
                    "forgot_password_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id INTEGER, " +
                    "question_id INTEGER, " +
                    "answer TEXT, " +
                    "FOREIGN KEY(user_id) REFERENCES users(user_id)," +
                    " FOREIGN KEY(question_id) REFERENCES Questions(question_id));";

    private static final String createTableCategories =
            "CREATE TABLE Categories(" +
                    "category_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " category_name TEXT," +
                    " description TEXT);";

    private static final String createTableProducts =
            "CREATE TABLE Products(product_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " category_id INTEGER, " +
                    "product_name TEXT, " +
                    "description TEXT," +
                    "price REAL," +
                    "image_product BLOB, " +
                    "FOREIGN KEY(category_id) REFERENCES Categories(category_id));";

    private static final String createTableCarts =
            "CREATE TABLE Cart(" +
                    "cart_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "user_id INTEGER, " +
                    "product_id INTEGER, " +
                    "quantity INTEGER, " +
                    "FOREIGN KEY(user_id) REFERENCES users(user_id), " +
                    "FOREIGN KEY(product_id) REFERENCES Products(product_id));";

    private static final String createTablePurchaseOrders =
            "CREATE TABLE PurchaseOrders(" +
                    "purchase_order_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "product_id INTEGER, " +
                    "quantity INTEGER, " +
                    "purchase_time TEXT," + // "YYYY-MM-DD HH:MM:SS"
                    " cost REAL," +
                    " FOREIGN KEY(product_id) REFERENCES Products(product_id));";






    //các biến cần dùng
    private byte[] imageInBytes;
    private ByteArrayOutputStream byteArrayOutputStream;



    public DatabaseConnector(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    //chỉ chạy 1 lần đầu tiên
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableUsers);
        db.execSQL(createTableQuestions );
        db.execSQL(createTableForgotPassword );
        db.execSQL(createTableCategories );
        db.execSQL(createTableProducts );
        db.execSQL(createTableCarts  );
        db.execSQL(createTablePurchaseOrders  );
        Toast.makeText(context.getApplicationContext(), "Table created successfully", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//    public void storeData(ModelClass modelClass) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Bitmap imageToStoreBitMap = modelClass.getProfileImage();
//        byteArrayOutputStream = new ByteArrayOutputStream();
//        imageToStoreBitMap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//        imageInBytes = byteArrayOutputStream.toByteArray();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("userName", modelClass.getUserName());
//        contentValues.put("email", modelClass.getEmail());
//        contentValues.put("phone", modelClass.getPhone());
//        contentValues.put("password", modelClass.getPassword());
//        contentValues.put("image", imageInBytes);
//        long checkIfQueryRun = db.insert("LoginUser", null, contentValues);
//        if (checkIfQueryRun != -1) {
//            Toast.makeText(context.getApplicationContext(), "Table added successfully", Toast.LENGTH_LONG).show();
//            db.close();
//        } else {
//            Toast.makeText(context.getApplicationContext(), "Fail to add", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    public Cursor getUser() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        return db.rawQuery("SELECT * FROM LoginUser", null);
//    }
}
