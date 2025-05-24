package com.example.price_comparator;


import com.example.price_comparator.model.Discount;
import com.example.price_comparator.model.PriceEntry;
import com.example.price_comparator.model.Product;
import com.example.price_comparator.service.BasketService;
import com.example.price_comparator.service.DiscountService;
import com.example.price_comparator.service.PriceHistoryService;
import com.example.price_comparator.model.RecommendedProduct;
import com.example.price_comparator.service.RecommendationService;
import com.example.price_comparator.model.PriceAlert;
import com.example.price_comparator.service.AlertService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
public class PriceComparatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceComparatorApplication.class, args);
	}

}


