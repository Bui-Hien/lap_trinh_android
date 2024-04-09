package com.example.bt_lon.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bt_lon.R;
import com.example.bt_lon.activity.LoginActivity;
import com.example.bt_lon.adapter.ProductAdapter;
import com.example.bt_lon.model.category.Category;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.sqlite_open_helper.DAO.ProductDAO;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HomeFragment extends Fragment {
    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Product> productList = new ArrayList<>();

        ProductDAO productDAO = new ProductDAO(HomeFragment.this.getContext());
        productList = productDAO.getAllProducts();

        // Filter out products with quantity equal to 0
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getQuantity() == 0) {
                iterator.remove();
            }
        }
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        ProductAdapter productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}