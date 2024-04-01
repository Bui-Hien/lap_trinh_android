package com.example.bt_lon.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bt_lon.R;
import com.example.bt_lon.model.user.RepositoryUser;

import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap profileImage = BitmapFactory.decodeResource(getResources(), R.drawable.cargo);
                RepositoryUser.createNewAccount(1, "Bùi Xuân Hiền", "johndoe123", false, new Date(), "123 Main St", "1234567890", null);
                finish();
            }
        });
    }
}