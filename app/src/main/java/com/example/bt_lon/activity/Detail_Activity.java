package com.example.bt_lon.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bt_lon.R;
import com.example.bt_lon.model.product.Product;

public class Detail_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }

        Product product = (Product) bundle.get("object_item");
        TextView textView = findViewById(R.id.textViewDetail);
        assert product != null;
        textView.setText(product.getProduct_name());

    }
}