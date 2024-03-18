package com.example.bt_lon.cart;


import com.example.bt_lon.product.Product;
import com.example.bt_lon.user.User;

public class Cart {
    private int cart_id;
    private User user;
    private Product product;
    private int quantity;

    public Cart() {
    }

    public Cart(int cart_id, User user, Product product, int quantity) {
        this.cart_id = cart_id;
        this.user = user;
        this.product = product;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
