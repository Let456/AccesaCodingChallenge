package com.example.price_comparator.service;

import com.example.price_comparator.model.Discount;
import com.example.price_comparator.util.DiscountCsvReader;

import java.time.LocalDate;
import java.util.*;

public class DiscountService {

    private static final List<String> STORES = List.of("lidl", "kaufland", "profi");

    public List<Discount> getTopXDiscounts(int x, LocalDate date) {
        List<Discount> allDiscounts = new ArrayList<>();

        for (String store : STORES) {
            String discountFile = String.format("data/%s_discounts_%s.csv", store, date);
            List<Discount> storeDiscounts = DiscountCsvReader.readDiscountsFromFile(discountFile);

            for (Discount discount : storeDiscounts) {
                // Verificăm dacă discountul este activ în ziua dată
                if (!date.isBefore(discount.getFromDate()) && !date.isAfter(discount.getToDate())) {
                    discount.setStoreName(store); // Setăm magazinul de unde vine reducerea
                    allDiscounts.add(discount);
                }
            }
        }

        // Sortăm descrescător după procentul de reducere
        allDiscounts.sort(Comparator.comparingInt(Discount::getPercentageOfDiscount).reversed());

        return allDiscounts.stream().limit(x).toList();
    }

    public List<Discount> getNewDiscounts(LocalDate date) {
        List<Discount> newDiscounts = new ArrayList<>();

        for (String store : STORES) {
            String discountFile = String.format("data/%s_discounts_%s.csv", store, date);
            List<Discount> storeDiscounts = DiscountCsvReader.readDiscountsFromFile(discountFile);

            for (Discount discount : storeDiscounts) {
                if (discount.getFromDate().equals(date)) {
                    discount.setStoreName(store); // să știm de unde vine
                    newDiscounts.add(discount);
                }
            }
        }

        return newDiscounts;
    }


}
