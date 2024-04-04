package com.example.bt_lon.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_lon.R;
import com.example.bt_lon.adapter.CartAdapter;
import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DAO.CartDAO;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private List<Cart> cartList;
    private CartAdapter cartAdapter;
    private TextView tvCartTotalCost;
    private TextView tvCartLogo;
    private Button btnCartBuyAll;
    Button btnOkNoneProductCart;

    @SuppressLint({"SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        tvCartTotalCost = findViewById(R.id.tvCartTotalCost);
        tvCartLogo = findViewById(R.id.tvCartLogo);
        btnCartBuyAll = findViewById(R.id.btnCartBuyAll);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewCart);
        ImageView imageBack = findViewById(R.id.imageBack);
        CheckBox checkBoxAllCart = findViewById(R.id.checkBoxAllCart);
        cartList = InsertData();
        cartAdapter = new CartAdapter(this, cartList);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        tvCartLogo.setText("Giỏ hàng (" + cartList.size() + ")");

        Dialog dialog;
        dialog = new Dialog(CartActivity.this);
        dialog.setContentView(R.layout.warning_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        btnOkNoneProductCart = dialog.findViewById(R.id.btnWarningDialog);
        btnOkNoneProductCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnCartBuyAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TotalBuyedAll(cartList) == 0) {
                    dialog.show();
                } else {
                    //lưu vào sql
                    //chuyển sang màn đã mua
                    Intent intent = new Intent(CartActivity.this, PurchaseOrderActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        checkBoxAllCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (Cart cart : cartList) {
                        cart.setChecked(true);
                    }
                    tvCartTotalCost.setText("đ" + TotalCost(cartList));
                } else {
                    for (Cart cart : cartList) {
                        cart.setChecked(false);
                    }
                    tvCartTotalCost.setText("đ0");
                }
                cartAdapter.notifyDataSetChanged();
            }
        });
    }

    public List<Cart> InsertData() {
//        List<Cart> cartList = new ArrayList<>();
//        Bitmap productImage = BitmapFactory.decodeResource(getResources(), R.drawable.logo_miton);
//        User user = new User(1, "hienbui", productImage);
//
//        for (int i = 1; i < 10; i++) {
//            Product product = new Product("productName " + i, "description" + i, 100000, productImage);
//            Cart cart = new Cart(i, user, product, false, i);
//            cartList.add(cart);
//        }
        CartDAO cartDAO = new CartDAO(CartActivity.this);
        return cartDAO.getCartItemsByUserId(RepositoryUser.getAccount());
    }

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

    public int TotalBuyedAll(List<Cart> cartList) {
        int totalCost = 0;
        for (Cart cart : cartList) {
            if (cart.isChecked()) {
                totalCost += 1;
            }
        }
        return totalCost;
    }

    @SuppressLint("SetTextI18n")
    public void updateText() {
        tvCartTotalCost.setText("đ" + TotalCost(cartList));
        tvCartLogo.setText("Giỏ hàng (" + cartList.size() + ")");
        btnCartBuyAll.setText("Mua hàng (" + TotalBuyedAll(cartList) + ")");
    }
}
