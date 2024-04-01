package com.example.bt_lon.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_lon.R;
import com.example.bt_lon.activity.CartActivity;
import com.example.bt_lon.activity.PurchaseOrderActivity;
import com.example.bt_lon.model.purchaseorder.PurchaseOrder;

import java.text.SimpleDateFormat;
import java.util.List;

public class PurchaseOrderAdapter extends RecyclerView.Adapter<PurchaseOrderAdapter.ViewHolder> {
    private List<PurchaseOrder> purchaseOrderList;
    private Context mContext;

    public PurchaseOrderAdapter(Context context, List<PurchaseOrder> purchaseOrderList) {
        this.mContext = context;
        this.purchaseOrderList = purchaseOrderList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_purchase_order, parent, false);
        return new ViewHolder(listItem);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PurchaseOrder purchaseOrder = purchaseOrderList.get(position);

        // Định dạng ngày tháng
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        holder.tvPurchaseDate.setText(String.valueOf(formatter.format(purchaseOrder.getPurchase_date())));
        holder.imgViewPurchase.setImageBitmap(purchaseOrder.getProduct().getImage_product());
        holder.tvPurchaseName.setText(String.valueOf(purchaseOrder.getProduct().getProduct_name()));
        holder.tvPurchaseQuantity.setText("x" + purchaseOrder.getQuantity());
        holder.tvPurchaseCostOne.setText(String.valueOf(purchaseOrder.getProduct().getPrice()));
        holder.tvPurchaseQuantity2.setText(String.valueOf(purchaseOrder.getQuantity()));
        holder.tvPurchaseCostTotal.setText(String.valueOf(purchaseOrder.getCost()));
        if (position == purchaseOrderList.size() - 1) {
            GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            layoutParams.bottomMargin = 0;
            holder.itemView.setLayoutParams(layoutParams);

        }
        holder.btnRepurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PurchaseOrderActivity) mContext).Repurchase(purchaseOrder);
            }
        });
    }


    @Override
    public int getItemCount() {
        return purchaseOrderList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPurchaseDate;
        public ImageView imgViewPurchase;
        public TextView tvPurchaseName;
        public TextView tvPurchaseQuantity;
        public TextView tvPurchaseCostOne;
        public TextView tvPurchaseQuantity2;
        public TextView tvPurchaseCostTotal;
        public Button btnRepurchase;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvPurchaseDate = (TextView) itemView.findViewById(R.id.tvPurchaseDate);
            this.imgViewPurchase = (ImageView) itemView.findViewById(R.id.imgViewPurchase);
            this.tvPurchaseName = (TextView) itemView.findViewById(R.id.tvPurchaseName);
            this.tvPurchaseQuantity = (TextView) itemView.findViewById(R.id.tvPurchaseQuantity);
            this.tvPurchaseCostOne = (TextView) itemView.findViewById(R.id.tvPurchasePriceProduct);
            this.tvPurchaseQuantity2 = (TextView) itemView.findViewById(R.id.tvPurchaseQuantity2);
            this.tvPurchaseCostTotal = (TextView) itemView.findViewById(R.id.tvPurchaseCostTotal);
            this.btnRepurchase = (Button) itemView.findViewById(R.id.btnRepurchase);
        }
    }
}
