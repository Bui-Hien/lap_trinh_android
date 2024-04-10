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

import com.example.bt_lon.model.category.Category;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CategoryDAO {
    private SQLiteDatabase database;
    private DatabaseConnector dbHelper;

    public CategoryDAO(Context context) {
        dbHelper = new DatabaseConnector(context);
    }
    

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertCategory(Category category) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("category_name", category.getCategory_name());
//        values.put("description", category.getDescription());

        long result = db.insert("Categories", null, values);
        db.close(); // Close the database connection after use
        if (result != -1) {
            Log.d("insertCategory", "Insertion successful");
            return true;
        } else {
            Log.d("insertCategory", "Insertion failed");
            return false;
        }
    }

    public Category getCategoryById(int categoryId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {"category_id", "category_name", "description"};
        String selection = "category_id = ?";
        String[] selectionArgs = {String.valueOf(categoryId)};
        Cursor cursor = db.query("Categories", projection, selection, selectionArgs, null, null, null);

        Category category = new Category(0, "");
        if (cursor.moveToFirst()) {
            String categoryName = cursor.getString(1);
            String description = cursor.getString(2);

            category = new Category(categoryId, categoryName);
        }

        cursor.close();
        db.close();

        return category;
    }


}
