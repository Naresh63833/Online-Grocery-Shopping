package com.example.demo_app.models;

import java.io.Serializable;

public class NavCategoryDetailedModel implements Serializable {
    String name;
    String img_url;
    int price;
    String type;
    String rating;
    String shop_name;
    String shop_location;
    String description;

    public NavCategoryDetailedModel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NavCategoryDetailedModel() {
    }

    public NavCategoryDetailedModel(String name, String img_url, int price, String type, String rating, String shop_name, String shop_location) {
        this.name = name;
        this.img_url = img_url;
        this.price = price;
        this.type = type;
        this.rating = rating;
        this.shop_name = shop_name;
        this.shop_location = shop_location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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
