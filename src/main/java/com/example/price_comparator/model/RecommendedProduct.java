package com.example.price_comparator.model;

public class RecommendedProduct {
    private String productName;
    private String brand;
    private String store;
    private double price;
    private double packageQuantity;
    private String packageUnit;
    private double pricePerUnit;

    public RecommendedProduct(String productName, String brand, String store,
                              double price, double packageQuantity, String packageUnit,
                              double pricePerUnit) {
        this.productName = productName;
        this.brand = brand;
        this.store = store;
        this.price = price;
        this.packageQuantity = packageQuantity;
        this.packageUnit = packageUnit;
        this.pricePerUnit = pricePerUnit;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public double getPackageQuantity() {
        return packageQuantity;
    }

    public void setPackageQuantity(double packageQuantity) {
        this.packageQuantity = packageQuantity;
    }

    public String getPackageUnit() {
        return packageUnit;
    }

    public void setPackageUnit(String packageUnit) {
        this.packageUnit = packageUnit;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
