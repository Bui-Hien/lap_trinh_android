package com.example.bt_lon.model.purchaseorder;

import com.example.bt_lon.model.product.Product;
import com.example.bt_lon.model.user.User;

import java.util.Date;

public class PurchaseOrder {
    private int purchase_order_id;
    private Product product;
    private User user;
    private int quantity;
    private Date purchase_date;
    private double cost;
    private int numberOfPurchases;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Product product, User user, int quantity, Date purchase_date, double cost) {
        this.product = product;
        this.user = user;
        this.quantity = quantity;
        this.purchase_date = purchase_date;
        this.cost = cost;
    }

    public PurchaseOrder(int purchase_order_id, Product product, User user, int quantity, Date purchase_date, double cost) {
        this.purchase_order_id = purchase_order_id;
        this.product = product;
        this.user = user;
        this.quantity = quantity;
        this.purchase_date = purchase_date;
        this.cost = cost;
    }

    public PurchaseOrder(int purchase_order_id, Product product, User user, int quantity, Date purchase_date, double cost, int numberOfPurchases) {
        this.purchase_order_id = purchase_order_id;
        this.product = product;
        this.user = user;
        this.quantity = quantity;
        this.purchase_date = purchase_date;
        this.cost = cost;
        this.numberOfPurchases = numberOfPurchases;
    }

    public int getNumberOfPurchases() {
        return numberOfPurchases;
    }

    public void setNumberOfPurchases(int numberOfPurchases) {
        this.numberOfPurchases = numberOfPurchases;
    }

    public int getPurchase_order_id() {
        return purchase_order_id;
    }

    public void setPurchase_order_id(int purchase_order_id) {
        this.purchase_order_id = purchase_order_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}

