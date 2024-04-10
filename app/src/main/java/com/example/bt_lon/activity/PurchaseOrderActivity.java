package com.example.bt_lon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_lon.R;
import com.example.bt_lon.adapter.PurchaseOrderAdapter;
import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.purchaseorder.PurchaseOrder;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DAO.CartDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.ProductDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.PurchaseOrderDAO;

import java.util.Collections;
import java.util.List;

public class PurchaseOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_order);

        ConstraintLayout constraintLayout1 = findViewById(R.id.purchase_none_product);
        ConstraintLayout constraintLayout2 = findViewById(R.id.purchase_product);
        ImageView imageBackPurchase = findViewById(R.id.imageBackPurchase);

        List<PurchaseOrder> purchaseOrderList = initData();


        if (!purchaseOrderList.isEmpty()) {
            constraintLayout1.setVisibility(View.GONE);
            constraintLayout2.setVisibility(View.VISIBLE);

            RecyclerView recyclerView = findViewById(R.id.recyclerViewPurchase);

            // Initialize and set up the adapter
            PurchaseOrderAdapter adapter = new PurchaseOrderAdapter(this, purchaseOrderList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        } else {
            constraintLayout1.setVisibility(View.VISIBLE);
            constraintLayout2.setVisibility(View.GONE);
        }

        imageBackPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void Repurchase(Cart cart) {
        //them purchaseOrder vao db
        ProductDAO productDAO = new ProductDAO(PurchaseOrderActivity.this);
        Product productCheck = productDAO.getProductById(PurchaseOrderActivity.this, cart.getProduct().getProduct_id());
        if (productCheck.getQuantity() != 0) {
            if (RepositoryUser.getAccount() != null) {
                CartDAO cartDAO = new CartDAO(PurchaseOrderActivity.this);
                cartDAO.storeProductToCart(cart);
                Intent intent = new Intent(PurchaseOrderActivity.this, CartActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(PurchaseOrderActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(PurchaseOrderActivity.this, "Sản phẩm đã hết.", Toast.LENGTH_SHORT).show();
        }

    }

    private List<PurchaseOrder> initData() {
        PurchaseOrderDAO purchaseOrderDAO = new PurchaseOrderDAO(PurchaseOrderActivity.this);
        List<PurchaseOrder> list = purchaseOrderDAO.getAllPurchaseOrdersByUserId(PurchaseOrderActivity.this, RepositoryUser.getAccount().getUser_id());
        Collections.reverse(list);
        return list;
    }
}
