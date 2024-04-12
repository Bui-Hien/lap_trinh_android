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


import com.example.bt_lon.Interface.OnGetProductsListener;
import com.example.bt_lon.R;
import com.example.bt_lon.activity.LoginActivity;
import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.category.Category;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.example.bt_lon.Interface.OnCompleteListener;
import com.example.bt_lon.Interface.OnGetAllProductsListener;
import com.example.bt_lon.Interface.OnGetProductByIdListener;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


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

    //innner class đổi bitmap sang Base64
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

    //inner class productDAO + firebase
    public class FirebaseProductDAO{
        public void writeProductToFirebase(Product product, OnCompleteListener onCompleteListener) {
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

        public void getAllProductsFromFireBase(OnGetAllProductsListener listener) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<Product> productList = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Truy cập vào từng thuộc tính của sản phẩm
                        String productIdfirebase = snapshot.getKey(); // Lấy productId từ key của nút
//                        String product_id = snapshot.child("product_id").getValue();
                        String productName = snapshot.child("product_name").getValue(String.class);
                        String description = snapshot.child("description").getValue(String.class);
                        double price = snapshot.child("price").getValue(Double.class);
                        int quantity = snapshot.child("quantity").getValue(Integer.class);
                        String base64 = snapshot.child("base64").getValue(String.class);
                        Category category = new Category(
                                snapshot.child("category").child("category_id").getValue(Integer.class),
                                snapshot.child("category").child("category_name").getValue(String.class)
                                );

                        // Tạo đối tượng Product và thêm vào danh sách
                        Product product = new Product(productIdfirebase,category, productName, description, price,base64, quantity);

                        productList.add(product);
                    }
                    listener.onSuccess(productList);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    listener.onError(databaseError.getMessage());
                }
            });
        }

        public void getAllProductsByCategoryIdFromFireBase( String categoryId, OnGetAllProductsListener listener) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");
            databaseReference.orderByChild("category/category_id").equalTo(categoryId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<Product> productList = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Product product = snapshot.getValue(Product.class);
                        productList.add(product);
                    }
                    listener.onSuccess(productList);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    listener.onError(databaseError.getMessage());
                }
            });
        }

        public void getProductByIdFromFireBase(String productId, OnGetProductByIdListener listener) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products").child(productId);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Product product = dataSnapshot.getValue(Product.class);
                        listener.onSuccess(product);
                    } else {
                        listener.onError("Product not found");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    listener.onError(databaseError.getMessage());
                }
            });
        }

        public void getAllProductsByNameFromFireBase(String name, OnGetAllProductsListener listener) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");
            Query query = databaseReference.orderByChild("product_name").startAt(name).endAt(name + "\uf8ff");

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<Product> productList = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Product product = snapshot.getValue(Product.class);
                        productList.add(product);
                    }
                    listener.onSuccess(productList);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    listener.onError(databaseError.getMessage());
                }
            });
        }

    }

    // end inner class firebaseDAO
    //==============================================================================================

    public void insertProduct(Product product) {

        ProductDAO.FirebaseProductDAO firebaseProductDAO = new ProductDAO.FirebaseProductDAO();
        firebaseProductDAO.writeProductToFirebase(product, new OnCompleteListener() {
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

    }

    public void getAllProducts(Context context, OnGetAllProductsListener listener) {
        ProductDAO.FirebaseProductDAO firebaseProductDAO = new ProductDAO.FirebaseProductDAO();
        firebaseProductDAO.getAllProductsFromFireBase(listener);
    }

    // Lấy tất cả sản phẩm theo ID danh mục
    public void getAllProductsByCategoryId(Context context,String categoryId, OnGetAllProductsListener listener) {

        ProductDAO.FirebaseProductDAO firebaseProductDAO = new ProductDAO.FirebaseProductDAO();
        firebaseProductDAO.getAllProductsByCategoryIdFromFireBase(categoryId, listener);
    }
    // Lấy sản phẩm theo ID
    public void getProductById(Context context, String productID, OnGetProductByIdListener listener) {

        ProductDAO.FirebaseProductDAO firebaseProductDAO = new ProductDAO.FirebaseProductDAO();

        firebaseProductDAO.getProductByIdFromFireBase(productID,  listener);
    }
    public void getAllProductsByName(Context context,String name,OnGetAllProductsListener listener) {

        ProductDAO.FirebaseProductDAO firebaseProductDAO = new ProductDAO.FirebaseProductDAO();
        firebaseProductDAO.getAllProductsByNameFromFireBase(name, listener);
    }

    // end productDAO methods
    //============================================================================================

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
//        categoryDAO.insertCategory(category);

        Category category2 = new Category(2, "Áo");
//        categoryDAO.insertCategory(category2);

        Category category3 = new Category(3, "Jeans");
//        categoryDAO.insertCategory(category3);

        Category category4 = new Category(4, "T-shirts");
//        categoryDAO.insertCategory(category4);

        Category category5 = new Category(5, "Set");
//        categoryDAO.insertCategory(category5);

        Category category6= new Category(6, "Sock");
//        categoryDAO.insertCategory(category6);

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


        Bitmap productImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_2);
        Bitmap resizedImage2 = resizeBitmap(productImage2,120,context);
        String base64String2 = bitmapUtil.bitmapToBase64(resizedImage2);
        Product product2 = new Product(
                category5,
                "Set váy nâu",
                "Vải đũi",
                20,
                base64String2,
                10);
        productDAO.insertProduct(product2);
        Log.d("fakeProductData", "Inserted successfully");

        Bitmap productImage3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_3);
        Bitmap resizedImage3 = resizeBitmap(productImage3,120,context);
        String base64String3 = bitmapUtil.bitmapToBase64(resizedImage3);
        Product product3 = new Product(
                category2,
                "Áo Nâu",
                "Cotton da cá",
                20,
                base64String3,
                10);
        productDAO.insertProduct(product3);
        Log.d("fakeProductData", "Inserted successfully");


        Bitmap productImage4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_4);
        Bitmap resizedImage4 = resizeBitmap(productImage4,120,context);
        String base64String4 = bitmapUtil.bitmapToBase64(resizedImage4);
        Product product4 = new Product(
                category5,
                "Set váy công sở",
                "váy công sở",
                20,
                base64String4,
                10);
        productDAO.insertProduct(product4);
        Log.d("fakeProductData", "Inserted successfully");


        Bitmap productImage5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_5);
        Bitmap resizedImage5 = resizeBitmap(productImage5,120,context);
        String base64String5 = bitmapUtil.bitmapToBase64(resizedImage5);
        Product product5 = new Product(
                category2,
                "Sweater cổ V nâu",
                "Cotton dày",
                20,
                base64String5,
                10);
        productDAO.insertProduct(product5);
        Log.d("fakeProductData", "Inserted successfully");


        Bitmap productImage6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_6);
        Bitmap resizedImage6 = resizeBitmap(productImage6,120,context);
        String base64String6 = bitmapUtil.bitmapToBase64(resizedImage6);
        Product product6 = new Product(
                category5,
                "Váy 2 dây hoa",
                "váy xinh",
                20,
                base64String6,
                10);
        productDAO.insertProduct(product6);
        Log.d("fakeProductData", "Inserted successfully");


        Bitmap productImage7 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_7);
        Bitmap resizedImage7 = resizeBitmap(productImage7,120,context);
        String base64String7 = bitmapUtil.bitmapToBase64(resizedImage7);
        Product product7 = new Product(
                category4,
                "Áo T-shirt trắng",
                "áo trắng",
                20,
                base64String7,
                10);
        productDAO.insertProduct(product7);
        Log.d("fakeProductData", "Inserted successfully");


        Bitmap productImage8 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_8);
        Bitmap resizedImage8 = resizeBitmap(productImage8,120,context);
        String base64String8 = bitmapUtil.bitmapToBase64(resizedImage8);
        Product product8 = new Product(
                category5,
                "Set váy Tím",
                "váy tím",
                20,
                base64String8,
                10);
        productDAO.insertProduct(product8);
        Log.d("fakeProductData", "Inserted successfully");


        Bitmap productImage9 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_9);
        Bitmap resizedImage9 = resizeBitmap(productImage9,120,context);
        String base64String9 = bitmapUtil.bitmapToBase64(resizedImage9);
        Product product9 = new Product(
                category2,
                "Áo sweater kẻ sọc",
                "áo len",
                20,
                base64String9,
                10);
        productDAO.insertProduct(product9);
        Log.d("fakeProductData", "Inserted successfully");


        Bitmap productImage10 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_10);
        Bitmap resizedImage10 = resizeBitmap(productImage10,120,context);
        String base64String10 = bitmapUtil.bitmapToBase64(resizedImage10);
        Product product10 = new Product(
                category4,
                "áo t-shirts màu be",
                "áo be",
                20,
                base64String10,
                10);
        productDAO.insertProduct(product10);
        Log.d("fakeProductData", "Inserted successfully");


        Bitmap productImage11 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_11);
        Bitmap resizedImage11 = resizeBitmap(productImage11,120,context);
        String base64String11 = bitmapUtil.bitmapToBase64(resizedImage11);
        Product product11 = new Product(
                category2,
                "Áo",
                "váy ngắn dễ thương",
                20,
                base64String11,
                10);
        productDAO.insertProduct(product11);
        Log.d("fakeProductData", "Inserted successfully");


        Bitmap productImage12 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_12);
        Bitmap resizedImage12 = resizeBitmap(productImage12,120,context);
        String base64String12 = bitmapUtil.bitmapToBase64(resizedImage12);
        Product product12 = new Product(
                category,
                "Set váy hoa nhí",
                "váy ngắn dễ thương",
                20,
                base64String12,
                10);
        productDAO.insertProduct(product12);
        Log.d("fakeProductData", "Inserted successfully");


        Bitmap productImage13 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_13);
        Bitmap resizedImage13 = resizeBitmap(productImage13,120,context);
        String base64String13 = bitmapUtil.bitmapToBase64(resizedImage13);
        Product product13 = new Product(
                category2,
                "Áo hoodie",
                "váy ngắn dễ thương",
                20,
                base64String13,
                10);
        productDAO.insertProduct(product13);
        Log.d("fakeProductData", "Inserted successfully");


        Bitmap productImage14 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_14);
        Bitmap resizedImage14 = resizeBitmap(productImage14,120,context);
        String base64String14 = bitmapUtil.bitmapToBase64(resizedImage14);
        Product product14 = new Product(
                category4,
                "T-shirt kẻ caro",
                "váy ngắn dễ thương",
                20,
                base64String14,
                10);
        productDAO.insertProduct(product14);
        Log.d("fakeProductData", "Inserted successfully");


        Bitmap productImage15 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_15);
        Bitmap resizedImage15 = resizeBitmap(productImage15,120,context);
        String base64String15 = bitmapUtil.bitmapToBase64(resizedImage15);
        Product product15 = new Product(
                category3,
                "Ao nau",
                "váy ngắn dễ thương",
                20,
                base64String15,
                10);
        productDAO.insertProduct(product15);
        Log.d("fakeProductData", "Inserted successfully");

        Bitmap productImage16 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_16);
        Bitmap resizedImage16 = resizeBitmap(productImage16,120,context);
        String base64String16 = bitmapUtil.bitmapToBase64(resizedImage16);
        Product product16 = new Product(
                category3,
                "yem jeans",
                "váy ngắn dễ thương",
                20,
                base64String16,
                10);
        productDAO.insertProduct(product16);
        Log.d("fakeProductData", "Inserted successfully");

        Bitmap productImage17 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_17);
        Bitmap resizedImage17 = resizeBitmap(productImage17,120,context);
        String base64String17 = bitmapUtil.bitmapToBase64(resizedImage17);
        Product product17 = new Product(
                category3,
                "Jeans tui hop",
                "váy ngắn dễ thương",
                20,
                base64String17,
                10);
        productDAO.insertProduct(product17);
        Log.d("fakeProductData", "Inserted successfully");

        Bitmap productImage18 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_18);
        Bitmap resizedImage18 = resizeBitmap(productImage18,120,context);
        String base64String18 = bitmapUtil.bitmapToBase64(resizedImage18);
        Product product18 = new Product(
                category6,
                "tat cute",
                "váy ngắn dễ thương",
                20,
                base64String18,
                10);
        productDAO.insertProduct(product18);
        Log.d("fakeProductData", "Inserted successfully");

        Bitmap productImage19 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_19);
        Bitmap resizedImage19 = resizeBitmap(productImage19,120,context);
        String base64String19 = bitmapUtil.bitmapToBase64(resizedImage19);
        Product product19 = new Product(
                category6,
                "tat co cao",
                "váy ngắn dễ thương",
                20,
                base64String19,
                10);
        productDAO.insertProduct(product19);
        Log.d("fakeProductData", "Inserted successfully");

        Bitmap productImage20 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_20);
        Bitmap resizedImage20 = resizeBitmap(productImage20,120,context);
        String base64String20 = bitmapUtil.bitmapToBase64(resizedImage20);
        Product product20 = new Product(
                category6,
                "Tat hoa co",
                "váy ngắn dễ thương",
                20,
                base64String20,
                10);
        productDAO.insertProduct(product20);
        Log.d("fakeProductData", "Inserted successfully");

//       =================================================
//
//        Bitmap productImage15 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_15);
//        Bitmap resizedImage15 = resizeBitmap(productImage,120,context);
//        String base64String15 = bitmapUtil.bitmapToBase64(resizedImage15);
//        Product product15 = new Product(
//                category5,
//                "Set váy nâu",
//                "váy ngắn dễ thương",
//                20,
//                base64String15,
//                10);
//        productDAO.insertProduct(product);
//        Log.d("fakeProductData", "Inserted successfully");
//
//        Bitmap productImage15 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_15);
//        Bitmap resizedImage15 = resizeBitmap(productImage,120,context);
//        String base64String15 = bitmapUtil.bitmapToBase64(resizedImage15);
//        Product product15 = new Product(
//                category5,
//                "Set váy nâu",
//                "váy ngắn dễ thương",
//                20,
//                base64String15,
//                10);
//        productDAO.insertProduct(product);
//        Log.d("fakeProductData", "Inserted successfully");
//
//        Bitmap productImage15 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_15);
//        Bitmap resizedImage15 = resizeBitmap(productImage,120,context);
//        String base64String15 = bitmapUtil.bitmapToBase64(resizedImage15);
//        Product product15 = new Product(
//                category3,
//                "Set váy nâu",
//                "váy ngắn dễ thương",
//                20,
//                base64String15,
//                10);
//        productDAO.insertProduct(product);
//        Log.d("fakeProductData", "Inserted successfully");
//
//        Bitmap productImage15 = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_15);
//        Bitmap resizedImage15 = resizeBitmap(productImage,120,context);
//        String base64String15 = bitmapUtil.bitmapToBase64(resizedImage15);
//        Product product15 = new Product(
//                category5,
//                "Set váy nâu",
//                "váy ngắn dễ thương",
//                20,
//                base64String15,
//                10);
//        productDAO.insertProduct(product);
//        Log.d("fakeProductData", "Inserted successfully");





    }
}
