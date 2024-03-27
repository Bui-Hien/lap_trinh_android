package com.example.bt_lon.model.purchaseorder;

import java.util.Date;

public class PurchaseOrder {
    private int purchase_order_id;
    private int product_id;
    private int quantity;
    private Date purchase_time;
    private double cost;

    public PurchaseOrder() {
    }

    public PurchaseOrder(int purchase_order_id, int product_id, int quantity, Date purchase_time, double cost) {
        this.purchase_order_id = purchase_order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.purchase_time = purchase_time;
        this.cost = cost;
    }

    public int getPurchase_order_id() {
        return purchase_order_id;
    }

    public void setPurchase_order_id(int purchase_order_id) {
        this.purchase_order_id = purchase_order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getPurchase_time() {
        return purchase_time;
    }

    public void setPurchase_time(Date purchase_time) {
        this.purchase_time = purchase_time;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}

