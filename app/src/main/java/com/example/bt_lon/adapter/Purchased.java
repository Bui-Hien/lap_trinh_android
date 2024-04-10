package com.example.bt_lon.adapter;

import static androidx.core.content.ContextCompat.startActivity;
import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_lon.R;
import com.example.bt_lon.activity.CartActivity;
import com.example.bt_lon.activity.LoginActivity;
import com.example.bt_lon.activity.PurchaseOrderActivity;
import com.example.bt_lon.fragment.AccountFragment;
import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.purchaseorder.PurchaseOrder;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.sqlite_open_helper.DAO.CartDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.ProductDAO;

import java.util.List;

public class Purchased extends RecyclerView.Adapter<Purchased.ViewHolder> {
    private List<PurchaseOrder> dataList;
    private Context context;

    // Constructor
    public Purchased(List<PurchaseOrder> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPricePurchase;
        public TextView tvNumberOfPurchases;
        public ImageView imgProductPurchased;
        public ImageView iconRepurchased;
        public ConstraintLayout constranlayoutProductPurchased;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvPricePurchase = itemView.findViewById(R.id.tvPricePurchase);
            this.tvNumberOfPurchases = itemView.findViewById(R.id.tvNumberOfPurchases);
            this.imgProductPurchased = itemView.findViewById(R.id.imgProductPurchased);
            this.iconRepurchased = itemView.findViewById(R.id.iconRepurchased);
            this.constranlayoutProductPurchased = (ConstraintLayout) itemView.findViewById(R.id.linearLayoutCart);
        }
    }

    // onCreateViewHolder method
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.product_purchased, parent, false);
        return new Purchased.ViewHolder(listItem);
    }

    // onBindViewHolder method
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PurchaseOrder data = dataList.get(position);
        if (data.getProduct() != null) {
            holder.tvPricePurchase.setText(String.valueOf(data.getProduct().getPrice()));
            holder.tvNumberOfPurchases.setText("Đã mua " + data.getNumberOfPurchases());
            holder.imgProductPurchased.setImageBitmap(data.getProduct().getImage_product());
            holder.iconRepurchased.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductDAO productDAO = new ProductDAO(v.getContext());
                    Product productCheck = productDAO.getProductById(v.getContext(), data.getProduct().getProduct_id());
                    if (productCheck.getQuantity() != 0) {
                        Cart cart = new Cart(data.getUser(), data.getProduct());
                        if (RepositoryUser.getAccount() != null) {
                            CartDAO cartDAO = new CartDAO(v.getContext());
                            cartDAO.storeProductToCart(cart, 1);
                            Intent intent = new Intent(v.getContext(), CartActivity.class);
                            v.getContext().startActivity(intent);
                        } else {
                            Intent intent = new Intent(v.getContext(), LoginActivity.class);
                            v.getContext().startActivity(intent);
                        }
                    } else {
                        Toast.makeText(context, "Sản phẩm đã hết.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if (position == dataList.size() - 1) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            layoutParams.rightMargin = 0;
            holder.itemView.setLayoutParams(layoutParams);
        }
    }


    // getItemCount method
    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
