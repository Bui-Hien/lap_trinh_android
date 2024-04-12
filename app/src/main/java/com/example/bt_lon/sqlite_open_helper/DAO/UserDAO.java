package com.example.bt_lon.sqlite_open_helper.DAO;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.bt_lon.R;
import com.example.bt_lon.model.forgotPassword.ForgotPassword;
import com.example.bt_lon.model.question.Question;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class UserDAO {
    private SQLiteDatabase database;
    private DatabaseConnector dbHelper;

    public UserDAO(Context context) {
        dbHelper = new DatabaseConnector(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void fakeUser(Context context, String username) {
        Bitmap profileImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.cargo);
        User user = new User(1, "Bui Xuan Hien", username, "12345", true, new Date(), "Ha Noi", "0763433779", profileImage);
        this.storeUser(user);
        Log.d("fakeUser", "Inserted successfully");
    }


    public void storeUser(User user) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

        // Generate a unique key for the user
        String userId = usersRef.push().getKey();

        // If userId is null, try again to generate a unique key
        if (userId == null) {
            userId = usersRef.push().getKey();
        }

        // Convert bitmap to base64 string
        String imageBase64 = bitmapToBase64(user.getProfileImage());

        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String birthDateString = formatter.format(user.getYear_of_birth());

        // Create a HashMap to hold user data
        HashMap<String, Object> userData = new HashMap<>();
        userData.put("full_name", user.getFull_name());
        userData.put("username", user.getUsername());
        userData.put("password", user.getPassword());
        userData.put("sex", user.isMale() ? "male" : "female"); // Store sex as string
        userData.put("year_of_birth", birthDateString);
        userData.put("address", user.getAddress());
        userData.put("phone_number", user.getPhone_number());
        userData.put("profileImage", imageBase64);
        // Add other user data as needed

        // Store user data to Firebase
        usersRef.child(userId).setValue(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Data successfully saved
                        Log.d("Firebase", "User data saved successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors
                        Log.e("Firebase", "Error saving user data", e);
                    }
                });
    }


    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public void close() {
        dbHelper.close();
    }

    public boolean deleteUser(String username) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Define the WHERE clause to identify the user by their username
        String selection = "username = ?";
        String[] selectionArgs = {username};

        // Delete the user record
        int rowsDeleted = db.delete("users", selection, selectionArgs);
        db.close();

        // Check if any rows were deleted
        return rowsDeleted > 0;
    }

//    public boolean storeUser(User user) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        byte[] imageInBytes = bitmapToByteArray(user.getProfileImage());
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        String birthDateString = formatter.format(user.getYear_of_birth());
//
//
//        ContentValues values = new ContentValues();
//        values.put("full_name", user.getFull_name());
//        values.put("username", user.getUsername());
//        values.put("password", user.getPassword());
//        values.put("sex", user.isMale() ? 1 : 0);
//        values.put("year_of_birth", birthDateString);
//        values.put("address", user.getAddress());
//        values.put("phone_number", user.getPhone_number());
//        values.put("profileImage", imageInBytes);
//        // Add other columns here
//
//        long qk = db.insert("users", null, values);
//        db.close();
//        if (qk > 0) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }

    public User checkUser(User user) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT user_id, username FROM users WHERE username LIKE ?", new String[]{user.getUsername()});

        User userQuery = null;

        if (cursor.moveToFirst()) {
            // Nếu có dữ liệu trong Cursor, tạo một đối tượng User từ dữ liệu và trả về
            int userId = cursor.getInt(0);
            String username = cursor.getString(1);
            // Khởi tạo đối tượng User từ dữ liệu
            userQuery = new User(userId, user.getUsername());
        }

        cursor.close();

        return userQuery; // Nếu user null, có nghĩa là không tìm thấy user với username đã cho.
    }

    public interface UserCallback {
        void onCallback(User user);
    }

    public void checkUser(String username, UserCallback userCallback) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

        usersRef.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Người dùng tồn tại trong cơ sở dữ liệu Firebase
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        // Lấy thông tin người dùng từ dataSnapshot
                        User user = userSnapshot.getValue(User.class);
                        userCallback.onCallback(user);
                        return; // Kết thúc vòng lặp khi tìm thấy người dùng
                    }
                } else {
                    // Người dùng không tồn tại trong cơ sở dữ liệu Firebase
                    userCallback.onCallback(null);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý lỗi nếu có
                userCallback.onCallback(null);
            }
        });
    }

//    checkUser("username", new UserCallback() {
//        @Override
//        public void onCallback(User user) {
//            if (user != null) {
//                // Người dùng tồn tại
//            } else {
//                // Người dùng không tồn tại
//            }
//        }
//    });

    public void getUser(String userName, String password, UserCallback userCallback) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

        usersRef.orderByChild("username").equalTo(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        User user = userSnapshot.getValue(User.class);
                        if (user.getPassword().equals(password)) {
                            // Mật khẩu đúng, trả về thông tin người dùng
                            userCallback.onCallback(user);
                            return;
                        }
                    }
                }
                // Không tìm thấy người dùng hoặc mật khẩu không đúng
                userCallback.onCallback(null);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý lỗi nếu có
                userCallback.onCallback(null);
            }
        });
    }

//    getUser("username", "password", new UserCallback() {
//        @Override
//        public void onCallback(User user) {
//            if (user != null) {
//                // Người dùng tồn tại và mật khẩu đúng
//            } else {
//                // Người dùng không tồn tại hoặc mật khẩu không đúng
//            }
//        }
//    });

    public void updateUserInfo(User user) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users").child(user.getUsername());

        // Chuyển đổi hình ảnh sang dạng Base64 nếu cần thiết
        String imageBase64 = bitmapToBase64(user.getProfileImage());

        // Format ngày sinh
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String birthDateString = formatter.format(user.getYear_of_birth());

        // Tạo HashMap để chứa thông tin người dùng cần cập nhật
        Map<String, Object> userInfoUpdates = new HashMap<>();
        userInfoUpdates.put("full_name", user.getFull_name());
        userInfoUpdates.put("sex", user.isMale() ? "male" : "female");
        userInfoUpdates.put("year_of_birth", birthDateString);
        userInfoUpdates.put("address", user.getAddress());
        userInfoUpdates.put("phone_number", user.getPhone_number());
        userInfoUpdates.put("profileImage", imageBase64);
        // Thêm các trường dữ liệu khác cần cập nhật vào đây

        // Thực hiện cập nhật dữ liệu trong Firebase
        usersRef.updateChildren(userInfoUpdates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Xử lý khi cập nhật thành công
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Xử lý khi cập nhật thất bại
                    }
                });
    }

    public void updatePasswordUser(String username, String newPassword) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users").child(username);

        // Tạo HashMap để chỉ cập nhật mật khẩu
        Map<String, Object> passwordUpdate = new HashMap<>();
        passwordUpdate.put("password", newPassword);

        // Thực hiện cập nhật mật khẩu trong Firebase
        usersRef.updateChildren(passwordUpdate)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Xử lý khi cập nhật mật khẩu thành công
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Xử lý khi cập nhật mật khẩu thất bại
                    }
                });
    }

    public void updatePasswordUserFirebase(String username, String newPassword) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users").child(username);

        // Tạo HashMap để chỉ cập nhật mật khẩu
        Map<String, Object> passwordUpdate = new HashMap<>();
        passwordUpdate.put("password", newPassword);

        // Thực hiện cập nhật mật khẩu trong Firebase
        usersRef.updateChildren(passwordUpdate)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Xử lý khi cập nhật mật khẩu thành công
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Xử lý khi cập nhật mật khẩu thất bại
                    }
                });
    }


    //sqlite
    public User getUser(String userName, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{userName, password});

        User user = null;
        if (cursor.moveToFirst()) {
            int userId = cursor.getInt(0);
            String fullName = cursor.getString(1);
            boolean isMale = cursor.getInt(4) == 1;
            String birthDateString = cursor.getString(5);
            Date birthDate = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                birthDate = sdf.parse(birthDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String address = cursor.getString(6);
            String phoneNumber = cursor.getString(7);
            byte[] blobData = cursor.getBlob(8);
            Bitmap profileImage = getBitmapFromBlob(blobData);

            user = new User(userId,//0
                    fullName,//1
                    userName,//2
//                    password,//3
                    isMale,//4
                    birthDate,//5
                    address,//6
                    phoneNumber,//7
                    profileImage//8
            );
        }

        cursor.close();
        db.close();
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }


    public int updateInForUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        byte[] imageInBytes = bitmapToByteArray(user.getProfileImage());


        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String birthDateString = formatter.format(user.getYear_of_birth());


        ContentValues values = new ContentValues();
        values.put("full_name", user.getFull_name());
        values.put("sex", user.isMale() ? 1 : 0);
        values.put("year_of_birth", birthDateString);
        values.put("address", user.getAddress());
        values.put("phone_number", user.getPhone_number());
        values.put("profileImage", imageInBytes);
        // Add other columns here

        return db.update("users", values, "username" + " = ?", new String[]{String.valueOf(user.getUsername())});


    }

    public int updatePasswordUser(User user, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("password", user.getPassword());
        // You can add other columns to update here if needed

        // Update the row in the users table where the username matches the provided username
        int rowsAffected = db.update("users", values, "username = ? and password = ?", new String[]{user.getUsername(), password});

        // Return the number of rows affected
        return rowsAffected;
    }

    public int updatePasswordUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("password", user.getPassword());

        int rowsAffected = db.update("users", values, "username = ?", new String[]{user.getUsername()});

        // Return the number of rows affected
        return rowsAffected;
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
            Log.e("UserDAO bitmapToByteArray", e.toString());
        }
        return new byte[0];
    }


    public void fakeQuestionData(Context context, String username) {
        User user = new User(username);

        Question question1 = new Question(1, "1");
        Question question2 = new Question(2, "2");
        Question question3 = new Question(3, "3");

        ForgotPassword forgotPassword1 = new ForgotPassword(1, user, question1, "15/03/2003");
        ForgotPassword forgotPassword2 = new ForgotPassword(2, user, question2, "cho");
        ForgotPassword forgotPassword3 = new ForgotPassword(3, user, question3, "ngu");

        ForgotPasswordDAO forgotPasswordDAO = new ForgotPasswordDAO(context);
        forgotPasswordDAO.storeForgotPassword(context, forgotPassword1);
        forgotPasswordDAO.storeForgotPassword(context, forgotPassword2);
        forgotPasswordDAO.storeForgotPassword(context, forgotPassword3);
        Log.d("fakeQuestionData", "Inserted successfully");

    }
}
