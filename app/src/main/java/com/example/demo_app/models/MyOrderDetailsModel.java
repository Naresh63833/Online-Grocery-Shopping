package com.example.demo_app.models;

import java.io.Serializable;

public class MyOrderDetailsModel implements Serializable {
    String name;
    String phone;
    String address;
    String products;
    String total;
    String currentDate;
    String currentTime;
    String documentId;

    public MyOrderDetailsModel(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public MyOrderDetailsModel() {
    }

    public MyOrderDetailsModel(String name, String phone, String address, String products, String total, String currentDate, String currentTime) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.products = products;
        this.total = total;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

}
