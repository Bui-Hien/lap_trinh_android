package com.example.bt_lon.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_lon.R;
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
    private HomeFragment mContext;

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        Button button_buy;
        ImageView imgProduct;

        TextView nameProduct;
        TextView descriptionProduct;
        TextView priceProduct;



        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            button_buy = itemView.findViewById(R.id.button_buy);
            imgProduct = itemView.findViewById(R.id.img_item);
            nameProduct = itemView.findViewById(R.id.product_name);
            descriptionProduct = itemView.findViewById(R.id.product_description);
            priceProduct = itemView.findViewById(R.id.product_price);

        }
    }

    public ProductAdapter(HomeFragment context, List<Product> productList) {
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
        Product product = productList.get(position);

        holder.button_buy.setText(String.valueOf(product.getProduct_id()));
        holder.nameProduct.setText(product.getProduct_name());
        holder.descriptionProduct.setText(product.getDescription());
        holder.priceProduct.setText(product.getPrice()+"$");

//        private String price;
//        private String product_name;
//        private String description;

        holder.imgProduct.setImageBitmap(product.getImage_product());
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

    @Override
    public int getItemCount() {
        if (productList != null) {
            return productList.size();
        } else return 0;
    }


}
