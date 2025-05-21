package com.example.price_comparator.service;

import com.example.price_comparator.model.PriceEntry;
import com.example.price_comparator.model.Product;
import com.example.price_comparator.util.ProductCsvReader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PriceHistoryService {

    private static final List<String> STORES = List.of("lidl", "kaufland", "profi");

    public List<PriceEntry> getPriceHistory(String productName,
                                            LocalDate startDate,
                                            LocalDate endDate,
                                            Optional<String> storeFilter,
                                            Optional<String> categoryFilter,
                                            Optional<String> brandFilter) {
        List<PriceEntry> history = new ArrayList<>();

        for (String store : STORES) {
            if (storeFilter.isPresent() && !storeFilter.get().equalsIgnoreCase(store)) {
                continue;
            }

            LocalDate currentDate = startDate;
            while (!currentDate.isAfter(endDate)) {
                String productFile = String.format("data/%s_%s.csv", store, currentDate);
                List<Product> products;

                try {
                    products = ProductCsvReader.readProductsFromFile(productFile);
                } catch (Exception e) {
                    currentDate = currentDate.plusDays(1);
                    continue; // Dacă fișierul nu există pentru o zi, îl sărim
                }

                for (Product product : products) {
                    if (!product.getProductName().equalsIgnoreCase(productName)) continue;
                    if (categoryFilter.isPresent() &&
                            !product.getProductCategory().equalsIgnoreCase(categoryFilter.get())) continue;
                    if (brandFilter.isPresent() &&
                            !product.getBrand().equalsIgnoreCase(brandFilter.get())) continue;

                    history.add(new PriceEntry(currentDate, product.getPrice(), store));
                    break; // doar o apariție per zi
                }

                currentDate = currentDate.plusDays(1);
            }
        }

        return history;
    }
}
