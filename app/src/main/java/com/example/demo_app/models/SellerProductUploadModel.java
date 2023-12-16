package com.example.demo_app.models;

public class SellerProductUploadModel {
    String name;
    String description;
    int price;
    String rating;
    String type;
    String shop_name;
    String shop_location;

    public SellerProductUploadModel(String name, String description, int price, String rating, String type, String shop_name, String shop_location, String img_url) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.type = type;
        this.shop_name = shop_name;
        this.shop_location = shop_location;
    }

    public SellerProductUploadModel(String name, String description, String price, String rating, String type, String shop_name, String shop_location, String img_url) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_location() {
        return shop_location;
    }

    public void setShop_location(String shop_location) {
        this.shop_location = shop_location;
    }
}
