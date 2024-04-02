package com.example.bt_lon.fragment;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bt_lon.R;
import com.example.bt_lon.activity.CartActivity;
import com.example.bt_lon.activity.ChangePasswordActivity;
import com.example.bt_lon.activity.LoginActivity;
import com.example.bt_lon.activity.ProfileActivity;
import com.example.bt_lon.activity.PurchaseOrderActivity;
import com.example.bt_lon.activity.RegisterActivity;
import com.example.bt_lon.adapter.Purchased;
import com.example.bt_lon.model.purchaseorder.PurchaseOrder;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment {
    private ImageView imageViewLogined;
    private ImageView imgPurchasedCart;
    private ImageView imageViewLogout;
    private TextView tvNameAccount;
    private TextView tvPurchasedCart;
    private Button bntLogOut;
    private ConstraintLayout accountSetup;
    private Button btnSigin;
    private Button btnSigup;
    private ConstraintLayout logined, noLogin, constrainLayoutNoPurchased;
    private RecyclerView recyclerViewPurchased;
    private Purchased purchasedAdapter;
    private List<PurchaseOrder> purchaseOrderList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageViewLogined = view.findViewById(R.id.imageViewLogined);
        tvNameAccount = view.findViewById(R.id.tvNameAccount);
        bntLogOut = view.findViewById(R.id.bntLogOut);
        accountSetup = view.findViewById(R.id.AccountSetup);
        logined = view.findViewById(R.id.constraintLayoutLogined);
        noLogin = view.findViewById(R.id.constraintLayoutLogout);
        btnSigin = view.findViewById(R.id.btnSigin);
        btnSigup = view.findViewById(R.id.btnSigup);
        imageViewLogout = view.findViewById(R.id.imageViewLogout);
        imgPurchasedCart = view.findViewById(R.id.imgPurchasedCart);
        tvPurchasedCart = view.findViewById(R.id.tvPurchasedCart);
        constrainLayoutNoPurchased = view.findViewById(R.id.constrainLayoutNoPurchased);
        recyclerViewPurchased = view.findViewById(R.id.recyclerViewPurchased);

        setupUI();
    }

    private void setupUI() {
        User user = RepositoryUser.getAccount();
        if (user != null) {
            logined.setVisibility(View.VISIBLE);
            noLogin.setVisibility(View.GONE);
            bntLogOut.setVisibility(View.VISIBLE);

            tvNameAccount.setText(user.getFull_name());
            Bitmap profileImage = user.getProfileImage();
            if (profileImage != null) {
                imageViewLogined.setImageBitmap(profileImage);
            } else {
                if (user.getProfileImage() != null) {
                    imageViewLogined.setImageBitmap(user.getProfileImage());
                } else {
                    imageViewLogined.setScaleX(1.2F);
                    imageViewLogined.setScaleY(1.2F);
                }
            }
            bntLogOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RepositoryUser.deleteAccount();
                    setupUI();
                }
            });
            imageViewLogined.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ProfileActivity.class);
                    startActivity(intent);
                }
            });
            tvNameAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ProfileActivity.class);
                    startActivity(intent);
                }
            });
            accountSetup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                    intent.putExtra("previous", "change");
                    startActivity(intent);
                }
            });
            imgPurchasedCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), PurchaseOrderActivity.class);
                    startActivity(intent);
                }
            });
            tvPurchasedCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), PurchaseOrderActivity.class);
                    startActivity(intent);
                }
            });

            boolean d = true;
            if (d) {
                initData();
                purchaseOrderList = (purchaseOrderList == null) ? new ArrayList<>() : purchaseOrderList;
                purchasedAdapter = (purchasedAdapter == null) ? new Purchased(purchaseOrderList, getContext()) : purchasedAdapter;

                RecyclerView recyclerView = getView().findViewById(R.id.recyclerViewPurchased);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(purchasedAdapter);

                constrainLayoutNoPurchased.setVisibility(View.GONE);
                recyclerViewPurchased.setVisibility(View.VISIBLE);
            } else {
                constrainLayoutNoPurchased.setVisibility(View.VISIBLE);
                recyclerViewPurchased.setVisibility(View.GONE);
            }
        } else {
            logined.setVisibility(View.GONE);
            noLogin.setVisibility(View.VISIBLE);
            bntLogOut.setVisibility(View.GONE);
            imageViewLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            btnSigin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            btnSigup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), RegisterActivity.class);
                    startActivity(intent);
                }
            });
            accountSetup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            imgPurchasedCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            tvPurchasedCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setupUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    private void initData() {
        purchaseOrderList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            purchaseOrderList.add(new PurchaseOrder());
        }
    }
}
