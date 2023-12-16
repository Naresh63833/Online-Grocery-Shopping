package com.example.demo_app.models;

public class UserItemsModel {
    String Name;
    String Email;
    String Password;
    String Phone_Number;
    String Shop_Name;
    String Shop_Address;
    String Type;

    public UserItemsModel() {
    }

    public UserItemsModel(String name, String email, String password, String phone_Number, String shop_Name, String shop_Address, String type) {
        Name = name;
        Email = email;
        Password = password;
        Phone_Number = phone_Number;
        Shop_Name = shop_Name;
        Shop_Address = shop_Address;
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

    public String getShop_Name() {
        return Shop_Name;
    }

    public void setShop_Name(String shop_Name) {
        Shop_Name = shop_Name;
    }

    public String getShop_Address() {
        return Shop_Address;
    }

    public void setShop_Address(String shop_Address) {
        Shop_Address = shop_Address;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
