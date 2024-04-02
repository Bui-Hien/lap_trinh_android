package com.example.bt_lon.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bt_lon.R;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;

import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button5 = findViewById(R.id.button5);
        Button button = findViewById(R.id.button);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseConnector databaseConnector = new DatabaseConnector(LoginActivity.this);
//                Bitmap profileImage = BitmapFactory.decodeResource(getResources(), R.drawable.cargo);
//                User user = new User(1, "Bui Xuan Hien", "buixuanhien5", "12345", true, new Date(), "Ha Noi", "0763433779", profileImage);
//               databaseConnector.storeUser(user);



                User user = databaseConnector.getUser("buixuanhien5", "12345");
                RepositoryUser.createNewAccount(user.getUser_id(), user.getFull_name(), user.getUsername(), user.isMale(), user.getYear_of_birth(), user.getAddress(), user.getPhone_number(), user.getProfileImage());


                    finish();
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
    }
}