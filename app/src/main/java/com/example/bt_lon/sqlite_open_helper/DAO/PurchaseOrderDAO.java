package com.example.bt_lon.sqlite_open_helper.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.purchaseorder.PurchaseOrder;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PurchaseOrderDAO {
    private SQLiteDatabase database;
    private DatabaseConnector dbHelper;

    public PurchaseOrderDAO(Context context) {
        dbHelper = new DatabaseConnector(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertPurchaseOrder(PurchaseOrder purchaseOrder) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("product_id", purchaseOrder.getProduct().getProduct_id());
        values.put("user_id", purchaseOrder.getUser().getUser_id());
        values.put("quantity", purchaseOrder.getQuantity());
        values.put("purchase_time", String.valueOf(purchaseOrder.getPurchase_date()));
        values.put("cost", purchaseOrder.getCost());

        long result = db.insert("PurchaseOrders", null, values);
        db.close();

        if (result != -1) {
            Log.d("insertPurchaseOrder", "Insertion successful");
            return true;
        } else {
            Log.e("insertPurchaseOrder", "Insertion failed");
            return false;
        }
    }

    public List<PurchaseOrder> getAllPurchaseOrdersByUserId(Context context, int userId) {
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                "purchase_order_id",
                "product_id",
                "quantity",
                "purchase_time",
                "cost"
        };

        String selection = "user_id = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(
                "PurchaseOrders",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                Date date = null;
                try {
                    date = dateFormat.parse(cursor.getString(3));
                } catch (ParseException e) {
                    Log.e("getAllPurchaseOrdersByUserId", e.toString());
                }
                int purchaseOrderId = cursor.getInt(0);
                int productId = cursor.getInt(1);
                int quantity = cursor.getInt(2);
                double cost = cursor.getDouble(4);


                ProductDAO productDAO = new ProductDAO(context);
                User user = RepositoryUser.getAccount();

                Product product = productDAO.getProductById(context, productId);

                PurchaseOrder purchaseOrder = new PurchaseOrder(purchaseOrderId, product, user, quantity, date, cost);
                purchaseOrders.add(purchaseOrder);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return purchaseOrders;
    }

    public List<PurchaseOrder> getAllPurchaseOrdersByUserIdGroupByProductId(Context context, int userId) {
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                "product_id",
                "COUNT(*) AS solanmua",
                "MAX(purchase_order_id) AS purchase_order_id",
                "MAX(quantity) AS quantity",
                "MAX(purchase_time) AS purchase_time",
                "MAX(cost) AS cost"
        };

        String selection = "user_id = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(
                "PurchaseOrders",
                projection,
                selection,
                selectionArgs,
                "product_id", // Group by product_id
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int productId = cursor.getInt(0);
                int numberOfPurchases = cursor.getInt(1);
                int purchaseOrderId = cursor.getInt(2);
                int quantity = cursor.getInt(3);
                String purchaseTimeString = cursor.getString(4);
                double cost = cursor.getDouble(5);

                // Convert purchase time string to Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                Date purchaseTime = null;
                try {
                    purchaseTime = dateFormat.parse(purchaseTimeString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                ProductDAO productDAO = new ProductDAO(context);
                User user = RepositoryUser.getAccount();
                Product product = productDAO.getProductById(context, productId);

                PurchaseOrder purchaseOrder = new PurchaseOrder(purchaseOrderId, product, user, quantity, purchaseTime, cost, numberOfPurchases);
                purchaseOrders.add(purchaseOrder);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return purchaseOrders;
    }

}
