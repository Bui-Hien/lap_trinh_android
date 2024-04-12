package com.example.bt_lon.model.product;


import android.graphics.Bitmap;

import com.example.bt_lon.model.category.Category;

import java.io.Serializable;

public class Product implements Serializable {
    private String product_id;
    private Category category;
    private String product_name;
    private String description;
    private double price;
    private int quantity;
    private Bitmap image_product;

    String base64;

    public Product() {
    }

    public Product(String product_id) {
        this.product_id = product_id;
    }

    public Product(String product_name, String description, double price, Bitmap image_product) {

        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.image_product = image_product;
    }

    public Product(String product_id, Bitmap image_product) {
        this.product_id = product_id;
        this.image_product = image_product;
    }

    public Product(String product_id, Category category, String product_name, String description, double price, int quantity, Bitmap image_product) {
        this.product_id = product_id;
        this.category = category;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.image_product = image_product;
    }

    public Product(String product_id, Category category, String product_name, String description, double price, Bitmap image_product) {
        this.product_id = product_id;
        this.category = category;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.image_product = image_product;
    }

    public Product( Category category, String product_name, String description, double price, Bitmap image_product) {
        this.category = category;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.image_product = image_product;
    }

    public Product( Category category, String product_name, String description, double price, Bitmap image_product,int quantity) {
        this.category = category;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.image_product = image_product;
        this.quantity = quantity;
    }

    public Product( Category category, String product_name, String description, double price, String base64,int quantity) {
        this.category = category;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.base64 = base64;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImage_product(Bitmap image_product) {
        this.image_product = image_product;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
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
