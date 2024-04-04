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

import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CartDAO {
    private SQLiteDatabase database;
    private DatabaseConnector dbHelper;

    public CartDAO(Context context) {
        dbHelper = new DatabaseConnector(context);
    }

    public CartDAO(DatabaseConnector databaseConnector) {
    }

    public CartDAO(ForgotPasswordDAO forgotPasswordDAO) {
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean storeProductToCart(Cart cart) {
        if (isProductInCart(cart.getUser().getUser_id(), cart.getProduct().getProduct_id())) {
            Log.e("storeProductToCart", "da co");
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (updateCartQuantity(cart)) {
                db.close(); // Close the database connection after updating quantity
                return true; // Return true if quantity updated successfully
            } else {
                db.close();
                return false; // Return false if update failed
            }
        } else {
            Log.e("storeProductToCart", "Chưa có sản phẩn này trong giỏ hàng");
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("user_id", cart.getUser().getUser_id());
            values.put("product_id", cart.getProduct().getProduct_id());
            values.put("quantity", cart.getQuantity());

            if (db.insert("Cart", null, values) != -1) {
                Log.e("storeProductToCart", "thanh cong");
                db.close();
                return true;
            } else {
                Log.e("storeProductToCart", "that bai");
                db.close();
                return false;
            }
        }

    }


    public boolean isProductInCart(int userId, int productId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM Cart WHERE user_id = ? AND product_id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId), String.valueOf(productId)});
        int count = 0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            cursor.close();
        }
        db.close();
        return count > 0;
    }

    public boolean updateCartQuantity(Cart cart) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("quantity", cart.getQuantity());

        // Updating row
        int rowsAffected = db.update("Cart", values, "user_id = ? AND product_id = ?", new String[]{String.valueOf(cart.getUser().getUser_id()), String.valueOf(cart.getProduct().getProduct_id())});

        // Closing database connection
        db.close();

        // Check if the update was successful
        return rowsAffected > 0;
    }

    public List<Cart> getCartItemsByUserId(User user) {
        List<Cart> cartItems = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM Cart WHERE user_id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(user.getUser_id())});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int cartId = cursor.getInt(1);
                int productId = cursor.getInt(2);
                int quantity = cursor.getInt(3);

                Product product = new Product(productId, user.getProfileImage());
                // Create a Cart object and add it to the list
                Cart cartItem = new Cart(cartId, user, product, false, quantity);
                cartItems.add(cartItem);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return cartItems;
    }
}
