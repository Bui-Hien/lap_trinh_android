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
import com.example.bt_lon.adapter.CartAdapter;
import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public CartFragment() {
    }

    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the cartList
        List<Cart> cartList = new ArrayList<>();

        // Load profile image and product image
        Bitmap productImage = BitmapFactory.decodeResource(getResources(), R.drawable.logo_miton);

        // Create a User object
        User user = new User(1, "hienbui", productImage);

        // Populate the cartList with Cart objects
        for (int i = 0; i < 10; i++) {
            Product product = new Product("productName "+i,"description"+ i, 100000, productImage);
            Cart cart = new Cart(i, user, product, false, 1);
            cartList.add(cart);
        }

        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.recyclerViewCart); // Make sure to find RecyclerView in the view hierarchy of the fragment
        CartAdapter myAdapter = new CartAdapter(requireContext(), cartList); // Pass context and cartList
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 1)); // Use requireContext() to get the context
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
}