package com.example.bt_lon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_lon.R;
import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.product.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> productList;
    private Context mContext;

    public ProductAdapter(Context context, List<Product> productList) {
        this.mContext = context;
        this.productList = productList;
    }


    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_product, parent, false);
        return new ProductAdapter.ViewHolder(listItem);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product cart = productList.get(position);
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
