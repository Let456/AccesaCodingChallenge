package com.example.price_comparator.service;

import com.example.price_comparator.model.Product;
import com.example.price_comparator.model.RecommendedProduct;
import com.example.price_comparator.util.ProductCsvReader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RecommendationService {
    private static final List<String> STORES = List.of("lidl", "kaufland", "profi");

    public List<RecommendedProduct> getBestValuePerUnit(String productName, LocalDate date) {
        List<RecommendedProduct> results = new ArrayList<>();

        for (String store : STORES) {
            String productFile = String.format("data/%s_%s.csv", store, date);

            List<Product> products;
            try {
                products = ProductCsvReader.readProductsFromFile(productFile);
            } catch (Exception e) {
                continue;
            }

            for (Product product : products) {
                if (product.getProductName().equalsIgnoreCase(productName)) {
                    double pricePerUnit = product.getPrice() / product.getPackageQuantity();

                    RecommendedProduct rec = new RecommendedProduct(
                            product.getProductName(),
                            product.getBrand(),
                            store,
                            product.getPrice(),
                            product.getPackageQuantity(),
                            product.getPackageUnit(),
                            pricePerUnit
                    );

                    results.add(rec);
                }
            }
        }

        // Sortăm crescător după valoare per unitate
        results.sort(Comparator.comparingDouble(RecommendedProduct::getPricePerUnit));
        return results;
    }
}
