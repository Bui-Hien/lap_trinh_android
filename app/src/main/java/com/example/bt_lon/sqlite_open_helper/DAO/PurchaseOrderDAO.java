package com.example.bt_lon.sqlite_open_helper.DAO;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;


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

}
