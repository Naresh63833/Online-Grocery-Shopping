package com.example.demo_app.models;

public class UploadModel {
    String name;
    String description;
    String price;
    String rating;
    String type;
    static String img_url;

    public UploadModel(String img_url) {
        this.img_url = img_url;
    }

    public static String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public UploadModel() {
    }

    public UploadModel(String name, String description, String price, String rating, String type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.type = type;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
}
