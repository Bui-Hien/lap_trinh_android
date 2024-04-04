package com.example.bt_lon.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_lon.R;
import com.example.bt_lon.activity.CartActivity;
import com.example.bt_lon.activity.LoginActivity;
import com.example.bt_lon.activity.PurchaseOrderActivity;
import com.example.bt_lon.fragment.HomeFragment;
import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DAO.CartDAO;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> productList;
    private HomeFragment mContext;

    public ProductAdapter(HomeFragment context, List<Product> productList) {
        this.mContext = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.btnId.setText(String.valueOf(product.getProduct_id()));
        holder.imgProduct.setImageBitmap(product.getImage_product());
        holder.btnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RepositoryUser.getAccount() != null) {
                    User user = RepositoryUser.getAccount();
                    Cart cart = new Cart(user, product, 1);
                    CartDAO cartDAO = new CartDAO(mContext.getContext());
                    cartDAO.storeProductToCart(cart);
                } else {
                    Intent intent = new Intent(mContext.getContext(), LoginActivity.class);
                    mContext.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        Button btnId;
        ImageView imgProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnId = itemView.findViewById(R.id.btnId);
            imgProduct = itemView.findViewById(R.id.imgProduct);
        }
    }
}
