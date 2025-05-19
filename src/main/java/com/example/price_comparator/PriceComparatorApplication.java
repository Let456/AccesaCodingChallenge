package com.example.price_comparator;


import com.example.price_comparator.model.Product;
import com.example.price_comparator.service.BasketService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class PriceComparatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceComparatorApplication.class, args);
		BasketService basketService = new BasketService();

		List<String> shoppingList = List.of("lapte zuzu", "pâine albă", "cafea măcinată");
		LocalDate date = LocalDate.of(2025, 5, 8);

		Map<String, List<Product>> optimizedBasket = basketService.optimizeBasket(shoppingList, date);

		for (Map.Entry<String, List<Product>> entry : optimizedBasket.entrySet()) {
			String store = entry.getKey();
			List<Product> products = entry.getValue();

			System.out.println("📦 Magazin: " + store);
			for (Product p : products) {
				System.out.println(" - " + p.getProductName() + " | " + p.getPrice() + " RON");
			}
		}

	}

}
