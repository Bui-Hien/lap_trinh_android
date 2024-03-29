package com.example.bt_lon.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_lon.R;
import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.product.Product;

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
        Product product = cart.getProduct();

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        Drawable btnBackground = new ColorDrawable(Color.parseColor("#e8e8e8"));
        // Setting up views
        holder.imgViewCart.setImageBitmap(product.getImage_product());
        holder.tvCartNameProduct.setText(product.getProduct_name());
        holder.tvCartDescriptionProduct.setText(product.getDescription());
        holder.tvCartPriceProduct.setText(decimalFormat.format(product.getPrice()).replace(",", "."));
        holder.tvCartQualityProduct.setText(String.valueOf(cart.getQuantity()));
        holder.checkBoxCart.setChecked(cart.isChecked());

        // CheckBox click listener
        holder.checkBoxCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cart.setChecked(isChecked);
                Toast.makeText(mContext, "Đã được check " + (isChecked ? "true" : "false"), Toast.LENGTH_LONG).show();
            }
        });

        // Plus button click listener
        holder.btnCartPlusProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //giả sử tổng số lượng tất cả sản phầm là 10 sản phầm

                if (cart.getQuantity() < 10) {
                    holder.btnCartMinusProduct.setBackgroundDrawable(btnBackground);
                    int quantity = cart.getQuantity() + 1;
                    cart.setQuantity(quantity);
                    holder.tvCartQualityProduct.setText(String.valueOf(quantity));
                    Toast.makeText(mContext, "Đã tăng số lượng sản phẩm.", Toast.LENGTH_LONG).show();
                } else {
                    holder.btnCartPlusProduct.setBackgroundColor(Color.parseColor("#cdcdcd"));
                }

            }
        });

        // Minus button click listener
        holder.btnCartMinusProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cart.getQuantity() >= 1) {
                    holder.btnCartPlusProduct.setBackground(btnBackground);
                    int quantity = cart.getQuantity() -1;
                    cart.setQuantity(quantity);
                    holder.tvCartQualityProduct.setText(String.valueOf(quantity));
                    Toast.makeText(mContext, "Đã giảm số lượng sản phẩm.", Toast.LENGTH_LONG).show();
                } else {
                    cartList.remove(cart.getCart_id());
                }
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
