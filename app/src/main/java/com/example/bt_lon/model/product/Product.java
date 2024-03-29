package com.example.bt_lon.model.product;


import android.graphics.Bitmap;

import com.example.bt_lon.model.category.Category;

public class Product {
    private int product_id;
    private Category category;
    private String product_name;
    private String description;
    private double price;
    private Bitmap image_product;

    public Product() {
    }
    public Product( String product_name,String description, double price, Bitmap image_product) {

        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.image_product = image_product;
    }
    public Product(int product_id, Category category, String product_name, String description, double price, Bitmap image_product) {
        this.product_id = product_id;
        this.category = category;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.image_product = image_product;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Bitmap getImage_product() {
        return image_product;
    }

    public void setImageBitmap(Bitmap image_product) {
        this.image_product = image_product;
    }
}
