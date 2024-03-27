package com.example.bt_lon.model.product;


import android.graphics.Bitmap;

import com.example.bt_lon.model.category.Category;

public class Product {
    private int product_id;
    private int category_id;
    private String product_name;
    private String description;
    private double price;
    private String link_img;
    private Bitmap imageBitmap;

    public Product() {
    }

    public Product(int product_id, int category_id, String product_name, String description, double price, String link_img, Bitmap imageBitmap) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.link_img = link_img;
        this.imageBitmap = imageBitmap;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }
}
