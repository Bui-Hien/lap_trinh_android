package com.example.bt_lon.model.purchaseorder;

import com.example.bt_lon.model.product.Product;

import java.util.Date;

public class PurchaseOrder {
    private int purchase_order_id;
    private Product product;
    private int quantity;
    private Date purchase_date;
    private double cost;

    public PurchaseOrder() {
    }

    public PurchaseOrder(int purchase_order_id, Product product, int quantity, Date purchase_date, double cost) {
        this.purchase_order_id = purchase_order_id;
        this.product = product;
        this.quantity = quantity;
        this.purchase_date = purchase_date;
        this.cost = cost;
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

    public void setProduct_id(Product product) {
        this.product = product;
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

