package com.example.bt_lon.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.example.bt_lon.R;
import com.example.bt_lon.adapter.CartAdapter;
import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize the cartList
        List<Cart> cartList = new ArrayList<>();

        // Load profile image and product image
        Bitmap productImage = BitmapFactory.decodeResource(getResources(), R.drawable.logo_miton);

        // Create a User object
        User user = new User(1, "hienbui", productImage);

        // Populate the cartList with Cart objects
        for (int i = 0; i < 10; i++) {
            Product product = new Product("productName " + i, "description" + i, 100000, productImage);
            Cart cart = new Cart(i, user, product,false, i);
            cartList.add(cart);
        }

        // Find RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewCart);

        // Create and set adapter
        CartAdapter myAdapter = new CartAdapter(this, cartList);
        recyclerView.setAdapter(myAdapter);

        // Set layout manager
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        // Find and set Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CheckBox checkBoxAllCart = findViewById(R.id.checkBoxAllCart);
        checkBoxAllCart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                // Kiểm tra trạng thái của CheckBox
                boolean isChecked = checkBoxAllCart.isChecked();

                // Kiểm tra xem CheckBox đã được chọn hay không
                // Nếu CheckBox đã được chọn, kiểm tra tất cả các CheckBox của mỗi item
                if (isChecked) {
                    for (Cart cart : cartList) {
                        cart.setChecked(true); // Đặt trạng thái đã chọn cho item cart
                    }
                    // Cập nhật RecyclerView sau khi thiết lập trạng thái đã chọn cho tất cả các item
                    myAdapter.notifyDataSetChanged();
                } else {
                    // CheckBox chưa được chọn
                    Toast.makeText(CartActivity.this, "CheckBox chưa được chọn", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cart_back_nav_menu, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.backHome) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
