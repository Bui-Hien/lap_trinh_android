package com.example.bt_lon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.bt_lon.databinding.ActivityMainBinding;
import com.example.bt_lon.fragment.AccountFragment;
import com.example.bt_lon.fragment.HomeFragment;
import com.example.bt_lon.fragment.CartFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //muốn hiển thị các layout thì bỏ comment ra

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //muốn hiển thị menu
//        super.onCreate(savedInstanceState);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        replaceFragment(new HomeFragment());
//
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setSelectedItemId(R.id.home);
//        bottomNavigationView.setOnItemSelectedListener(item -> {
//            if (item.getItemId() == R.id.home){
//                replaceFragment(new HomeFragment());
//            } else if (item.getItemId() == R.id.cart) {
//                replaceFragment(new CartFragment());
//            } else if (item.getItemId() == R.id.account) {
//                replaceFragment(new AccountFragment());
//            }
//
//            return true;
//        });        super.onCreate(savedInstanceState);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        replaceFragment(new HomeFragment());
//
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setSelectedItemId(R.id.home);
//        bottomNavigationView.setOnItemSelectedListener(item -> {
//            if (item.getItemId() == R.id.home){
//                replaceFragment(new HomeFragment());
//            } else if (item.getItemId() == R.id.cart) {
//                replaceFragment(new CartFragment());
//            } else if (item.getItemId() == R.id.account) {
//                replaceFragment(new AccountFragment());
//            }
//
//            return true;
//        });        super.onCreate(savedInstanceState);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        replaceFragment(new HomeFragment());
//
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setSelectedItemId(R.id.home);
//        bottomNavigationView.setOnItemSelectedListener(item -> {
//            if (item.getItemId() == R.id.home){
//                replaceFragment(new HomeFragment());
//            } else if (item.getItemId() == R.id.cart) {
//                replaceFragment(new CartFragment());
//            } else if (item.getItemId() == R.id.account) {
//                replaceFragment(new AccountFragment());
//            }
//
//            return true;
//        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
