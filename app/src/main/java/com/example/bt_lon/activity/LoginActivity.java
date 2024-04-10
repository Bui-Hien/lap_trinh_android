package com.example.bt_lon.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bt_lon.R;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DAO.ProductDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.QuestionDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.UserDAO;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView imageBack = findViewById(R.id.imageBack);
        Button button5 = findViewById(R.id.button5);
        Button button = findViewById(R.id.button);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDAO userDAO = new UserDAO(LoginActivity.this);


/*
*   fake data login
* */
//                fakeData();


                User user = userDAO.getUser("buixuanhien", "12345");
                if (user != null) {
                    finish();
                    RepositoryUser.createNewAccount(user);
                } else {
                    Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu.", Toast.LENGTH_LONG).show();
                }

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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