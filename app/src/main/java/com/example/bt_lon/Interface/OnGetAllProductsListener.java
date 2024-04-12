package com.example.bt_lon.Interface;
import com.example.bt_lon.model.product.Product;

import java.util.List;

public interface OnGetAllProductsListener {

        void onSuccess(List<Product> productList);
        void onError(String errorMessage);

}
