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

import com.example.bt_lon.model.forgotPassword.ForgotPassword;
import com.example.bt_lon.model.question.Question;
import com.example.bt_lon.model.user.User;

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

    public User checkUser(User user) {
        SQLiteDatabase db = this.getReadableDatabase();

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
        if (user != null) {
            return user;
        } else {
            return null;
        }
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

    public int updatePasswordUser(User user, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("password", user.getPassword());
        // You can add other columns to update here if needed

        // Update the row in the users table where the username matches the provided username
        int rowsAffected = db.update("users", values, "username = ? and password = ?", new String[]{user.getUsername(), password});

        // Return the number of rows affected
        return rowsAffected;
    }

    public int updatePasswordUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("password", user.getPassword());

        int rowsAffected = db.update("users", values, "username = ?", new String[]{user.getUsername()});

        // Return the number of rows affected
        return rowsAffected;
    }


    public boolean storeQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("question", question.getQuestion());

        // Chèn dữ liệu vào bảng
        long newRowId = db.insert("Questions", null, values);

        // Kiểm tra xem việc chèn dữ liệu có thành công hay không
        if (newRowId != -1) {
            return true;
        } else {
            return false;
        }
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {"question_id", "question"};
        Cursor cursor = db.query("Questions", projection, null, null, null, null, null);

        // Duyệt qua các hàng kết quả và tạo đối tượng Question cho mỗi hàng
        if (cursor.moveToFirst()) {
            do {
                int questionId = cursor.getInt(0);
                String questionText = cursor.getString(1);

                // Tạo đối tượng Question và thêm vào danh sách
                Question question = new Question(questionId, questionText);
                questionList.add(question);
            } while (cursor.moveToNext());
        }

        // Đóng con trỏ cursor khi đã sử dụng xong
        cursor.close();

        // Trả về danh sách các câu hỏi
        return questionList;
    }

    public boolean deleteQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Xây dựng điều kiện WHERE để chỉ định câu hỏi cần xóa
        String selection = "question_id = ?";
        String[] selectionArgs = {String.valueOf(question.getQuestion_id())};

        // Thực hiện truy vấn xóa
        int deletedRows = db.delete("Questions", selection, selectionArgs);

        // Kiểm tra xem có hàng nào đã bị xóa không
        if (deletedRows > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean storeForgotPassword(ForgotPassword forgotPassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("forgot_password_id", forgotPassword.getForgot_password_id());
        values.put("user_id", forgotPassword.getUser().getUser_id()); // Assuming user_id is stored in ForgotPassword table
        values.put("question_id", forgotPassword.getQuestion().getQuestion_id());
        values.put("answer", forgotPassword.getAnswer());

        // Chèn dữ liệu vào bảng
        long newRowId = db.insert("ForgotPassword", null, values);

        // Kiểm tra xem việc chèn dữ liệu có thành công hay không
        if (newRowId != -1) {
            return true;
        } else {
            return false;
        }
    }

    public Question getQuestionById(int questionId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {"question_id", "question"};
        String selection = "question_id = ?";
        String[] selectionArgs = {String.valueOf(questionId)};
        Cursor cursor = db.query("Questions", projection, selection, selectionArgs, null, null, null);

        Question question = null;

        // Kiểm tra xem Cursor có dữ liệu không
        if (cursor.moveToFirst()) {
            // Nếu có dữ liệu trong Cursor, tạo một đối tượng Question từ dữ liệu và trả về
            String questionText = cursor.getString(1);
            // Khởi tạo đối tượng Question từ dữ liệu
            question = new Question(questionId, questionText);
        }

        cursor.close();

        return question; // Nếu question null, có nghĩa là không tìm thấy câu hỏi với questionId đã cho.
    }

    public List<ForgotPassword> getAllForgotPasswordsByUserId(User user) {
        List<ForgotPassword> forgotPasswordList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Câu lệnh SQL SELECT * để lấy tất cả các dữ liệu từ bảng ForgotPassword dựa trên user_id
        String[] projection = {"forgot_password_id", "user_id", "question_id", "answer"};
        String selection = "user_id = ?";
        String[] selectionArgs = {String.valueOf(user.getUser_id())};
        Cursor cursor = db.query("ForgotPassword", projection, selection, selectionArgs, null, null, null);

        // Duyệt qua các hàng kết quả và tạo đối tượng ForgotPassword cho mỗi hàng
        if (cursor.moveToFirst()) {
            do {
                int forgot_password_id = cursor.getInt(0);
                int user_id = cursor.getInt(1);
                int question_id = cursor.getInt(2);
                String answer = cursor.getString(3);

                // Tạo đối tượng Question và thêm vào danh sách
                Question question = new Question(question_id, String.valueOf(getQuestionById(question_id).getQuestion())); // Làm thế nào để lấy câu hỏi từ questionId, tùy thuộc vào cách bạn đã lưu dữ liệu
                ForgotPassword forgotPassword = new ForgotPassword(forgot_password_id, user, question, answer);
                forgotPasswordList.add(forgotPassword);
            } while (cursor.moveToNext());
        }

        // Đóng con trỏ cursor khi đã sử dụng xong
        cursor.close();

        // Trả về danh sách các đối tượng ForgotPassword
        return forgotPasswordList;
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
