package com.example.bt_lon.sqlite_open_helper.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bt_lon.Interface.OnCompleteListener;
import com.example.bt_lon.R;
import com.example.bt_lon.activity.LoginActivity;
import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.category.Category;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductDAO {
    private SQLiteDatabase database;
    private DatabaseConnector dbHelper;

    public class BitmapUtil {

        // Chuyển đổi Bitmap thành chuỗi Base64
        public  String bitmapToBase64(Bitmap bitmap) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] byteArray = baos.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        }

        // Chuyển đổi chuỗi Base64 thành Bitmap
        public  Bitmap base64ToBitmap(String base64String) {
            byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        }
    }

    public class FirebaseProductDAO{
        public void writeCategoryToFirebase(Product product, OnCompleteListener onCompleteListener) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");
            databaseReference.push().setValue(product, new DatabaseReference.CompletionListener() {
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

    public ProductDAO(Context context) {
        dbHelper = new DatabaseConnector(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertProduct(Product product) {

        ProductDAO.FirebaseProductDAO firebaseProductDAO = new ProductDAO.FirebaseProductDAO();
        firebaseProductDAO.writeCategoryToFirebase(product, new OnCompleteListener() {
            @Override
            public void onComplete(boolean isSuccess, String message) {
                if (isSuccess) {
                    // Thành công
                    Log.d("Product", "Category inserted successfully: " + message);
                } else {
                    // Lỗi
                    Log.e("Product", "Failed to insert category: " + message);
                }
            }
        });

//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put("category_id", product.getCategory().getCategory_id());
//        values.put("product_name", product.getProduct_name());
//        values.put("description", product.getDescription());
//        values.put("price", product.getPrice());
//        values.put("image_product", bitmapToByteArray(product.getImage_product()));
//        values.put("quantity", product.getQuantity());
//        long result = db.insert("Products", null, values);
//        db.close();
//
//        if (result != -1) {
//            Log.d("insertProduct", "Insertion successful");
//            return true;
//        } else {
//            Log.d("insertProduct", "Insertion failed");
//            return false;
//        }
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

                Category category = new Category(categoryId, "");
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

public List<Product> getAllProductsByCategoryId(Context context, int categoryId) {
    List<Product> productList = new ArrayList<>();
    SQLiteDatabase db = dbHelper.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT * FROM Products WHERE category_id = ?", new String[]{String.valueOf(categoryId)});

    if (cursor.moveToFirst()) {
        do {
            int productId = (int) cursor.getLong(0);
//            int categoryId = (int) cursor.getLong(1);
            String productName = cursor.getString(2);
            String description = cursor.getString(3);
            double price = cursor.getDouble(4);
            byte[] imageBytes = cursor.getBlob(5);
            Bitmap imageProduct = getBitmapFromBlob(imageBytes);
            int quantity = (int) cursor.getLong(6);

            CategoryDAO categoryDAO = new CategoryDAO(context);
            Category category = categoryDAO.getCategoryById(categoryId);
            Product product = new Product(productId, category, productName, description, price, quantity, imageProduct);
            productList.add(product);
        } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();

    return productList;
}

    public List<Product> getAllProductsByName(Context context, String name) {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Products WHERE product_name LIKE ?", new String[]{"%" + name + "%"});


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

                CategoryDAO categoryDAO = new CategoryDAO(context);
                Category category = categoryDAO.getCategoryById(categoryId);
                Product product = new Product(productId, category, productName, description, price, quantity, imageProduct);
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

    private Bitmap resizeBitmap(Bitmap bitmap, int targetSizeDP, Context context) {

        float density = context.getResources().getDisplayMetrics().density;
        int targetSizePx = (int) (targetSizeDP * density);
        float aspectRatio = (float) bitmap.getWidth() / bitmap.getHeight();
        int targetWidth = Math.round(targetSizePx * aspectRatio);
        int targetHeight = targetSizePx;

        return Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, true);
    }
    public byte[] bitmapToByteArray(Bitmap bitmap, int targetSizeDP, int quality, Context context) {
        try {
            Bitmap resizedBitmap = resizeBitmap(bitmap, targetSizeDP, context);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
//            Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return new byte[0];
    }
    public void fakeProductData(Context context) {

        /*
        * add category data
        * */
        Category category = new Category(1, "Váy");
        CategoryDAO categoryDAO = new CategoryDAO(context);
        categoryDAO.insertCategory(category);

        Category category2 = new Category(2, "Áo");
        categoryDAO.insertCategory(category);

        Category category3 = new Category(3, "Jeans");
        categoryDAO.insertCategory(category);

        Category category4 = new Category(4, "T-shirts");
        categoryDAO.insertCategory(category);

        Category category5 = new Category(5, "Set");
        categoryDAO.insertCategory(category);

        Category category6= new Category(6, "Sock");
        categoryDAO.insertCategory(category);

/*
* add data product
* */
        ProductDAO productDAO = new ProductDAO(context);
        BitmapUtil bitmapUtil = new BitmapUtil();

        Bitmap productImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_1);
        Bitmap resizedImage = resizeBitmap(productImage,120,context);
        String base64String = bitmapUtil.bitmapToBase64(resizedImage);
        Product product = new Product(
                category,
                "Set váy ngắn",
                "váy ngắn dễ thương",
                20,
                base64String,
                10);
        productDAO.insertProduct(product);
        Log.d("fakeProductData", "Inserted successfully");


//        Bitmap productImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_2);
//        Bitmap resizedImage2 = resizeBitmap(productImage2,120,context);
//        Product product2 = new Product(
//                category5,
//                "Set váy nâu",
//                "Vải đũi",
//                20,
//                resizedImage2,
//                10);
//        productDAO.insertProduct(product2);
//        Log.d("fakeProductData", "Inserted successfully");
//
//        Bitmap productImage3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_3);
//        Bitmap resizedImage3 = resizeBitmap(productImage3,120,context);
//        Product product3 = new Product(
//                category2,
//                "Áo Nâu",
//                "Cotton da cá",
//                20,
//                resizedImage3,
//                10);
//        productDAO.insertProduct(product3);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_4);
//        Bitmap resizedImage4 = resizeBitmap(productImage4,120,context);
//        Product product4 = new Product(
//                category5,
//                "Set váy công sở",
//                "váy công sở",
//                20,
//                resizedImage4,
//                10);
//        productDAO.insertProduct(product4);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_5);
//        Bitmap resizedImage5 = resizeBitmap(productImage5,120,context);
//        Product product5 = new Product(
//                category2,
//                "Sweater cổ V nâu",
//                "Cotton dày",
//                20,
//                resizedImage5,
//                10);
//        productDAO.insertProduct(product5);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_6);
//        Bitmap resizedImage6 = resizeBitmap(productImage6,120,context);
//        Product product6 = new Product(
//                category5,
//                "Váy 2 dây hoa",
//                "váy xinh",
//                20,
//                resizedImage6,
//                10);
//        productDAO.insertProduct(product6);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage7 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_7);
//        Bitmap resizedImage7 = resizeBitmap(productImage7,120,context);
//        Product product7 = new Product(
//                category4,
//                "Áo T-shirt trắng",
//                "áo trắng",
//                20,
//                resizedImage7,
//                10);
//        productDAO.insertProduct(product7);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage8 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_8);
//        Bitmap resizedImage8 = resizeBitmap(productImage8,120,context);
//        Product product8 = new Product(
//                category5,
//                "Set váy Tím",
//                "váy tím",
//                20,
//                resizedImage8,
//                10);
//        productDAO.insertProduct(product8);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage9 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_9);
//        Bitmap resizedImage9 = resizeBitmap(productImage9,120,context);
//        Product product9 = new Product(
//                category2,
//                "Áo sweater kẻ sọc",
//                "áo len",
//                20,
//                resizedImage9,
//                10);
//        productDAO.insertProduct(product9);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage10 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_10);
//        Bitmap resizedImage10 = resizeBitmap(productImage10,120,context);
//        Product product10 = new Product(
//                category4,
//                "áo t-shirts màu be",
//                "áo be",
//                20,
//                resizedImage10,
//                10);
//        productDAO.insertProduct(product10);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage11 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_11);
//        Bitmap resizedImage11 = resizeBitmap(productImage11,120,context);
//        Product product11 = new Product(
//                category2,
//                "Áo",
//                "váy ngắn dễ thương",
//                20,
//                resizedImage11,
//                10);
//        productDAO.insertProduct(product11);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage12 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_12);
//        Bitmap resizedImage12 = resizeBitmap(productImage12,120,context);
//        Product product12 = new Product(
//                category,
//                "Set váy hoa nhí",
//                "váy ngắn dễ thương",
//                20,
//                resizedImage12,
//                10);
//        productDAO.insertProduct(product12);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage13 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_13);
//        Bitmap resizedImage13 = resizeBitmap(productImage13,120,context);
//        Product product13 = new Product(
//                category2,
//                "Áo hoodie",
//                "váy ngắn dễ thương",
//                20,
//                resizedImage13,
//                10);
//        productDAO.insertProduct(product13);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage14 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_14);
//        Bitmap resizedImage14 = resizeBitmap(productImage14,120,context);
//        Product product14 = new Product(
//                category4,
//                "T-shirt kẻ caro",
//                "váy ngắn dễ thương",
//                20,
//                resizedImage14,
//                10);
//        productDAO.insertProduct(product14);
//        Log.d("fakeProductData", "Inserted successfully");

//
//        Bitmap productImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_2);
//        Bitmap resizedImage2 = resizeBitmap(productImage,120,context);
//        Product product2 = new Product(
//                category5,
//                "Set váy nâu",
//                "váy ngắn dễ thương",
//                20,
//                resizedImage);
//        productDAO.insertProduct(product);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_2);
//        Bitmap resizedImage2 = resizeBitmap(productImage,120,context);
//        Product product2 = new Product(
//                category5,
//                "Set váy nâu",
//                "váy ngắn dễ thương",
//                20,
//                resizedImage);
//        productDAO.insertProduct(product);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_2);
//        Bitmap resizedImage2 = resizeBitmap(productImage,120,context);
//        Product product2 = new Product(
//                category5,
//                "Set váy nâu",
//                "váy ngắn dễ thương",
//                20,
//                resizedImage);
//        productDAO.insertProduct(product);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_2);
//        Bitmap resizedImage2 = resizeBitmap(productImage,120,context);
//        Product product2 = new Product(
//                category5,
//                "Set váy nâu",
//                "váy ngắn dễ thương",
//                20,
//                resizedImage);
//        productDAO.insertProduct(product);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_2);
//        Bitmap resizedImage2 = resizeBitmap(productImage,120,context);
//        Product product2 = new Product(
//                category5,
//                "Set váy nâu",
//                "váy ngắn dễ thương",
//                20,
//                resizedImage);
//        productDAO.insertProduct(product);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_2);
//        Bitmap resizedImage2 = resizeBitmap(productImage,120,context);
//        Product product2 = new Product(
//                category5,
//                "Set váy nâu",
//                "váy ngắn dễ thương",
//                20,
//                resizedImage);
//        productDAO.insertProduct(product);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_2);
//        Bitmap resizedImage2 = resizeBitmap(productImage,120,context);
//        Product product2 = new Product(
//                category5,
//                "Set váy nâu",
//                "váy ngắn dễ thương",
//                20,
//                resizedImage);
//        productDAO.insertProduct(product);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_2);
//        Bitmap resizedImage2 = resizeBitmap(productImage,120,context);
//        Product product2 = new Product(
//                category5,
//                "Set váy nâu",
//                "váy ngắn dễ thương",
//                20,
//                resizedImage);
//        productDAO.insertProduct(product);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_2);
//        Bitmap resizedImage2 = resizeBitmap(productImage,120,context);
//        Product product2 = new Product(
//                category5,
//                "Set váy nâu",
//                "váy ngắn dễ thương",
//                20,
//                resizedImage);
//        productDAO.insertProduct(product);
//        Log.d("fakeProductData", "Inserted successfully");
//
//
//        Bitmap productImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_2);
//        Bitmap resizedImage2 = resizeBitmap(productImage,120,context);
//        Product product2 = new Product(
//                category5,
//                "Set váy nâu",
//                "váy ngắn dễ thương",
//                20,
//                resizedImage);
//        productDAO.insertProduct(product);
//        Log.d("fakeProductData", "Inserted successfully");



    }
}
