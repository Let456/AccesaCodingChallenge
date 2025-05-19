package com.example.price_comparator.util;

import com.example.price_comparator.model.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ProductCsvReader {

    public static List<Product> readProductsFromFile(String filePath) {
        try (InputStream input = ProductCsvReader.class.getClassLoader().getResourceAsStream(filePath)) {
            if (input == null) {
                throw new IllegalArgumentException("Fișierul nu a fost găsit: " + filePath);
            }
            return readProducts(input);
        } catch (IOException e) {
            throw new RuntimeException("Eroare la citirea fișierului: " + filePath, e);
        }
    }

    public static List<Product> readProducts(InputStream inputStream) throws IOException {
        List<Product> products = new ArrayList<>();

        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withDelimiter(';')
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim())) {

            for (CSVRecord record : csvParser) {
                Product product = new Product();
                product.setProductId(record.get("product_id"));
                product.setProductName(record.get("product_name"));
                product.setProductCategory(record.get("product_category"));
                product.setBrand(record.get("brand"));
                product.setPackageQuantity(Double.parseDouble(record.get("package_quantity")));
                product.setPackageUnit(record.get("package_unit"));
                product.setPrice(Double.parseDouble(record.get("price")));
                product.setCurrency(record.get("currency"));
                products.add(product);
            }
        }

        return products;
    }
}
