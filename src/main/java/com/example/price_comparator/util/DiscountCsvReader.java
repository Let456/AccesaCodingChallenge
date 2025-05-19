package com.example.price_comparator.util;

import com.example.price_comparator.model.Discount;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiscountCsvReader {

    public static List<Discount> readDiscountsFromFile(String filePath) {
        try (InputStream input = DiscountCsvReader.class.getClassLoader().getResourceAsStream(filePath)) {
            if (input == null) {
                throw new IllegalArgumentException("Fișierul nu a fost găsit: " + filePath);
            }
            return readDiscounts(input);
        } catch (IOException e) {
            throw new RuntimeException("Eroare la citirea fișierului: " + filePath, e);
        }
    }

    public static List<Discount> readDiscounts(InputStream inputStream) throws IOException {
        List<Discount> discounts = new ArrayList<>();

        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withDelimiter(';')
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim())) {

            for (CSVRecord record : csvParser) {
                Discount discount = new Discount();
                discount.setProductId(record.get("product_id"));
                discount.setProductName(record.get("product_name"));
                discount.setBrand(record.get("brand"));
                discount.setPackageQuantity(Double.parseDouble(record.get("package_quantity")));
                discount.setPackageUnit(record.get("package_unit"));
                discount.setProductCategory(record.get("product_category"));
                discount.setFromDate(LocalDate.parse(record.get("from_date")));
                discount.setToDate(LocalDate.parse(record.get("to_date")));
                discount.setPercentageOfDiscount(Integer.parseInt(record.get("percentage_of_discount")));

                discounts.add(discount);
            }
        }

        return discounts;
    }
}
