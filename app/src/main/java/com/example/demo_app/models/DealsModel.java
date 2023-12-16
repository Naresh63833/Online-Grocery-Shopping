package com.example.demo_app.models;

public class DealsModel {
    String name;
    String description;
    String rating;

    public DealsModel(String name, String description, String rating, String discount) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.discount = discount;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    String discount;
    String img_url;
    String type;

    public DealsModel() {
    }

    public DealsModel(String img_url, String type) {
        this.img_url = img_url;
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
