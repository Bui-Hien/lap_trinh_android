package com.example.bt_lon.Interface;

import com.example.bt_lon.model.product.Product;

public interface OnGetProductByIdListener {
    void onSuccess(Product product);
    void onError(String errorMessage);
}
