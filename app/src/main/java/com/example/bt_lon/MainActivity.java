package com.example.bt_lon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bt_lon.activity.CartActivity;
import com.example.bt_lon.activity.LoginActivity;
import com.example.bt_lon.databinding.ActivityMainBinding;
import com.example.bt_lon.fragment.AccountFragment;
import com.example.bt_lon.fragment.HomeFragment;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

//        DatabaseConnector databaseConnector = new DatabaseConnector(this);
//        Bitmap profileImage = BitmapFactory.decodeResource(getResources(), R.drawable.cargo);
//        User user = new User(1, "Bui Xuan Hien", "buixuanhien", "12345", true, new Date(), "Ha Noi", "0763433779", profileImage);
//
//        databaseConnector.storeUser(user);
//        Toast.makeText(MainActivity.this, databaseConnector.storeUser(user) ? "Co" : "Khong", Toast.LENGTH_SHORT).show();


//        boolean isUser = databaseConnector.checkUser("buihien2");
//        Toast.makeText(MainActivity.this, isUser ? "Co" : "Khong", Toast.LENGTH_SHORT).show();


//        Bitmap profileImage = BitmapFactory.decodeResource(getResources(), R.drawable.cargo);
//        User user = new User(1, "Bui Xuan Hien 1", "buihien1", "123455", true, new Date(), "Ha Noi", "0763433779", profileImage);
//


        replaceFragment(new HomeFragment());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        setUpUI();
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
    }

        private void setUpUI() {
        bottomNavigationView.setSelectedItemId(previousFragment == 1 ? R.id.home : R.id.account);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpUI();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
