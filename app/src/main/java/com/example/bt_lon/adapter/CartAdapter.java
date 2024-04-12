package com.example.bt_lon.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_lon.R;
import com.example.bt_lon.activity.CartActivity;
import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.sqlite_open_helper.DAO.CartDAO;

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

        holder.imgViewCart.setImageBitmap(product.getImage_product());
        holder.tvCartNameProduct.setText(product.getProduct_name());
        holder.tvCartDescriptionProduct.setText(product.getDescription());
        holder.tvCartPriceProduct.setText("đ" + decimalFormat.format(product.getPrice()).replace(",", "."));
        holder.tvCartQualityProduct.setText(String.valueOf(cart.getQuantity()));
        holder.checkBoxCart.setChecked(cart.isChecked());

        if (position == cartList.size() - 1) {
            GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            layoutParams.bottomMargin = 0;
            holder.itemView.setLayoutParams(layoutParams);

        }
        if (cart.getQuantity() == cart.getProduct().getQuantity()) {
            holder.btnCartPlusProduct.setBackgroundColor(Color.parseColor("#e8e8e8"));
        } else {
            holder.btnCartPlusProduct.setBackgroundResource(R.drawable.btncongtru);
        }

        holder.checkBoxCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cart.setChecked(isChecked);
                ((CartActivity) mContext).updateText();
            }
        });
        holder.btnCartPlusProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                holder.btnCartPlusProduct.setBackgroundResource(R.drawable.btncongtru);
                if (cart.getQuantity() < cart.getProduct().getQuantity()) {
                    int quantity = cart.getQuantity() + 1;
                    cart.setQuantity(quantity);
                    holder.tvCartQualityProduct.setText(String.valueOf(quantity));
                    ((CartActivity) mContext).updateText();

//                    int userId, int productId, int newQuantity
                    CartDAO cartDAO = new CartDAO(mContext.getApplicationContext());
                    cartDAO.updateCartQuantity(cart.getUser().getUser_id(), cart.getProduct().getProduct_id(), quantity);
                }
                if (cart.getQuantity() == cart.getProduct().getQuantity())
                    holder.btnCartPlusProduct.setBackgroundColor(Color.parseColor("#e8e8e8"));
            }
        });
        Dialog dialog;
        Button btnDiaLogCancel, btnDialogDelete;

        dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.custom_dialog_delete_product_cart);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        btnDiaLogCancel = dialog.findViewById(R.id.btnCancelDeleteProductCart);
        btnDialogDelete = dialog.findViewById(R.id.btnOklDeleteProductCart);
        btnDiaLogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tvCartQualityProduct.setText("1");
                cart.setQuantity(1);
                dialog.dismiss();
            }
        });
        btnDialogDelete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                cartList.remove(holder.getAdapterPosition());
                ((CartActivity) mContext).updateText();
                notifyItemRemoved(holder.getAdapterPosition());
                notifyDataSetChanged();
                CartDAO cartDAO = new CartDAO(mContext.getApplicationContext());
//                cartDAO.deleteCartItem(cart.getUser().getUser_id(), cart.getProduct().getProduct_id());
                if (cartList.isEmpty()) {
                    ((CartActivity) mContext).updateLayout();
                }
                dialog.dismiss();
            }
        });
        holder.btnCartMinusProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                holder.btnCartPlusProduct.setBackgroundResource(R.drawable.btncongtru);
                if (cart.getQuantity() >= 1) {
                    int quantity = cart.getQuantity() - 1;
                    cart.setQuantity(quantity);
                    //int userId, int productId, int newQuantity
                    CartDAO cartDAO = new CartDAO(mContext.getApplicationContext());
                    cartDAO.updateCartQuantity(cart.getUser().getUser_id(), cart.getProduct().getProduct_id(), quantity);
                    holder.tvCartQualityProduct.setText(String.valueOf(quantity));
                    if (cart.getQuantity() < 1) {
                        dialog.show();
                    }
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
        public ConstraintLayout linearLayoutCart;

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
            this.linearLayoutCart = (ConstraintLayout) itemView.findViewById(R.id.linearLayoutCart);
        }
    }
}