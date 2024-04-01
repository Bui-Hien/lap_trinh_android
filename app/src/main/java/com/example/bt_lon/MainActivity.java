package com.example.bt_lon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.bt_lon.activity.CartActivity;
import com.example.bt_lon.activity.LoginActivity;
import com.example.bt_lon.databinding.ActivityMainBinding;
import com.example.bt_lon.fragment.AccountFragment;
import com.example.bt_lon.fragment.HomeFragment;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int previousFragment;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                previousFragment = 1;
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.cart) {
                User user = RepositoryUser.getAccount();
                if (user != null) {
                    Intent intent = new Intent(this, CartActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }

            } else if (item.getItemId() == R.id.account) {
                previousFragment = 2;
                replaceFragment(new AccountFragment());
            }
            return true;
        });
        setUpUI();
    }

    private void setUpUI() {
        bottomNavigationView.setSelectedItemId(previousFragment == 1 ? R.id.home : R.id.account);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpUI();
        Toast.makeText(MainActivity.this, "onResume", Toast.LENGTH_LONG).show();
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}
