package com.example.bt_lon.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.TaskStackBuilder;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_lon.R;
import com.example.bt_lon.adapter.CartAdapter;
import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DAO.CartDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.ProductDAO;
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
    private Button btnOkNoneProductCart;
    private ConstraintLayout constrainLayoutNoProduct;
    private RecyclerView recyclerView;

    @SuppressLint({"SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        tvCartTotalCost = findViewById(R.id.tvCartTotalCost);
        tvCartLogo = findViewById(R.id.tvCartLogo);
        btnCartBuyAll = findViewById(R.id.btnCartBuyAll);
        ImageView imageBack = findViewById(R.id.imageBack);
        CheckBox checkBoxAllCart = findViewById(R.id.checkBoxAllCart);
        constrainLayoutNoProduct = findViewById(R.id.constrainLayoutNoProduct);
        recyclerView = findViewById(R.id.recyclerViewCart);
        cartList = InsertData();
        if (cartList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            constrainLayoutNoProduct.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            constrainLayoutNoProduct.setVisibility(View.GONE);
            cartAdapter = new CartAdapter(this, cartList);
            recyclerView.setAdapter(cartAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            tvCartLogo.setText("Giỏ hàng (" + cartList.size() + ")");
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
                if (TotalBuyedAll(cartList) == 0 || cartList.isEmpty()) {
                    dialog.show();
                } else {
                    //lưu vào sql
                    //chuyển sang màn đã mua
                    ProductDAO productDAO = new ProductDAO(CartActivity.this);
                    CartDAO cartDAO = new CartDAO(CartActivity.this);
                    for (int i = 0; i < cartList.size(); i++) {
                        if (cartList.get(i).getQuantity() == cartList.get(i).getProduct().getQuantity()) {
                            productDAO.deleteProduct(cartList.get(i).getProduct().getProduct_id());
                            cartDAO.deleteProductFromCart(cartList.get(i).getUser().getUser_id(), cartList.get(i).getProduct().getProduct_id());
                        } else {
                            int quantityNew = cartList.get(i).getProduct().getQuantity() - cartList.get(i).getQuantity();
                            productDAO.updateQuantity(cartList.get(i).getProduct(), quantityNew);
                            cartDAO.deleteProductFromCart(cartList.get(i).getUser().getUser_id(), cartList.get(i).getProduct().getProduct_id());
                        }
                    }

                    cartList = InsertData();
                    updateLayout();
//                    Intent intent = new Intent(CartActivity.this, PurchaseOrderActivity.class);
//                    startActivity(intent);
//                    finish();
                }
            }
        });

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public List<Cart> InsertData() {
        CartDAO cartDAO = new CartDAO(CartActivity.this);
        return cartDAO.getCartItemsByUserId(CartActivity.this, RepositoryUser.getAccount());
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

    public void updateLayout() {
        if (cartList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            constrainLayoutNoProduct.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            constrainLayoutNoProduct.setVisibility(View.GONE);
        }
    }
}
