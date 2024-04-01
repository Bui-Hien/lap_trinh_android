package com.example.bt_lon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_lon.R;
import com.example.bt_lon.model.purchaseorder.PurchaseOrder;

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
        public ImageView imgProductPurchased;
        public ConstraintLayout constranlayoutProductPurchased;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvPricePurchase = itemView.findViewById(R.id.tvPricePurchase);
            this.imgProductPurchased = itemView.findViewById(R.id.imgProductPurchased);
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
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PurchaseOrder data = dataList.get(position);
        if (data.getProduct() != null) {
            holder.tvPricePurchase.setText(String.valueOf(data.getProduct().getPrice()));
            holder.imgProductPurchased.setImageBitmap(data.getProduct().getImage_product());
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
