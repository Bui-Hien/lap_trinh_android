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
import com.example.bt_lon.model.category.Category;
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

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean storeProductToCart(Cart cart, int quantity) {
        if (isProductInCart(cart.getUser().getUser_id(), cart.getProduct().getProduct_id())) {
            Log.e("storeProductToCart", "Đã có sản phẩn này trong giỏ hàng");
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            int newQuantity = getCartQuantity(cart.getUser().getUser_id(), cart.getProduct().getProduct_id()) + quantity;
            if (updateCartQuantity(cart.getUser().getUser_id(), cart.getProduct().getProduct_id(), newQuantity)) {
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
            values.put("quantity", quantity);

            if (db.insert("Carts", null, values) != -1) {
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

    public boolean deleteProductFromCart(int user_id, int product_id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int rowsAffected = db.delete("Carts", "user_id = ? AND product_id = ?",
                new String[]{String.valueOf(user_id), String.valueOf(product_id)});

        db.close();

        if (rowsAffected > 0) {
            Log.d("deleteProductFromCart", "Deletion successful");
            return true;
        } else {
            Log.e("deleteProductFromCart", "Deletion failed");
            return false;
        }
    }

    public boolean isProductInCart(int userId, int productId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM Carts WHERE user_id = ? AND product_id = ?";
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

    public boolean updateCartQuantity(int userId, int productId, int newQuantity) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("quantity", newQuantity);

        String selection = "user_id = ? AND product_id = ?";
        String[] selectionArgs = {String.valueOf(userId), String.valueOf(productId)};

        int rowsAffected = db.update("Carts", values, selection, selectionArgs);

        db.close();

        if (rowsAffected > 0) {
            Log.d("updateCartQuantity", "Update successful");
            return true;
        } else {
            Log.d("updateCartQuantity", "No rows affected. User or product not found in the cart.");
            return false;
        }
    }


    public int getCartQuantity(int userId, int productId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {"quantity"};
        String selection = "user_id = ? AND product_id = ?";
        String[] selectionArgs = {String.valueOf(userId), String.valueOf(productId)};

        Cursor cursor = db.query("Carts", projection, selection, selectionArgs, null, null, null);

        int quantity = 0; // default value

        if (cursor.moveToFirst()) {
            quantity = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return quantity;
    }

    public boolean deleteCartItem(int userId, int productId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = "user_id = ? AND product_id = ?";
        String[] selectionArgs = {String.valueOf(userId), String.valueOf(productId)};

        int rowsAffected = db.delete("Carts", selection, selectionArgs);

        db.close();

        if (rowsAffected > 0) {
            Log.d("deleteCartItem", "Deletion successful");
            return true;
        } else {
            Log.d("deleteCartItem", "No rows affected. User or product not found in the cart.");
            return false;
        }
    }

    public List<Cart> getCartItemsByUserId(Context context, User user) {
        List<Cart> cartItems = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM Carts WHERE user_id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(user.getUser_id())});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int cartId = cursor.getInt(1);
                int productId = cursor.getInt(2);
                int quantity = cursor.getInt(3);

                ProductDAO productDAO = new ProductDAO(context);
                Product product = productDAO.getProductById(context, productId);

                Cart cartItem = new Cart(cartId, user, product, false, quantity);
                cartItems.add(cartItem);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return cartItems;
    }
}
