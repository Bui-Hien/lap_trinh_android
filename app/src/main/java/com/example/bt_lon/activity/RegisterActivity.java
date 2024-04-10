package com.example.bt_lon.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bt_lon.R;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DAO.UserDAO;

public class RegisterActivity extends AppCompatActivity {
    EditText etUser, etPw, etRePw;
    Button btnRegister, btnLogin;
    ImageView imageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUser = findViewById(R.id.etUsername);
        etPw = findViewById(R.id.etPassword);
        etRePw = findViewById(R.id.etRepassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnSignin);
        imageBack = findViewById(R.id.imageBack);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, password, rePw;
                username = etUser.getText().toString();
                password = etPw.getText().toString();
                rePw = etRePw.getText().toString();
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(rePw)) {
                        UserDAO userDAO = new UserDAO(getApplicationContext());
                        User user1 = new User(username, password);
//                        Toast.makeText(RegisterActivity.this, "" + userDAO.checkUser(user1).getUser_id(), Toast.LENGTH_SHORT).show();
                        if (userDAO.checkUser(user1) == null) {
                            Intent intent = new Intent(RegisterActivity.this, AnswerActivity.class);
                            intent.putExtra("username", username);
                            intent.putExtra("password", password);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Người dùng đã tồn tại", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }


}
