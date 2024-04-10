package com.example.bt_lon.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_lon.Interface.RecycleViewItemClickListener;
import com.example.bt_lon.R;
import com.example.bt_lon.activity.Detail_Activity;
import com.example.bt_lon.activity.LoginActivity;
import com.example.bt_lon.fragment.HomeFragment;
import com.example.bt_lon.model.cart.Cart;
import com.example.bt_lon.model.category.Category;
import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DAO.CartDAO;


import org.w3c.dom.Text;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;

    private List<Product> filteredList;
    private HomeFragment mContext;

    private RecycleViewItemClickListener recycleViewItemClickListener;

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        Button button_buy;
        ImageView imgProduct;

        TextView nameProduct;
        TextView descriptionProduct;
        TextView priceProduct;

        CardView cardView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            button_buy = itemView.findViewById(R.id.button_buy);
            imgProduct = itemView.findViewById(R.id.img_item);
            nameProduct = itemView.findViewById(R.id.product_name);
            descriptionProduct = itemView.findViewById(R.id.product_description);
            priceProduct = itemView.findViewById(R.id.product_price);
            cardView = itemView.findViewById(R.id.layout_item);

        }
    }

    public ProductAdapter(HomeFragment context, List<Product> productList, RecycleViewItemClickListener recycleViewItemClickListener  ) {
        this.mContext = context;
        this.productList = productList;
        this.recycleViewItemClickListener = recycleViewItemClickListener;
    }
    public ProductAdapter(HomeFragment context, List<Product> productList  ) {
        this.mContext = context;
        this.productList = productList;
    }

    public ProductAdapter(HomeFragment context) {
        this.mContext = context;
//        this.productList = productList;
    }

    public void setData(List<Product> list) {
        this.productList = list;

    }

    public void filterList(List<Product> filteredList) {
        this.filteredList = filteredList;
        this.productList = null;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home,parent,false);
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View listItem = layoutInflater.inflate(R.layout.item_home, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product;
        if (filteredList != null && filteredList.size() > 0) {
            product = filteredList.get(position);
        } else {
            product = productList.get(position);
        }
        holder.button_buy.setText(String.valueOf(product.getProduct_id()));
        holder.nameProduct.setText(product.getProduct_name());
        holder.descriptionProduct.setText(product.getDescription());
        holder.priceProduct.setText(product.getPrice()+"$");
        holder.imgProduct.setImageBitmap(product.getImage_product());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetail(product.getProduct_id(),v);
            }
        });
        holder.button_buy.setOnClickListener(new View.OnClickListener() {
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

    public void onClickGoToDetail(int product_id,View v){
        Intent intent = new Intent(v.getContext(), Detail_Activity.class);
        intent.putExtra("product_id", product_id);
        v.getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (productList != null) {
            return productList.size();
        } else return 0;
    }


}
