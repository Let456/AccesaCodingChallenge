package com.example.price_comparator.model;

import java.time.LocalDate;

public class PriceEntry {
    private LocalDate date;
    private double price;
    private String store;

    public PriceEntry(LocalDate date, double price, String store) {
        this.date = date;
        this.price = price;
        this.store = store;
    }

    public PriceEntry() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
