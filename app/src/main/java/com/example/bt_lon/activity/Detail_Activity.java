package com.example.bt_lon.activity;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import com.example.bt_lon.R;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.purchaseorder.PurchaseOrder;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.sqlite_open_helper.DAO.CartDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.ProductDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.PurchaseOrderDAO;

import java.util.Date;

public class Detail_Activity extends AppCompatActivity {
    ImageView img, imageBack;
    TextView txtGia, txtTen, txtMota;
    Button btnThem, btnMua;
    int product_id;
    Product product;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        product_id = intent.getIntExtra("product_id", 1);

        ProductDAO productDAO = new ProductDAO(this);

        product = productDAO.getProductById(this, product_id);

        img = (ImageView) findViewById(R.id.imgProduct);
        imageBack = (ImageView) findViewById(R.id.imageBack);
        txtGia = findViewById(R.id.txtGia);
        txtTen = findViewById(R.id.txtTen);
        txtMota = findViewById(R.id.txtMota);
        btnThem = findViewById(R.id.btnThem);
        btnMua = findViewById(R.id.btnMua);


        img.setImageBitmap(product.getImage_product());
        txtGia.setText("Giá sản phẩm: " + product.getPrice() + " đ");
        txtTen.setText("Tên sản phẩm: " + product.getProduct_name());
        txtMota.setText(product.getDescription());
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openThemDialog(Gravity.CENTER);

            }
        });

        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMuaDialog(Gravity.CENTER);
            }
        });
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void openMuaDialog(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_mua);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);


        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true);
        }

        Button btnThoat = dialog.findViewById(R.id.btnThoat);
        Button btnMua = dialog.findViewById(R.id.btnMua);
        Button btntru = dialog.findViewById(R.id.btntru);
        Button btngiatri = dialog.findViewById(R.id.btngiatri);
        Button btncong = dialog.findViewById(R.id.btncong);

        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = Integer.parseInt(String.valueOf(btngiatri.getText()));
                sl += 1;
                btngiatri.setText(String.valueOf(sl));
            }
        });

        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = Integer.parseInt(String.valueOf(btngiatri.getText()));
                if (sl > 1) {
                    sl -= 1;
                    btngiatri.setText(String.valueOf(sl));
                }
            }
        });


        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDAO productDAO = new ProductDAO(Detail_Activity.this);
                CartDAO cartDAO = new CartDAO(Detail_Activity.this);
                PurchaseOrderDAO purchaseOrderDAO = new PurchaseOrderDAO(Detail_Activity.this);
                int sl = Integer.parseInt(String.valueOf(btngiatri.getText()));
                double cost = sl * product.getPrice();
                PurchaseOrder purchaseOrder = new
                        PurchaseOrder(
                        product,
                        RepositoryUser.getAccount(),
                        sl,
                        new Date(),
                        cost
                );
                purchaseOrderDAO.insertPurchaseOrder(purchaseOrder);
                if (sl == product.getQuantity()) {
//                            productDAO.deleteProduct(cartList.get(i).getProduct().getProduct_id());
                    productDAO.updateQuantity(product, 0);
                    cartDAO.deleteProductFromCart(RepositoryUser.getAccount().getUser_id(), product.getProduct_id());
                } else {
                    int quantityNew = product.getQuantity() - sl;
                    productDAO.updateQuantity(product, quantityNew);
                    cartDAO.deleteProductFromCart(RepositoryUser.getAccount().getUser_id(), product.getProduct_id());
                }
                Intent intent = new Intent(Detail_Activity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();

    }

    private void openThemDialog(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_them);
//        dialog.setContentView(R.layout.layout_mua);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);


        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true);
        }

        Button btnThoat = dialog.findViewById(R.id.btnThoat);
        Button btnThem = dialog.findViewById(R.id.btnThem);
        Button btntru = dialog.findViewById(R.id.btntru);
        Button btngiatri = dialog.findViewById(R.id.btngiatri);
        Button btncong = dialog.findViewById(R.id.btncong);

        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = Integer.parseInt(String.valueOf(btngiatri.getText()));
                sl += 1;
                btngiatri.setText(String.valueOf(sl));
            }
        });

        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = Integer.parseInt(String.valueOf(btngiatri.getText()));
                if (sl > 1) {
                    sl -= 1;
                    btngiatri.setText(String.valueOf(sl));
                }
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detail_Activity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();

    }

}