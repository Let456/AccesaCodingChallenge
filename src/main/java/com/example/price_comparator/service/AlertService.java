package com.example.price_comparator.service;

import com.example.price_comparator.model.Discount;
import com.example.price_comparator.model.PriceAlert;
import com.example.price_comparator.model.Product;
import com.example.price_comparator.util.DiscountCsvReader;
import com.example.price_comparator.util.ProductCsvReader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlertService {

    private static final List<String> STORES = List.of("lidl", "kaufland", "profi");

    public List<Product> checkPriceAlerts(List<PriceAlert> alerts, LocalDate date) {
        List<Product> matchedProducts = new ArrayList<>();

        for (String store : STORES) {
            String productFile = String.format("data/%s_%s.csv", store, date);
            String discountFile = String.format("data/%s_discounts_%s.csv", store, date);

            List<Product> products;
            List<Discount> discounts;

            try {
                products = ProductCsvReader.readProductsFromFile(productFile);
                discounts = DiscountCsvReader.readDiscountsFromFile(discountFile);
            } catch (Exception e) {
                continue;
            }

            for (Product product : products) {
                for (PriceAlert alert : alerts) {
                    if (product.getProductId().equals(alert.getProductId())) {
                        double finalPrice = product.getPrice();

                        for (Discount discount : discounts) {
                            if (discount.getProductId().equals(product.getProductId())
                                    && !date.isBefore(discount.getFromDate())
                                    && !date.isAfter(discount.getToDate())) {
                                finalPrice -= finalPrice * discount.getPercentageOfDiscount() / 100.0;
                                break;
                            }
                        }

                        if (finalPrice <= alert.getTargetPrice()) {
                            Product copy = new Product(product);
                            copy.setPrice(finalPrice); // adăugăm prețul final cu reducere
                            matchedProducts.add(copy);
                        }
                    }
                }
            }
        }

        return matchedProducts;
    }
}
