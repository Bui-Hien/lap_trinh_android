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

import com.example.bt_lon.R;
import com.example.bt_lon.model.category.Category;
import com.example.bt_lon.model.product.Product;
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

public class QuestionDAO {
    private SQLiteDatabase database;
    private DatabaseConnector dbHelper;

    public QuestionDAO(Context context) {
        dbHelper = new DatabaseConnector(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean storeQuestion(Question question) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("question", question.getQuestion());

        long newRowId = db.insert("Questions", null, values);

        if (newRowId != -1) {
            return true;
        } else {
            return false;
        }
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

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

    public void createListQuestion() {
        List<String> stringList = new ArrayList<>();
        stringList.add("Bạn sinh vào ngày nào?");
        stringList.add("Bạn có con vật cưng nào?");
        stringList.add("Bạn thích môn thể thao nào nhất?");
        stringList.add("Tên người bạn thân nhất của bạn là gì?");
        stringList.add("Bạn đã từng đi du lịch ở đâu?");
        stringList.add("Trong gia đình bạn, bạn là người thứ mấy?");
        stringList.add("Bạn tốt nghiệp từ trường đại học nào?");
        stringList.add("Tên của người đồng nghiệp đầu tiên mà bạn làm việc cùng là gì?");
        stringList.add("Bạn thường xem thể loại phim nào?");
        stringList.add("Bạn đã từng tham gia sự kiện nào của cộng đồng gần đây nhất?");
        for (int i = 0; i < 10; i++) {
            Question question = new Question(i, stringList.get(i));
            this.storeQuestion(question);
        }
        Log.d("createListQuestion", "Inserted successfully");

    }

    public boolean deleteQuestion(Question question) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

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

    public Question getQuestionById(int questionId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

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


}
