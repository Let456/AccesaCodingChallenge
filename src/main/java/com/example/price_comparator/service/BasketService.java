package com.example.price_comparator.service;

import com.example.price_comparator.model.Product;
import com.example.price_comparator.model.Discount;
import com.example.price_comparator.util.ProductCsvReader;
import com.example.price_comparator.util.DiscountCsvReader;

import java.time.LocalDate;
import java.util.*;

public class BasketService {

    private static final List<String> STORES = List.of("lidl", "kaufland", "profi");

    public Map<String, List<Product>> optimizeBasket(List<String> shoppingList, LocalDate date) {
        Map<String, List<Product>> result = new HashMap<>();

        for (String productName : shoppingList) {
            Product bestProduct = null;
            String bestStore = null;
            double bestPricePerUnit = Double.MAX_VALUE;

            for (String store : STORES) {
                String productFile = String.format("data/%s_%s.csv", store, date);
                String discountFile = String.format("data/%s_discounts_%s.csv", store, date);

                List<Product> products = ProductCsvReader.readProductsFromFile(productFile);
                List<Discount> discounts = DiscountCsvReader.readDiscountsFromFile(discountFile);

                for (Product product : products) {
                    if (product.getProductName().equalsIgnoreCase(productName)) {
                        double finalPrice = product.getPrice();

                        for (Discount discount : discounts) {
                            if (discount.getProductId().equals(product.getProductId())
                                    && !date.isBefore(discount.getFromDate())
                                    && !date.isAfter(discount.getToDate())) {
                                double discountPercent = discount.getPercentageOfDiscount();
                                finalPrice -= finalPrice * discountPercent / 100.0;
                                break;
                            }
                        }

                        double pricePerUnit = finalPrice / product.getPackageQuantity();

                        if (pricePerUnit < bestPricePerUnit) {
                            Product copy = new Product(product);
                            copy.setPrice(finalPrice); // setam pretul redus efectiv
                            bestProduct = copy;
                            bestStore = store;
                            bestPricePerUnit = pricePerUnit;
                        }
                    }
                }
            }

            if (bestProduct != null && bestStore != null) {
                List<Product> productsInStore = result.get(bestStore);
                if (productsInStore == null) {
                    productsInStore = new ArrayList<>();
                    result.put(bestStore, productsInStore);
                }
                productsInStore.add(bestProduct);
            }
        }

        return result;
    }
}
