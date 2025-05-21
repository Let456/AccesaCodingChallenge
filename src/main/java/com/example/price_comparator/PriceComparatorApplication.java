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
		BasketService basketService = new BasketService();
		PriceHistoryService priceHistoryService = new PriceHistoryService();
		RecommendationService recommendationService = new RecommendationService();
		AlertService alertService = new AlertService();

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

		DiscountService discountService = new DiscountService();
		LocalDate newDate = LocalDate.of(2025, 5, 8);

		List<Discount> topDiscounts = discountService.getTopXDiscounts(5, newDate);

		System.out.println("📉 Top 5 reduceri active:");
		for (Discount d : topDiscounts) {
			System.out.printf(" - %s (%s, %s): %d%% reducere [%s → %s]\n",
					d.getProductName(),
					d.getBrand(),
					d.getProductCategory(),
					d.getPercentageOfDiscount(),
					d.getFromDate(),
					d.getToDate());
			System.out.println("   Magazin: " + d.getStoreName());
		}

		LocalDate today = LocalDate.of(2025, 5, 8);
		List<Discount> newDiscounts = discountService.getNewDiscounts(today);

		System.out.println("🆕 Reduceri adăugate azi:");
		for (Discount d : newDiscounts) {
			System.out.printf(" - %s (%s): %d%% [%s → %s] la %s\n",
					d.getProductName(),
					d.getBrand(),
					d.getPercentageOfDiscount(),
					d.getFromDate(),
					d.getToDate(),
					d.getStoreName());
		}

		List<PriceEntry> priceHistory = priceHistoryService.getPriceHistory(
				"lapte zuzu",
				LocalDate.of(2025, 5, 1),
				LocalDate.of(2025, 5, 8),
				Optional.of("lidl"),         // filtrăm după magazin
				Optional.empty(),            // fără filtru categorie
				Optional.of("Zuzu")          // filtrăm după brand
		);

		System.out.println("📈 Istoric prețuri pentru 'lapte zuzu' în Lidl:");
		for (PriceEntry entry : priceHistory) {
			System.out.printf(" - %s: %.2f RON (%s)\n", entry.getDate(), entry.getPrice(), entry.getStore());
		}

		System.out.println("\n💡 Recomandări pentru 'lapte zuzu' (valoare per unitate):");
		List<RecommendedProduct> recommendations = recommendationService.getBestValuePerUnit("lapte zuzu", LocalDate.of(2025, 5, 8));

		for (RecommendedProduct r : recommendations) {
			System.out.printf(" - %s (%s, %s): %.2f RON/%.2f %s = %.2f RON/unit (%s)\n",
					r.getProductName(),
					r.getBrand(),
					r.getStore(),
					r.getPrice(),
					r.getPackageQuantity(),
					r.getPackageUnit(),
					r.getPricePerUnit(),
					r.getStore());
		}

		List<PriceAlert> alerts = List.of(
				new PriceAlert("P034", 21.00), // cafea Davidoff
				new PriceAlert("P001", 10.00)  // lapte zuzu
		);

		List<Product> triggered = alertService.checkPriceAlerts(alerts, LocalDate.of(2025, 5, 8));

		System.out.println("\n🔔 Produse care au atins prețul țintă:");
		for (Product p : triggered) {
			System.out.printf(" - %s (%s): %.2f RON (%s)\n",
					p.getProductName(), p.getBrand(), p.getPrice(), p.getCurrency());
		}

	}

}


