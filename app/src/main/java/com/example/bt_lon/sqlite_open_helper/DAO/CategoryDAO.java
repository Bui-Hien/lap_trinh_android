package com.example.bt_lon.sqlite_open_helper.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bt_lon.model.category.Category;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.bt_lon.Interface.OnCompleteListener;



public class CategoryDAO {
    private SQLiteDatabase database;
    private DatabaseConnector dbHelper;

    public class FirebaseCategoryDAO {
        public void writeCategoryToFirebase(Category category, OnCompleteListener onCompleteListener) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("categories");
            databaseReference.push().setValue(category, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    if (error != null) {
                        // Xảy ra lỗi trong quá trình thêm dữ liệu
                        onCompleteListener.onComplete(false, error.getMessage());
                    } else {
                        // Dữ liệu đã được push thành công
                        onCompleteListener.onComplete(true, "Push succeeded");
                    }
                }
            });
        }
    }

    public CategoryDAO(Context context) {
        dbHelper = new DatabaseConnector(context);
    }
    

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertCategory(Category category) {

        FirebaseCategoryDAO firebaseCategoryDAO = new FirebaseCategoryDAO();
        firebaseCategoryDAO.writeCategoryToFirebase(category, new OnCompleteListener() {
            @Override
            public void onComplete(boolean isSuccess, String message) {
                if (isSuccess) {
                    // Thành công
                    Log.d("CATEGORY", "Category inserted successfully: " + message);
                } else {
                    // Lỗi
                    Log.e("CATEGORY", "Failed to insert category: " + message);
                }
            }
        });
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("category_name", category.getCategory_name());
////        values.put("description", category.getDescription());
//
//        long result = db.insert("Categories", null, values);
//        db.close(); // Close the database connection after use
//        if (result != -1) {
//            Log.d("insertCategory", "Insertion successful");
//            return true;
//        } else {
//            Log.d("insertCategory", "Insertion failed");
//            return false;
//        }
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
