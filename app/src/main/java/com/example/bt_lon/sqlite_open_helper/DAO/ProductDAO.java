package com.example.bt_lon.sqlite_open_helper.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.category.Category;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private SQLiteDatabase database;
    private DatabaseConnector dbHelper;

    public ProductDAO(Context context) {
        dbHelper = new DatabaseConnector(context);
    }

    public ProductDAO(DatabaseConnector databaseConnector) {
    }

    public ProductDAO(ForgotPasswordDAO forgotPasswordDAO) {
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertProduct(Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("category_id", product.getCategory().getCategory_id());
        values.put("product_name", product.getProduct_name());
        values.put("description", product.getDescription());
        values.put("price", product.getPrice());
        values.put("image_product", bitmapToByteArray(product.getImage_product()));
        long result = db.insert("Products", null, values);
        db.close();

        if (result != -1) {
            Log.d("insertProduct", "Insertion successful");
            return true;
        } else {
            Log.d("insertProduct", "Insertion failed");
            return false;
        }
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Products", null);

        if (cursor.moveToFirst()) {
            do {
                int productId = (int) cursor.getLong(0);
                int categoryId = (int) cursor.getLong(1);
                String productName = cursor.getString(2);
                String description = cursor.getString(3);
                double price = cursor.getDouble(4);
                byte[] imageBytes = cursor.getBlob(5);
                Bitmap imageProduct = getBitmapFromBlob(imageBytes);

                Category category = new Category(categoryId, "", "");
                Product product = new Product(productId, category, productName, description, price, imageProduct);
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return productList;
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
            Log.e("ProductDAO bitmapToByteArray", e.toString());
        }
        return new byte[0];
    }

}
