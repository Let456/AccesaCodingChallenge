package com.example.price_comparator.model;

public class Product {
    private String productId;
    private String productName;
    private String productCategory;
    private String brand;
    private double packageQuantity;
    private String packageUnit;
    private double price;
    private String currency;

    public Product() {
    }

    public Product(String productId, String productName, String productCategory, String brand, double packageQuantity, String packageUnit, double price, String currency) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.brand = brand;
        this.packageQuantity = packageQuantity;
        this.packageUnit = packageUnit;
        this.price = price;
        this.currency = currency;
    }

    public Product(Product other) {
        this.productId = other.getProductId();
        this.productName = other.getProductName();
        this.productCategory = other.getProductCategory();
        this.brand = other.getBrand();
        this.packageQuantity = other.getPackageQuantity();
        this.packageUnit = other.getPackageUnit();
        this.price = other.getPrice();
        this.currency = other.getCurrency();
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
