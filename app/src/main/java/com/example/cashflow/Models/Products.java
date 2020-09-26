package com.example.cashflow.Models;

public class Products {

    private String cost_price, sell_price, name, quantity, date_time;

    public Products(String cost_price, String sell_price, String name, String quantity, String date_time) {
        this.cost_price = cost_price;
        this.sell_price = sell_price;
        this.name = name;
        this.quantity = quantity;
        this.date_time = date_time;
    }

    public Products() {
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String cost_price) {
        this.cost_price = cost_price;
    }

    public String getSell_price() {
        return sell_price;
    }

    public void setSell_price(String sell_price) {
        this.sell_price = sell_price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
