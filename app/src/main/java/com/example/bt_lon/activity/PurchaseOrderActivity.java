package com.example.bt_lon.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_lon.R;
import com.example.bt_lon.adapter.PurchaseOrderAdapter;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.purchaseorder.PurchaseOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_order);

        ConstraintLayout constraintLayout1 = findViewById(R.id.purchase_none_product);
        ConstraintLayout constraintLayout2 = findViewById(R.id.purchase_product);
        ImageView imageBackPurchase = findViewById(R.id.imageBackPurchase);

        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();


        String productName = "Áo thun nam";
        String description = "Áo thun nam màu đen, kiểu dáng thời trang.";
        double price = 250000; // Giá của sản phẩm
        Bitmap imageProduct = BitmapFactory.decodeResource(getResources(), R.drawable.logo_miton); // Lấy hình ảnh từ tài nguyên

        Product product = new Product(productName, description, price, imageProduct);

        for (int i = 0; i < 10; i++) {
            int quantity = 10;
            Date purchaseDate = new Date(); // Khởi tạo ngày hiện tại hoặc sử dụng ngày từ dữ liệu
            double cost = 100.0; // Chi phí tổng cộng

            PurchaseOrder purchaseOrder = new PurchaseOrder(i, product, quantity, purchaseDate, cost);
            purchaseOrderList.add(purchaseOrder);
        }

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

    public void Repurchase(PurchaseOrder purchaseOrder) {
        //them purchaseOrder vao db
        Intent intent = new Intent(PurchaseOrderActivity.this, CartActivity.class);
        startActivity(intent);
        finish();
    }

}
