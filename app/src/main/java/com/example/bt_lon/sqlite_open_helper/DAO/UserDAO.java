package com.example.bt_lon.sqlite_open_helper.DAO;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.example.bt_lon.R;
import com.example.bt_lon.model.forgotPassword.ForgotPassword;
import com.example.bt_lon.model.question.Question;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserDAO {
    private SQLiteDatabase database;
    private DatabaseConnector dbHelper;

    public UserDAO(Context context) {
        dbHelper = new DatabaseConnector(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void fakeUser(Context context, String username) {
        Bitmap profileImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.cargo);
        User user = new User(1, "Bui Xuan Hien", username, "12345", true, new Date(), "Ha Noi", "0763433779", profileImage);
        this.storeUser(user);
        Log.d("fakeUser", "Inserted successfully");
    }

    public void close() {
        dbHelper.close();
    }

    public boolean storeUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

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

    public User checkUser(User user) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT user_id, username FROM users WHERE username LIKE ?", new String[]{user.getUsername()});

        User userQuery = null;

        if (cursor.moveToFirst()) {
            // Nếu có dữ liệu trong Cursor, tạo một đối tượng User từ dữ liệu và trả về
            int userId = cursor.getInt(0);
            String username = cursor.getString(1);
            // Khởi tạo đối tượng User từ dữ liệu
            user = new User(userId, user.getUsername());
        }

        cursor.close();

        return user; // Nếu user null, có nghĩa là không tìm thấy user với username đã cho.
    }


    public User getUser(String userName, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

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
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }


    public int updateInForUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();


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

    public int updatePasswordUser(User user, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("password", user.getPassword());
        // You can add other columns to update here if needed

        // Update the row in the users table where the username matches the provided username
        int rowsAffected = db.update("users", values, "username = ? and password = ?", new String[]{user.getUsername(), password});

        // Return the number of rows affected
        return rowsAffected;
    }

    public int updatePasswordUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("password", user.getPassword());

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
            Log.e("UserDAO bitmapToByteArray", e.toString());
        }
        return new byte[0];
    }

    public void fakeQuestionData(Context context, String username) {
        User user = new User(username);

        Question question1 = new Question(1, "1");
        Question question2 = new Question(2, "2");
        Question question3 = new Question(3, "3");

        ForgotPassword forgotPassword1 = new ForgotPassword(1, user, question1, "15/03/2003");
        ForgotPassword forgotPassword2 = new ForgotPassword(2, user, question2, "cho");
        ForgotPassword forgotPassword3 = new ForgotPassword(3, user, question3, "ngu");

        ForgotPasswordDAO forgotPasswordDAO = new ForgotPasswordDAO(context);
        forgotPasswordDAO.storeForgotPassword(context, forgotPassword1);
        forgotPasswordDAO.storeForgotPassword(context, forgotPassword2);
        forgotPasswordDAO.storeForgotPassword(context, forgotPassword3);
        Log.d("fakeQuestionData", "Inserted successfully");

    }
}
