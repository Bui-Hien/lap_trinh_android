package com.example.bt_lon.sqlite_open_helper.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.bt_lon.model.forgotPassword.ForgotPassword;
import com.example.bt_lon.model.question.Question;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ForgotPasswordDAO {
    private SQLiteDatabase database;
    private DatabaseConnector dbHelper;

    public ForgotPasswordDAO(Context context) {
        dbHelper = new DatabaseConnector(context);
    }

    public ForgotPasswordDAO(DatabaseConnector databaseConnector) {
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean storeForgotPassword(ForgotPassword forgotPassword) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("forgot_password_id", forgotPassword.getForgot_password_id());
        UserDAO userDAO = new UserDAO(ForgotPasswordDAO.this);
        values.put("user_id", userDAO.checkUser(forgotPassword.getUser()).getUser_id());
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


    public List<ForgotPassword> getAllForgotPasswordsByUserId(User user) {
        List<ForgotPassword> forgotPasswordList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

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
                QuestionDAO questionDAO = new QuestionDAO(ForgotPasswordDAO.this);
                Question question = new Question(question_id, String.valueOf(questionDAO.getQuestionById(question_id).getQuestion())); // Làm thế nào để lấy câu hỏi từ questionId, tùy thuộc vào cách bạn đã lưu dữ liệu
                ForgotPassword forgotPassword = new ForgotPassword(forgot_password_id, user, question, answer);
                forgotPasswordList.add(forgotPassword);
            } while (cursor.moveToNext());
        }

        // Đóng con trỏ cursor khi đã sử dụng xong
        cursor.close();

        // Trả về danh sách các đối tượng ForgotPassword
        return forgotPasswordList;
    }
}
