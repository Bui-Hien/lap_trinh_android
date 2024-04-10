package com.example.bt_lon.fragment;

import static java.util.Locale.filter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.bt_lon.R;
import com.example.bt_lon.activity.LoginActivity;
import com.example.bt_lon.adapter.ProductAdapter;
import com.example.bt_lon.model.category.Category;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.sqlite_open_helper.DAO.ProductDAO;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;

import java.util.ArrayList;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ImageSlider imageSlider = view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.item_1, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.item_2, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.item_3, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.item_4, ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);


        ProductDAO productDAO = new ProductDAO(HomeFragment.this.getContext());
//        List<Product> productList = new ArrayList<>();
//        productList = productDAO.getAllProducts();

        Button button1 = view.findViewById(R.id.button_category1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> productList = new ArrayList<>();
                productList = productDAO.getAllProductsByCategoryId(HomeFragment.this.getContext(), 1);
                RecyclerView rcv_product = view.findViewById(R.id.recyclerViewItem);
                ProductAdapter mProductAdapter = new ProductAdapter(HomeFragment.this, productList);
                rcv_product.setLayoutManager(new GridLayoutManager(requireContext(), 2));
                mProductAdapter.setData(productList);
                rcv_product.setAdapter(mProductAdapter);
            }
        });

        Button button2 = view.findViewById(R.id.button_category2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> productList = new ArrayList<>();
                productList = productDAO.getAllProductsByCategoryId(HomeFragment.this.getContext(), 2);
                RecyclerView rcv_product = view.findViewById(R.id.recyclerViewItem);
                ProductAdapter mProductAdapter = new ProductAdapter(HomeFragment.this, productList);
                rcv_product.setLayoutManager(new GridLayoutManager(requireContext(), 2));
                mProductAdapter.setData(productList);
                rcv_product.setAdapter(mProductAdapter);
            }
        });

        Button button3 = view.findViewById(R.id.button_category3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> productList = new ArrayList<>();
                productList = productDAO.getAllProductsByCategoryId(HomeFragment.this.getContext(), 3);
                RecyclerView rcv_product = view.findViewById(R.id.recyclerViewItem);
                ProductAdapter mProductAdapter = new ProductAdapter(HomeFragment.this, productList);
                rcv_product.setLayoutManager(new GridLayoutManager(requireContext(), 2));
                mProductAdapter.setData(productList);
                rcv_product.setAdapter(mProductAdapter);
            }
        });

        Button button4 = view.findViewById(R.id.button_category4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> productList = new ArrayList<>();
                productList = productDAO.getAllProductsByCategoryId(HomeFragment.this.getContext(), 4);
                RecyclerView rcv_product = view.findViewById(R.id.recyclerViewItem);
                ProductAdapter mProductAdapter = new ProductAdapter(HomeFragment.this, productList);
                rcv_product.setLayoutManager(new GridLayoutManager(requireContext(), 2));
                mProductAdapter.setData(productList);
                rcv_product.setAdapter(mProductAdapter);
            }
        });

        Button button5 = view.findViewById(R.id.button_category5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> productList = new ArrayList<>();
                productList = productDAO.getAllProductsByCategoryId(HomeFragment.this.getContext(), 5);
                RecyclerView rcv_product = view.findViewById(R.id.recyclerViewItem);
                ProductAdapter mProductAdapter = new ProductAdapter(HomeFragment.this, productList);
                rcv_product.setLayoutManager(new GridLayoutManager(requireContext(), 2));
                mProductAdapter.setData(productList);
                rcv_product.setAdapter(mProductAdapter);
            }
        });

        Button button6 = view.findViewById(R.id.button_category6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> productList = new ArrayList<>();
                productList = productDAO.getAllProductsByCategoryId(HomeFragment.this.getContext(), 6);
                RecyclerView rcv_product = view.findViewById(R.id.recyclerViewItem);
                ProductAdapter mProductAdapter = new ProductAdapter(HomeFragment.this, productList);
                rcv_product.setLayoutManager(new GridLayoutManager(requireContext(), 2));
                mProductAdapter.setData(productList);
                rcv_product.setAdapter(mProductAdapter);
            }
        });
    }

    /*
     * khoi tao product list
     * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ProductDAO productDAO = new ProductDAO(HomeFragment.this.getContext());
        List<Product> productList = new ArrayList<>();
        productList = productDAO.getAllProducts();

        RecyclerView rcv_product = view.findViewById(R.id.recyclerViewItem);
        ProductAdapter mProductAdapter = new ProductAdapter(HomeFragment.this, productList);
        rcv_product.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        mProductAdapter.setData(productList);
        rcv_product.setAdapter(mProductAdapter);

        SearchView searchView = view.findViewById(R.id.searchView);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ProductDAO productDAO = new ProductDAO(HomeFragment.this.getContext());
                List<Product> productList = new ArrayList<>();
                productList = productDAO.getAllProducts();

                ArrayList<Product> filteredList = new ArrayList<>();
                for (Product product : productList) {
                    if (product.getProduct_name().toLowerCase().contains(newText.toLowerCase())) {
                        filteredList.add(product);
                    }
                }

                RecyclerView rcv_product = view.findViewById(R.id.recyclerViewItem);
                mProductAdapter.filterList(filteredList);
                rcv_product.setLayoutManager(new GridLayoutManager(requireContext(), 2));
                mProductAdapter.setData(productList);
                rcv_product.setAdapter(mProductAdapter);

                return true;
            }
        });


        return view;
    }


}