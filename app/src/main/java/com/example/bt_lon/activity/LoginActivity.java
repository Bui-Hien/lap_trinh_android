package com.example.bt_lon.activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bt_lon.MainActivity;
import com.example.bt_lon.R;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DAO.ProductDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.QuestionDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.UserDAO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    EditText etUser, etPw;
    Button btnRegister, btnLogin, btnFogotPw;

    //    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView imageBack = findViewById(R.id.imageBack);

        etUser = findViewById(R.id.etUsername);
        etPw = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        btnFogotPw = findViewById(R.id.btnFogotPw);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnFogotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                fakeData();

                String username = String.valueOf(etUser.getText());
                UserDAO userDAO = new UserDAO(LoginActivity.this);
                if (username.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Tên đăng nhập không được trống.", Toast.LENGTH_SHORT).show();
                } else {
                    if (userDAO.checkUser(new User(username)) == null) {
                        Toast.makeText(LoginActivity.this, "Tên đăng nhập không tồn tại.", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    }
                }
            }
        });
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap profileImage = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.cargo);
                User user = new User(1, "Bui Xuan Hien", "username", "12345", true, new Date(), "Ha Noi", "0763433779", profileImage);
                storeUser(user);



//                String userName = String.valueOf(etUser.getText());
//                String pass = String.valueOf(etPw.getText());
//                UserDAO userDAO = new UserDAO(LoginActivity.this);
//                User user = userDAO.getUser(userName, pass);
//                if (user != null) {
//                    finish();
//                    RepositoryUser.createNewAccount(user);
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                } else {
//                    Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu.", Toast.LENGTH_LONG).show();
//                }
            }
        });


    }

    public void fakeData() {
//        QuestionDAO questionDAO = new QuestionDAO(LoginActivity.this);
//        questionDAO.createListQuestion();
//
//        ProductDAO productDAO = new ProductDAO(LoginActivity.this);
//        productDAO.fakeProductData(LoginActivity.this);

//        String username = "buixuanhien12345";
//        UserDAO userDAO = new UserDAO(LoginActivity.this);
//        userDAO.fakeUser(LoginActivity.this, username);
//        userDAO.fakeQuestionData(LoginActivity.this, username);

    }

    public void storeUser(User user) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

        // Generate a unique key for the user
        String userId = usersRef.push().getKey();

        // If userId is null, try again to generate a unique key
        if (userId == null) {
            userId = usersRef.push().getKey();
        }
        Toast.makeText(getApplicationContext(), userId, Toast.LENGTH_SHORT).show();

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
}