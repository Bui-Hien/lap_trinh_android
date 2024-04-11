package com.example.bt_lon.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bt_lon.MainActivity;
import com.example.bt_lon.R;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DAO.ProductDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.QuestionDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.UserDAO;

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


/*
*   fake data login
* */






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
                String userName = String.valueOf(etUser.getText());
                String pass = String.valueOf(etPw.getText());
                UserDAO userDAO = new UserDAO(LoginActivity.this);
                User user = userDAO.getUser(userName, pass);
                if (user != null) {
                    finish();
                    RepositoryUser.createNewAccount(user);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu.", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    public void fakeData() {
        QuestionDAO questionDAO = new QuestionDAO(LoginActivity.this);
        questionDAO.createListQuestion();

        ProductDAO productDAO = new ProductDAO(LoginActivity.this);
        productDAO.fakeProductData(LoginActivity.this);

        String username = "buixuanhien";
        UserDAO userDAO = new UserDAO(LoginActivity.this);
        userDAO.fakeUser(LoginActivity.this, username);
        userDAO.fakeQuestionData(LoginActivity.this, username);
    }
}