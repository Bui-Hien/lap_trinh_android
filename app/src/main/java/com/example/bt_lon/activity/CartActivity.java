package com.example.bt_lon.activity;

import android.annotation.SuppressLint;

import java.text.DecimalFormat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
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
    private List<Cart> cartList;
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private TextView tvCartTotalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        tvCartTotalCost = findViewById(R.id.tvCartTotalCost);
        recyclerView = findViewById(R.id.recyclerViewCart);

        cartList = InsertData();
        cartAdapter = new CartAdapter(this, cartList);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CheckBox checkBoxAllCart = findViewById(R.id.checkBoxAllCart);
        checkBoxAllCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(CartActivity.this, "CheckBox đã được chọn", Toast.LENGTH_SHORT).show();
                    for (Cart cart : cartList) {
                        cart.setChecked(true);
                    }
                    tvCartTotalCost.setText("đ" + TotalCost(cartList));
                } else {
                    Toast.makeText(CartActivity.this, "CheckBox chưa được chọn", Toast.LENGTH_SHORT).show();
                    for (Cart cart : cartList) {
                        cart.setChecked(false);
                    }
                    tvCartTotalCost.setText("đ0");
                }
                cartAdapter.notifyDataSetChanged();
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


    public List<Cart> InsertData() {
        List<Cart> cartList = new ArrayList<>();

        // Load profile image and product image
        Bitmap productImage = BitmapFactory.decodeResource(getResources(), R.drawable.logo_miton);

        // Create a User object
        User user = new User(1, "hienbui", productImage);

        // Populate the cartList with Cart objects
        for (int i = 0; i < 10; i++) {
            Product product = new Product("productName " + i, "description" + i, 100000, productImage);
            Cart cart = new Cart(i, user, product, false, i);
            cartList.add(cart);
        }
        return cartList;
    }


    // Các phương thức và thuộc tính khác của lớp

    public String TotalCost(List<Cart> cartList) {
        double totalCost = 0;
        for (Cart cart : cartList) {
            if (cart.isChecked()) {
                totalCost += cart.getQuantity() * cart.getProduct().getPrice();
            }
        }

        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        return decimalFormat.format(totalCost).replace(",", ".");
    }

}
