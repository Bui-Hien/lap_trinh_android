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


//        Bitmap productImage = BitmapFactory.decodeResource(requireContext().getResources(), R.drawable.girl);
//        List<Product> productList = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            Product product = new Product(
//                    i, category,
//                    "Book Title" + i,
//                    "Description of the book" + i,
//                    19.99, productImage);
//            productList.add(product);
//        }
//        ProductDAO productDAO = new ProductDAO(HomeFragment.this.getContext());
//        List<Product> productList = new ArrayList<>();
//        productList = productDAO.getAllProducts();
//        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewItem);
//        ProductAdapter productAdapter = new ProductAdapter(this, productList);
//        recyclerView.setAdapter(productAdapter);
//        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        ProductDAO productDAO = new ProductDAO(HomeFragment.this.getContext());
        List<Product> productList = new ArrayList<>();
        productList = productDAO.getAllProducts();

        Button button1 = view.findViewById(R.id.button_category1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> productList = new ArrayList<>();
                productList = productDAO.getAllProductsByCategoryId(HomeFragment.this.getContext(),1);
                RecyclerView rcv_product = view.findViewById(R.id.recyclerViewItem);
                ProductAdapter mProductAdapter = new ProductAdapter(HomeFragment.this,productList);
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
                productList = productDAO.getAllProductsByCategoryId(HomeFragment.this.getContext(),2);
                RecyclerView rcv_product = view.findViewById(R.id.recyclerViewItem);
                ProductAdapter mProductAdapter = new ProductAdapter(HomeFragment.this,productList);
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
                productList = productDAO.getAllProductsByCategoryId(HomeFragment.this.getContext(),3);
                RecyclerView rcv_product = view.findViewById(R.id.recyclerViewItem);
                ProductAdapter mProductAdapter = new ProductAdapter(HomeFragment.this,productList);
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
                productList = productDAO.getAllProductsByCategoryId(HomeFragment.this.getContext(),4);
                RecyclerView rcv_product = view.findViewById(R.id.recyclerViewItem);
                ProductAdapter mProductAdapter = new ProductAdapter(HomeFragment.this,productList);
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
                productList = productDAO.getAllProductsByCategoryId(HomeFragment.this.getContext(),5);
                RecyclerView rcv_product = view.findViewById(R.id.recyclerViewItem);
                ProductAdapter mProductAdapter = new ProductAdapter(HomeFragment.this,productList);
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
                productList = productDAO.getAllProductsByCategoryId(HomeFragment.this.getContext(),6);
                RecyclerView rcv_product = view.findViewById(R.id.recyclerViewItem);
                ProductAdapter mProductAdapter = new ProductAdapter(HomeFragment.this,productList);
                rcv_product.setLayoutManager(new GridLayoutManager(requireContext(), 2));
                mProductAdapter.setData(productList);
                rcv_product.setAdapter(mProductAdapter);
            }
        });
    }

    /*
    * fake data cho product list
    * */
//    public List<Product> getListItem() {
//        List<Product> list = new ArrayList<>();
//        list.add(new Product("item 1", "day la item 1",20, BitmapFactory.decodeResource(getResources(), R.drawable.item_1)));
//        list.add(new Product("item 2", "day la item 2",20, BitmapFactory.decodeResource(getResources(), R.drawable.item_2)));
//        list.add(new Product("item 3", "day la item 3",20, BitmapFactory.decodeResource(getResources(), R.drawable.item_3)));
//        list.add(new Product("item 4", "day la item 4",20, BitmapFactory.decodeResource(getResources(), R.drawable.item_4)));
//        list.add(new Product("item 1", "day la item 1",20, BitmapFactory.decodeResource(getResources(), R.drawable.item_1)));
//        list.add(new Product("item 2", "day la item 2",20, BitmapFactory.decodeResource(getResources(), R.drawable.item_2)));
//        return list;
//
//    }

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
        ProductAdapter mProductAdapter = new ProductAdapter(HomeFragment.this,productList);
        rcv_product.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        mProductAdapter.setData(productList);
        rcv_product.setAdapter(mProductAdapter);
        return view;
    }


}