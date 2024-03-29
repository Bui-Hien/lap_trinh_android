package com.example.bt_lon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_lon.R;
import com.example.bt_lon.model.cart.Cart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<Cart> cartList;
    private Context mContext;

    public CartAdapter(Context context, List<Cart> cartList) {
        this.mContext = context;
        this.cartList = cartList;
    }


    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_cart, parent, false);
        return new CartAdapter.ViewHolder(listItem);
    }
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        Cart cart = cartList.get(position);

        holder.imgViewCart.setImageBitmap(cart.getProduct().getImage_product());
        holder.tvCartNameProduct.setText(String.valueOf(cart.getProduct().getProduct_name()));
        holder.tvCartDescriptionProduct.setText(String.valueOf(cart.getProduct().getDescription()));
        holder.tvCartPriceProduct.setText(String.valueOf(cart.getProduct().getPrice()));
        holder.tvCartQualityProduct.setText(String.valueOf(cart.getQuantity()));
        holder.checkBoxCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.checkBoxCart.setChecked(holder.checkBoxCart.isChecked());
                cart.setChecked(holder.checkBoxCart.isChecked());
            }
        });
        holder.btnCartMinusProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Đã mua lại sản phẩm.", Toast.LENGTH_LONG).show();
            }
        });
        holder.btnCartPlusProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Đã mua lại sản phẩm.", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return cartList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBoxCart;
        public ImageView imgViewCart;
        public TextView tvCartNameProduct;
        public TextView tvCartDescriptionProduct;
        public TextView tvCartPriceProduct;
        public TextView tvCartQualityProduct;
        public Button btnCartMinusProduct;
        public Button btnCartPlusProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.checkBoxCart = (CheckBox) itemView.findViewById(R.id.checkBoxCart);
            this.imgViewCart = (ImageView) itemView.findViewById(R.id.imgViewCart);
            this.tvCartNameProduct = (TextView) itemView.findViewById(R.id.tvCartNameProduct);
            this.tvCartDescriptionProduct = (TextView) itemView.findViewById(R.id.tvCartDescriptionProduct);
            this.tvCartPriceProduct = (TextView) itemView.findViewById(R.id.tvCartPriceProduct);
            this.tvCartQualityProduct = (TextView) itemView.findViewById(R.id.tvCartQualityProduct);
            this.btnCartMinusProduct = (Button) itemView.findViewById(R.id.btnCartMinusProduct);
            this.btnCartPlusProduct = (Button) itemView.findViewById(R.id.btnCartPlusProduct);
        }
    }
}
