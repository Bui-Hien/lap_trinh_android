package com.example.bt_lon.model.cart;


import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.user.User;

public class Cart {
    private int cart_id;
    private User user;
    private Product product;
    private boolean isChecked;
    private int quantity;


    public Cart() {
    }

    public Cart(int cart_id, User user, Product product, boolean isChecked, int quantity) {
        this.cart_id = cart_id;
        this.user = user;
        this.product = product;
        this.isChecked = isChecked;
        this.quantity = quantity;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
