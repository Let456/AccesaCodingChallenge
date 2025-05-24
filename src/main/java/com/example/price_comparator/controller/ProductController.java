package com.example.price_comparator.controller;

import com.example.price_comparator.model.PriceAlert;
import com.example.price_comparator.model.PriceEntry;
import com.example.price_comparator.model.Product;
import com.example.price_comparator.model.RecommendedProduct;
import com.example.price_comparator.service.AlertService;
import com.example.price_comparator.service.PriceHistoryService;
import com.example.price_comparator.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final PriceHistoryService priceHistoryService = new PriceHistoryService();
    private final RecommendationService recommendationService = new RecommendationService();
    private final AlertService alertService = new AlertService();

    @GetMapping("/price-history")
    public List<PriceEntry> getPriceHistory(
            @RequestParam String productName,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam Optional<String> store,
            @RequestParam Optional<String> category,
            @RequestParam Optional<String> brand) {

        return priceHistoryService.getPriceHistory(
                productName,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate),
                store,
                category,
                brand
        );
    }

    @GetMapping("/recommendations")
    public List<RecommendedProduct> getBestValue(
            @RequestParam String productName,
            @RequestParam String date) {

        return recommendationService.getBestValuePerUnit(
                productName,
                LocalDate.parse(date)
        );
    }

    @PostMapping("/alerts/check")
    public List<Product> checkAlerts(
            @RequestBody List<PriceAlert> alerts,
            @RequestParam String date) {

        return alertService.checkPriceAlerts(alerts, LocalDate.parse(date));
    }
}
