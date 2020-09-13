package com.example.cashflow.Models;

public class Products {

    private String cost_price, sell_price, name, quantity;

    public Products(String cost_price, String sell_price, String name, String quantity) {
        this.cost_price = cost_price;
        this.sell_price = sell_price;
        this.name = name;
        this.quantity = quantity;
    }

    public Products() {
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
