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

import com.example.bt_lon.R;
import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.category.Category;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductDAO {
    private SQLiteDatabase database;
    private DatabaseConnector dbHelper;

    public ProductDAO(Context context) {
        dbHelper = new DatabaseConnector(context);
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
        values.put("quantity", product.getQuantity());
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

    public boolean updateQuantity(Product product, int newQuantity) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("quantity", newQuantity);

        int rowsAffected = db.update("Products", values, "product_id = ?",
                new String[]{String.valueOf(product.getProduct_id())});

        db.close();

        if (rowsAffected > 0) {
            Log.d("updateQuantity", "Update successful");
            return true;
        } else {
            Log.d("updateQuantity", "Update failed");
            return false;
        }
    }
    public boolean deleteProduct(int productId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int rowsAffected = db.delete("Products", "product_id = ?", new String[]{String.valueOf(productId)});

        db.close();

        if (rowsAffected > 0) {
            Log.d("deleteProduct", "Deletion successful");
            return true;
        } else {
            Log.d("deleteProduct", "Deletion failed");
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
                int quantity = (int) cursor.getLong(6);

                Category category = new Category(categoryId, "", "");
                Product product = new Product(productId, category, productName, description, price, quantity, imageProduct);
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return productList;
    }

    public Product getProductById(Context context, int productId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Products WHERE product_id = ?", new String[]{String.valueOf(productId)});

        Product product = null;
        if (cursor.moveToFirst()) {
            int categoryId = cursor.getInt(1);
            String productName = cursor.getString(2);
            String description = cursor.getString(3);
            double price = cursor.getDouble(4);
            byte[] imageBytes = cursor.getBlob(5);
            Bitmap imageProduct = getBitmapFromBlob(imageBytes);
            int quantity = (int) cursor.getLong(6);

            CategoryDAO categoryDAO = new CategoryDAO(context);
            Category category = categoryDAO.getCategoryById(categoryId);
            product = new Product(productId, category, productName, description, price, quantity, imageProduct);

        }

        cursor.close();
        db.close();
        return product;
    }

    public void fakeProductData(Context context) {
        Category category = new Category(1, "dien thoai", "iphone 15 promax");
        CategoryDAO categoryDAO = new CategoryDAO(context);
        categoryDAO.insertCategory(category);

        Bitmap productImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.girl);

        ProductDAO productDAO = new ProductDAO(context);
        for (int i = 1; i <= 10; i++) {

            Random rand = new Random();
            int QuantityProduct = rand.nextInt(100) + 1;

            Product product = new Product(
                    i, category,
                    "Book Title" + i,
                    "Description of the book" + i,
                    1999.99,
                    QuantityProduct,
                    productImage);
            productDAO.insertProduct(product);
        }
        Log.d("fakeProductData", "Inserted successfully");

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
