package com.example.price_comparator.controller;

import com.example.price_comparator.model.Product;
import com.example.price_comparator.service.BasketService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    private final BasketService basketService = new BasketService();

    @PostMapping("/optimize")
    public Map<String, List<Product>> optimizeBasket(
            @RequestBody List<String> shoppingList,
            @RequestParam String date) {

        LocalDate localDate = LocalDate.parse(date);
        return basketService.optimizeBasket(shoppingList, localDate);
    }
}
