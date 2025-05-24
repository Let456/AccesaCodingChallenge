package com.example.price_comparator.controller;

import com.example.price_comparator.model.Discount;
import com.example.price_comparator.service.DiscountService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    private final DiscountService discountService = new DiscountService();

    @GetMapping("/top")
    public List<Discount> getTopDiscounts(
            @RequestParam int limit,
            @RequestParam String date) {
        return discountService.getTopXDiscounts(limit, LocalDate.parse(date));
    }

    @GetMapping("/new")
    public List<Discount> getNewDiscounts(@RequestParam String date) {
        return discountService.getNewDiscounts(LocalDate.parse(date));
    }
}
